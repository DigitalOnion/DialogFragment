package com.outspace.dialogfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

public class FirstFragment extends Fragment {

    public FirstFragment() { } // Required empty public constructor

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("FRAGMENT", "onAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("FRAGMENT", "onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("FRAGMENT", "onStart");
        Button btn = (Button) getActivity().findViewById(R.id.btn_fragment_two);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                SecondFragment fragment = new SecondFragment();
                transaction.add(R.id.fragment_place_holder, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
