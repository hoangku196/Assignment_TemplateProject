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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.BillDetailsDAO;
import com.example.myapplication.dao.BookDAO;
import com.example.myapplication.model.Bill;
import com.example.myapplication.model.BillDetails;
import com.example.myapplication.model.Book;


/**
 * A simple {@link Fragment} subclass.
 */
public class BillDetail extends Fragment {

    private EditText edIdBillDetail, edAmountBillDetail;
    private Spinner spnIdBook;
    private Button btnAddBillDetail, btnCancelBillDetail, btnShowBillDetail;

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

        edIdBillDetail = view.findViewById(R.id.edIdBillDetail);
        edAmountBillDetail = view.findViewById(R.id.edAmountBillDetail);

        ArrayAdapter<Book> bookArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        bookArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnIdBook = view.findViewById(R.id.spnIdBook);
        spnIdBook.setAdapter(bookArrayAdapter);

        btnAddBillDetail = view.findViewById(R.id.btnAddBillDetail);
        btnAddBillDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edIdBillDetail.getText().toString();
                int amount = Integer.parseInt(edAmountBillDetail.getText().toString());

                com.example.myapplication.model.Book book = (Book) spnIdBook.getSelectedItem();
                String idBill = getArguments().getString("key_id");
                if (billDetailsDAO.insertBillDetails(new BillDetails(id, new Bill(idBill), book, amount)))
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancelBillDetail = view.findViewById(R.id.btnCancelBillDetail);

        btnShowBillDetail = view.findViewById(R.id.btnShowBillDetail);
        btnShowBillDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_billDetail_to_listBillDetails);
            }
        });

        return view;
    }

}
