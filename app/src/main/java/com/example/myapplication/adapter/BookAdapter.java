package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.example.myapplication.R;
import com.example.myapplication.dao.BookDAO;
import com.example.myapplication.databinding.AdapterListBookBinding;
import com.example.myapplication.model.Book;

import java.util.List;

public class BookAdapter extends BaseAdapter {

    private List<Book> books;
    private LayoutInflater inflater;

    private BookDAO bookDAO;

    private AdapterListBookBinding adapterListBookBinding;

    public BookAdapter(Context context, List<Book> books) {
        this.books = books;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            bookDAO = new BookDAO(parent.getContext());
            adapterListBookBinding = DataBindingUtil.inflate(inflater, R.layout.adapter_list_book, parent, false);
            final Book book = (Book) getItem(position);

            ImageView viewDeleteBook = adapterListBookBinding.getRoot().findViewById(R.id.viewDeleteBook);
            viewDeleteBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bookDAO.deleteBook(book))
                        Toast.makeText(parent.getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                }
            });

            adapterListBookBinding.setBook(book);
        }

        return adapterListBookBinding.getRoot();
    }

    public void refeshDataList(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

}
