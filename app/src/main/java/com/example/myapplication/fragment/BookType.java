package com.example.myapplication.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.BookTypeDAO;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookType extends Fragment {

    private BookTypeDAO bookTypeDAO;

    private EditText edIdBookType, edNameBookType, edLocationBookType, edDescribeBookType;

    private Button btnAddBookType, btnCancelBookType, btnShowBookType;

    public BookType() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_type, container, false);

        bookTypeDAO = new BookTypeDAO(getActivity());

        edIdBookType = view.findViewById(R.id.edIdBookType);
        edNameBookType = view.findViewById(R.id.edNameBookType);
        edLocationBookType = view.findViewById(R.id.edLocationBookType);
        edDescribeBookType = view.findViewById(R.id.edDescribeBookType);

        btnAddBookType = view.findViewById(R.id.btnAddBookType);
        btnAddBookType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO btnAddBookType
                String id = edIdBookType.getText().toString();
                String name = edNameBookType.getText().toString();
                String location = edLocationBookType.getText().toString();
                String describe = edDescribeBookType.getText().toString();
                if (bookTypeDAO.insertBookType(new com.example.myapplication.model.BookType(id, name, describe, location)))
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Thêm thất bại kiểm tra lại mã loại sách", Toast.LENGTH_SHORT).show();
            }
        });

        btnShowBookType = view.findViewById(R.id.btnShowBookType);
        btnShowBookType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_bookType_to_listBookType);
            }
        });

        return view;
    }

}
