package com.example.myapplication.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.BillDAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Bill extends Fragment {

    private EditText edIdBill, edDateBill;
    private Button btnDateBill, btnAddBill, btnCancelBill, btnShowBill;

    //DAO
    private BillDAO billDAO;

    public Bill() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill, container, false);

        billDAO = new BillDAO(getActivity());

        edIdBill = view.findViewById(R.id.edIdBill);
        edDateBill = view.findViewById(R.id.edDateBill);

        btnDateBill = view.findViewById(R.id.btnDateBill);
        btnDateBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DATE);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        calendar.set(year, month, dayOfMonth);
                        edDateBill.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        btnAddBill = view.findViewById(R.id.btnAddBill);
        btnAddBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = edIdBill.getText().toString();
                String date = edDateBill.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("key_id", id);

                if (billDAO.insertBill(new com.example.myapplication.model.Bill(id, date))) {
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigate(R.id.action_bill_to_billDetail, bundle);
                } else
                    Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

            }
        });

        btnCancelBill = view.findViewById(R.id.btnCancelBill);

        btnShowBill = view.findViewById(R.id.btnShowBill);
        btnShowBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_bill_to_listBill);
            }
        });

        return view;
    }

}
