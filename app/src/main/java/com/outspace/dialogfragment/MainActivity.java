package com.outspace.dialogfragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 12345;
    private String phone = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = this.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        FirstFragment fragment = new FirstFragment();
        transaction.add(R.id.fragment_place_holder, fragment);
        transaction.commit();
    }

    public void dial(String phone) {
        this.phone = phone;
        int permissionCheck = ContextCompat
                .checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED)
            doTheActualCall(phone);
        else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE:
                doTheActualCall(phone);
                break;
            default:
                Toast.makeText(this, "Dang it, unknown permission", Toast.LENGTH_SHORT).show();
        }
    }

    private void doTheActualCall(String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone));
        try {
            this.startActivity(intent);
        }
        catch (SecurityException se) {
            String s = this.getResources().getString(R.string.msg_need_phone_permission);
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }
    }
}
