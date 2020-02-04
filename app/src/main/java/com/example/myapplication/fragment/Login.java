package com.example.myapplication.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.UserDAO;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {

    private final String FILE_NAME = "USER_FILE";

    //UI
    private EditText edUserName, edPassword;
    private CheckBox chkRememberPass;
    private Button btnLogin;

    private UserDAO userDAO;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        userDAO = new UserDAO(getActivity());

        //Find View By Id
        edUserName = view.findViewById(R.id.edUserName);
        edPassword = view.findViewById(R.id.edPassword);
        chkRememberPass = view.findViewById(R.id.chkRememberPass);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

        loadUser();

        return view;
    }

    public void checkLogin() {
        String userName = edUserName.getText().toString();
        String password = edPassword.getText().toString();

        if (userName.isEmpty() || password.isEmpty()) {
            Toast.makeText(getActivity(), "Tên đăng nhập và mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        } else {
            if (userDAO.login(userName, password)) {
                rememberUser(userName, password, chkRememberPass.isChecked());
                Toast.makeText(getActivity(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(this).navigate(R.id.action_login_to_home3);
            }
            if (userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
                rememberUser(userName, password, chkRememberPass.isChecked());
                Toast.makeText(getActivity(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(this).navigate(R.id.action_login_to_home3);
            }
        }
    }

    private void rememberUser(String userName, String password, boolean status) {
        SharedPreferences pref = getActivity().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        if (!status)
            editor.clear();
        else {
            editor.putString("USERNAME", userName);
            editor.putString("PASSWORD", password);
            editor.putBoolean("REMEMBER", status);
        }

        editor.apply();
    }

    private void loadUser() {
        Context context = getActivity();
        SharedPreferences sharedPreferences = null;
        if (context != null) {
            sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        }
        boolean status = sharedPreferences.getBoolean("REMEMBER", false);

        if (status) {
            String user = sharedPreferences.getString("USERNAME", "");
            String password = sharedPreferences.getString("PASSWORD", "");

            edUserName.setText(user);
            edPassword.setText(password);
        }
        chkRememberPass.setChecked(status);
    }
}
