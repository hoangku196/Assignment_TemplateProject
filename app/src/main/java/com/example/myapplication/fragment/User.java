package com.example.myapplication.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.UserDAO;


/**
 * A simple {@link Fragment} subclass.
 */
public class User extends Fragment {

    private EditText edUserName, edPassword, edRePassword, edPhone, edFullName;

    private Button btnAddUser, btnShowUser, btnCancelUser;

    private UserDAO userDAO;

    public User() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        getActivity().setTitle("Người dùng");

        userDAO = new UserDAO(getActivity());

        edUserName = view.findViewById(R.id.edUserName);
        edPassword = view.findViewById(R.id.edPassword);
        edRePassword = view.findViewById(R.id.edRePassword);
        edPhone = view.findViewById(R.id.edPhone);
        edFullName = view.findViewById(R.id.edFullName);

        btnAddUser = view.findViewById(R.id.btnAddUser);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edUserName.getText().toString();
                String password = edPassword.getText().toString();
                String rePassword = edRePassword.getText().toString();
                String phone = edPhone.getText().toString();
                String fullName = edFullName.getText().toString();
                if (userName.isEmpty() || password.isEmpty() || rePassword.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(getActivity(), "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!password.equalsIgnoreCase(rePassword)) {
                    Toast.makeText(getActivity(), "Hai mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
                } else {
                    if (userDAO.insertUser(new com.example.myapplication.model.User(userName, password, phone, fullName)))
                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), "Thêm thất bại kiểm tra lại UserName", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUserName.setText("");
                edPassword.setText("");
                edRePassword.setText("");
                edPhone.setText("");
                edFullName.setText("");
            }
        });

        btnShowUser = view.findViewById(R.id.btnShowUser);
        btnShowUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_user_to_listUser);
            }
        });

        return view;
    }

}
