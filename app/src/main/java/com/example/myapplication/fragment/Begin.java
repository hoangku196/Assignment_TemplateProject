package com.example.myapplication.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Begin extends Fragment {


    public Begin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_begin, container, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate();
            }
        }, 1500);

        return view;
    }

    private void navigate() {
        NavHostFragment.findNavController(this).navigate(R.id.action_begin_to_login);
    }

}
