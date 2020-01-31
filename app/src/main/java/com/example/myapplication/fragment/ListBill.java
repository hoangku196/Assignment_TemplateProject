package com.example.myapplication.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.BillAdapter;
import com.example.myapplication.dao.BillDAO;
import com.example.myapplication.model.Bill;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListBill extends Fragment {

    private ListView lvBill;
    private List<Bill> bills;
    private BillDAO billDAO;
    private BillAdapter billAdapter;


    public ListBill() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_bill, container, false);

        billDAO = new BillDAO(getActivity());

        bills = billDAO.getAllBill();
        billAdapter = new BillAdapter(bills, getActivity());
        lvBill = view.findViewById(R.id.lvBill);
        lvBill.setAdapter(billAdapter);

        return view;
    }

}
