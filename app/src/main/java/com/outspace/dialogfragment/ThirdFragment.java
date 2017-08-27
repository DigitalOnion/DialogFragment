package com.outspace.dialogfragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdFragment extends Fragment {

    public ThirdFragment() { }     // Required empty public constructor

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Button btnDial = (Button) getActivity().findViewById(R.id.btn_dial);
        final EditText txtPhone = (EditText) getActivity().findViewById(R.id.edit_phone);
        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dialNumber = txtPhone.getText().toString();

                MainActivity main = (MainActivity) getActivity();
                main.dial(dialNumber);

                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.popBackStack();
            }
        });
    }
}
