package com.example.myapplication.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.model.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePassword extends Fragment {

    private EditText edChangeUser, edChangePassword, edNewPassword;
    private Button btnUpdateUser;

    private UserDAO userDAO;

    public ChangePassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);

        userDAO = new UserDAO(getActivity());

        edChangeUser = view.findViewById(R.id.edChangeUser);
        edChangePassword = view.findViewById(R.id.edChangePassword);
        edNewPassword = view.findViewById(R.id.edNewPassword);

        btnUpdateUser = view.findViewById(R.id.btnUpdateUser);
        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idUser = edChangeUser.getText().toString();
                String changePassword = edChangePassword.getText().toString();
                String newPassword = edNewPassword.getText().toString();
                if (!idUser.isEmpty() || !changePassword.isEmpty() || !newPassword.isEmpty()) {
                    if (userDAO.updatePassword(new User(idUser, changePassword), newPassword)) {
                        Toast.makeText(getActivity(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    } else
                        Toast.makeText(getActivity(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
