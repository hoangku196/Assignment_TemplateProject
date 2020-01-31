package com.example.myapplication.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.example.myapplication.R;
import com.example.myapplication.databinding.AdapterListBillBinding;
import com.example.myapplication.model.Bill;

import java.util.List;

public class BillAdapter extends BaseAdapter {

    private List<Bill> bills;
    private LayoutInflater inflater;

    private AdapterListBillBinding adapterListBillBinding;

    public BillAdapter(List<Bill> bills, Context context) {
        this.bills = bills;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return bills.size();
    }

    @Override
    public Object getItem(int position) {
        return bills.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            adapterListBillBinding = DataBindingUtil.inflate(inflater, R.layout.adapter_list_bill, parent, false);

            Bill bill = (Bill) getItem(position);
            adapterListBillBinding.setBill(bill);

            ConstraintLayout navigate = adapterListBillBinding.getRoot().findViewById(R.id.navigate_to_bill_detail);
            navigate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("key_id", adapterListBillBinding.getBill().getId());
                    Navigation.findNavController(v).navigate(R.id.action_listBill_to_billDetail, bundle);
                }
            });
        }

        return adapterListBillBinding.getRoot();
    }
}
