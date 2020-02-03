package com.example.myapplication.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.BookAdapter;
import com.example.myapplication.dao.BillDetailsDAO;
import com.example.myapplication.model.Book;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BestSelling extends Fragment {

    private BillDetailsDAO billDetailsDAO;

    private List<Book> books;

    private BookAdapter bookAdapter;

    private EditText edTopBestSelling;
    private Button btnSearchTopSelling;
    private ListView lvTopSelling;

    public BestSelling() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_best_selling, container, false);
        billDetailsDAO = new BillDetailsDAO(getActivity());
        edTopBestSelling = view.findViewById(R.id.edTopBestSelling);
        lvTopSelling = view.findViewById(R.id.lvTopSelling);
        btnSearchTopSelling = view.findViewById(R.id.btnSearchTopSelling);
        btnSearchTopSelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int top = Integer.parseInt(edTopBestSelling.getText().toString());
                books = billDetailsDAO.getTopSellingBook(top);
                bookAdapter = new BookAdapter(getActivity(), books);
                lvTopSelling.setAdapter(bookAdapter);
            }
        });

        return view;
    }

}
