package com.example.myapplication.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.BillDetailAdapter;
import com.example.myapplication.dao.BillDetailsDAO;
import com.example.myapplication.dao.BookDAO;
import com.example.myapplication.model.Bill;
import com.example.myapplication.model.BillDetails;
import com.example.myapplication.model.Book;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BillDetail extends Fragment {

    public static final String TAG = "BillDetail";

    private EditText edIdBillDetail, edAmountBillDetail;
    private Spinner spnIdBook;
    private Button btnAddBillDetail, btnPayBillDetail;
    private ListView lvBillDetailPreview;
    private TextView tvIdBill;

    public static List<BillDetails> billDetailsPreview = new ArrayList<>();

    //DAO
    private BookDAO bookDAO;
    private BillDetailsDAO billDetailsDAO;

    public BillDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill_detail, container, false);
        bookDAO = new BookDAO(getActivity());
        billDetailsDAO = new BillDetailsDAO(getActivity());

        tvIdBill = view.findViewById(R.id.tvIdBill);
        tvIdBill.setText("Mã Hóa đơn :" + getArguments().getString("key_id"));
        edIdBillDetail = view.findViewById(R.id.edIdBillDetail);
        edAmountBillDetail = view.findViewById(R.id.edAmountBillDetail);
        lvBillDetailPreview = view.findViewById(R.id.lvBillDetailPreview);

        ArrayAdapter<Book> bookArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, bookDAO.getAllBook());
        bookArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnIdBook = view.findViewById(R.id.spnIdBook);
        spnIdBook.setAdapter(bookArrayAdapter);

        btnPayBillDetail = view.findViewById(R.id.btnPayBillDetail);
        btnPayBillDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (BillDetails billDetails : billDetailsPreview) {
                    billDetailsDAO.insertBillDetails(billDetails);
                }
                Navigation.findNavController(v).navigate(R.id.action_billDetail_to_listBillDetails);
            }
        });

        btnAddBillDetail = view.findViewById(R.id.btnAddBillDetail);
        btnAddBillDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (billDetailsPreview.size() <= 0) {
                    String id = edIdBillDetail.getText().toString();
                    int amount = Integer.parseInt(edAmountBillDetail.getText().toString());

                    com.example.myapplication.model.Book book = (Book) spnIdBook.getSelectedItem();
                    String idBill = getArguments().getString("key_id");
                    try {
                        if (amount > bookDAO.getAmountBookById(book.getId()))
                            Toast.makeText(getActivity(), "Số lượng sách lớn hơn lượng sách đang có", Toast.LENGTH_SHORT).show();
                        else
                            billDetailsPreview.add(new BillDetails(id, new Bill(idBill), book, amount));
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String id = edIdBillDetail.getText().toString();
                    int amount = Integer.parseInt(edAmountBillDetail.getText().toString());
                    try {
                        for (BillDetails billDetails : billDetailsPreview) {
                            amount += billDetails.getAmount();
                        }

                        com.example.myapplication.model.Book book = (Book) spnIdBook.getSelectedItem();
                        String idBill = getArguments().getString("key_id");
                        if (amount <= bookDAO.getAmountBookById(book.getId()))
                            billDetailsPreview.add(new BillDetails(id, new Bill(idBill), book, amount));
                        else
                            Toast.makeText(getActivity(), "Số lượng sách mua lớn hơn số lượng sách hiện có", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                BillDetailAdapter billDetailAdapter = new BillDetailAdapter(billDetailsPreview, getActivity(), TAG);
                lvBillDetailPreview.setAdapter(billDetailAdapter);
            }
        });

        return view;
    }

}
