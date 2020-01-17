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
import com.example.myapplication.dao.BookDAO;
import com.example.myapplication.dao.BookTypeDAO;


/**
 * A simple {@link Fragment} subclass.
 */
public class Book extends Fragment {

    private EditText edIdBook, edNameBook, edAuthorBook, edPublishingCompanyBook, edPriceBook;
    private Spinner spnBookType;
    private Button btnAddBook, btnCancelBook, btnShowBook;

    //DAO
    private BookDAO bookDAO;
    private BookTypeDAO bookTypeDAO;

    public Book() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book, container, false);

        bookDAO = new BookDAO(getActivity());
        bookTypeDAO = new BookTypeDAO(getActivity());

        edIdBook = view.findViewById(R.id.edIdBook);
        edNameBook = view.findViewById(R.id.edNameBook);
        edAuthorBook = view.findViewById(R.id.edAuthorBook);
        edPublishingCompanyBook = view.findViewById(R.id.edPublishingCompanyBook);
        edPriceBook = view.findViewById(R.id.edPriceBook);

        ArrayAdapter<com.example.myapplication.model.BookType> bookTypeArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, bookTypeDAO.getAllBookType());
        bookTypeArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnBookType = view.findViewById(R.id.spnBookType);
        spnBookType.setAdapter(bookTypeArrayAdapter);

        btnAddBook = view.findViewById(R.id.btnAddBook);
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edIdBook.getText().toString();
                String name = edNameBook.getText().toString();
                String author = edAuthorBook.getText().toString();
                String publishingCompany = edPublishingCompanyBook.getText().toString();
                float priceBook = Float.parseFloat(edPriceBook.getText().toString());

                com.example.myapplication.model.BookType bookType = (com.example.myapplication.model.BookType) spnBookType.getSelectedItem();
                if (bookDAO.insertBook(new com.example.myapplication.model.Book(id, bookType, name, author, publishingCompany, priceBook)))
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Thêm thất bại kiểm tra lại mã sách", Toast.LENGTH_SHORT).show();
            }
        });


        btnCancelBook = view.findViewById(R.id.btnCancelBook);

        btnShowBook = view.findViewById(R.id.btnShowBook);
        btnShowBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_book_to_listBook);
            }
        });


        return view;
    }

}
