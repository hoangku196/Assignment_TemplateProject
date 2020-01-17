package com.example.myapplication.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    private ImageView viewUser, viewBook, viewBestSelling, viewBookType, viewBill, viewStatistic;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewUser = view.findViewById(R.id.viewUser);
        viewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_home3_to_user);
            }
        });

        viewBook = view.findViewById(R.id.viewBook);
        viewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_home3_to_book);
            }
        });

        viewBestSelling = view.findViewById(R.id.viewBestSelling);
        viewBestSelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_home3_to_bestSelling);
            }
        });

        viewBookType = view.findViewById(R.id.viewBookType);
        viewBookType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_home3_to_bookType);
            }
        });

        viewBill = view.findViewById(R.id.viewBill);
        viewBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_home3_to_bill);
            }
        });

        viewStatistic = view.findViewById(R.id.viewStatistic);
        viewStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_home3_to_statistic);
            }
        });

        return view;
    }

}
