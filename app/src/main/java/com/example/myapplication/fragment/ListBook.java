package com.example.myapplication.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.BookAdapter;
import com.example.myapplication.dao.BookDAO;
import com.example.myapplication.model.Book;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListBook extends Fragment {

    private List<Book> books;

    private ListView lvBook;

    private BookAdapter bookAdapter;

    private BookDAO bookDAO;


    public ListBook() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_book, container, false);
        bookDAO = new BookDAO(getActivity());

        books = bookDAO.getAllBook();

        bookAdapter = new BookAdapter(view.getContext(), books);

        lvBook = view.findViewById(R.id.lvBook);

        lvBook.setAdapter(bookAdapter);

        return view;
    }

}
