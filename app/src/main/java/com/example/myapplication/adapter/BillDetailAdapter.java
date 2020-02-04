package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;

import com.example.myapplication.R;
import com.example.myapplication.dao.BillDetailsDAO;
import com.example.myapplication.dao.BookDAO;
import com.example.myapplication.databinding.AdapterListBillDetailBinding;
import com.example.myapplication.fragment.BillDetail;
import com.example.myapplication.fragment.ListBillDetails;
import com.example.myapplication.model.BillDetails;

import java.util.List;

public class BillDetailAdapter extends BaseAdapter {

    private List<BillDetails> billDetails;
    private LayoutInflater inflater;
    private String nameFragment;
    private BillDetailsDAO billDetailsDAO;

    private AdapterListBillDetailBinding adapterListBillDetailBinding;

    public BillDetailAdapter(List<BillDetails> billDetails, Context context, String nameFragment) {
        this.billDetails = billDetails;
        this.nameFragment = nameFragment;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null && nameFragment.equals(BillDetail.TAG)) {
            adapterListBillDetailBinding = DataBindingUtil.inflate(inflater, R.layout.adapter_list_bill_detail, parent, false);
            ImageView viewDeleteBillDetail = adapterListBillDetailBinding.getRoot().findViewById(R.id.viewDeleteBillDetail);

            final BillDetails billDetails = (BillDetails) getItem(position);

            Detail detail = new Detail(billDetails.getBook().getId(), billDetails.getAmount(), billDetails.getBook().getPriceBook());
            adapterListBillDetailBinding.setDetail(detail);
            viewDeleteBillDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BillDetail.billDetailsPreview.remove(billDetails);
                }
            });
        }
        if (convertView == null && nameFragment.equals(ListBillDetails.TAG)) {
            billDetailsDAO = new BillDetailsDAO(parent.getContext());

            adapterListBillDetailBinding = DataBindingUtil.inflate(inflater, R.layout.adapter_list_bill_detail, parent, false);
            ImageView viewDeleteBillDetail = adapterListBillDetailBinding.getRoot().findViewById(R.id.viewDeleteBillDetail);

            final BillDetails billDetails = (BillDetails) getItem(position);

            Detail detail = new Detail(billDetails.getBook().getId(), billDetails.getAmount(), billDetails.getBook().getPriceBook());
            adapterListBillDetailBinding.setDetail(detail);
            viewDeleteBillDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    billDetailsDAO.deleteBillDetails(billDetails);
                }
            });
        }

        return adapterListBillDetailBinding.getRoot();
    }

    //TODO LÀM NỐT NHA
    public class Detail {
        private String idBook;
        private String amount;
        private String price;
        private String total;

        Detail(String idBook, int amount, double price) {
            this.idBook = "Mã sách :" + idBook;
            this.amount = "Số lượng :" + amount;
            this.price = "Giá bìa :" + price;
            this.total = "Thành tiền :" + (price * amount);
        }

        public String getIdBook() {
            return "Mã sách :" + idBook;
        }

        public String getAmount() {
            return "Số lượng :" + amount;
        }

        public String getPrice() {
            return "Giá bìa :" + price;
        }

        public String getTotal() {
            return "Thành tiền :" + total;
        }
    }
}
