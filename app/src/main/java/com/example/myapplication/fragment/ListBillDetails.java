package com.example.myapplication.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.BillDetailAdapter;
import com.example.myapplication.dao.BillDetailsDAO;
import com.example.myapplication.model.BillDetails;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListBillDetails extends Fragment {

    public static final String TAG = "ListBillDetails";

    private ListView lvBillDetail;

    private BillDetailsDAO billDetailsDAO;
    private BillDetailAdapter billDetailAdapter;

    private List<BillDetails> billDetails;

    public ListBillDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_bill_details, container, false);
        billDetailsDAO = new BillDetailsDAO(getContext());

        String idBill = getArguments().getString("key_idBill");

        billDetails = billDetailsDAO.getAllBillDetails(idBill);

        billDetailAdapter = new BillDetailAdapter(billDetails, getActivity(), TAG);

        lvBillDetail = view.findViewById(R.id.lvBillDetail);
        lvBillDetail.setAdapter(billDetailAdapter);

        return view;
    }

}
