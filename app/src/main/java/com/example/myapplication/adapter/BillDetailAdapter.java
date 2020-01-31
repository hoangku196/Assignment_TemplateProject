package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.example.myapplication.R;
import com.example.myapplication.dao.BookDAO;
import com.example.myapplication.databinding.AdapterListBillDetailBinding;
import com.example.myapplication.model.BillDetails;

import java.util.List;

public class BillDetailAdapter extends BaseAdapter {

    private List<BillDetails> billDetails;
    private LayoutInflater inflater;

    private BookDAO bookDAO;

    private AdapterListBillDetailBinding adapterListBillDetailBinding;

    public BillDetailAdapter(List<BillDetails> billDetails, Context context) {
        this.billDetails = billDetails;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return billDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return billDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            adapterListBillDetailBinding = DataBindingUtil.inflate(inflater, R.layout.adapter_list_bill_detail, parent, false);

            BillDetails billDetails = (BillDetails) getItem(position);

            Detail detail = new Detail(billDetails.getBook().getId(), billDetails.getAmount(), billDetails.getBook().getPriceBook());
            adapterListBillDetailBinding.setDetail(detail);
        }

        return null;
    }

    //TODO LÀM NỐT NHA
    private class Detail {
        private String idBook;
        private int amount;
        private double price;
        private double total;

        public Detail(String idBook, int amount, double price) {
            this.idBook = idBook;
            this.amount = amount;
            this.price = price;
            this.total = this.price * this.amount;
        }

        public String getIdBook() {
            return idBook;
        }

        public int getAmount() {
            return amount;
        }

        public double getPrice() {
            return price;
        }

        public double getTotal() {
            return total;
        }
    }
}
