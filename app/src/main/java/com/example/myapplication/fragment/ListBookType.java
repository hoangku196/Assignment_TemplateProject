package com.example.myapplication.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.BookTypeAdapter;
import com.example.myapplication.dao.BookTypeDAO;
import com.example.myapplication.model.BookType;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListBookType extends Fragment {

    private List<BookType> bookTypes;
    private BookTypeAdapter bookTypeAdapter;
    private BookTypeDAO bookTypeDAO;
    private ListView lvBookType;

    public ListBookType() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_book_type, container, false);

        bookTypeDAO = new BookTypeDAO(getActivity());

        bookTypes = bookTypeDAO.getAllBookType();
        bookTypeAdapter = new BookTypeAdapter(bookTypes, getActivity());
        lvBookType = view.findViewById(R.id.lvBookType);
        lvBookType.setAdapter(bookTypeAdapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_booktype_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add_new_book_type) {
            getActivity().onBackPressed();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }
}
