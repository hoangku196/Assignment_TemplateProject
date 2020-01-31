package com.example.myapplication.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.example.myapplication.R;
import com.example.myapplication.dao.BookTypeDAO;
import com.example.myapplication.databinding.AdapterListBookTypeBinding;
import com.example.myapplication.model.BookType;

import java.util.List;

public class BookTypeAdapter extends BaseAdapter {

    private List<BookType> bookTypes;
    private LayoutInflater inflater;

    private AdapterListBookTypeBinding adapterListBookTypeBinding;

    public BookTypeAdapter(List<BookType> bookTypes, Context context) {
        this.bookTypes = bookTypes;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return bookTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return bookTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            adapterListBookTypeBinding = DataBindingUtil.inflate(inflater, R.layout.adapter_list_book_type, parent, false);

            BookType bookType = (BookType) getItem(position);

            adapterListBookTypeBinding.setBookType(bookType);
        }

        return adapterListBookTypeBinding.getRoot();
    }
}
