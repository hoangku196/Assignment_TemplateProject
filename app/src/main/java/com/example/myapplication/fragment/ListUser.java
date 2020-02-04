package com.example.myapplication.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.model.User;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListUser extends Fragment {

    private List<User> users;

    private ListView lvUser;

    private UserAdapter userAdapter;

    private UserDAO userDAO;

    public ListUser() {
        // Required empty public constructor
    }

    //TODO delete user
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_list_user, container, false);

        userDAO = new UserDAO(getActivity());

        users = userDAO.getAllUser();

        userAdapter = new UserAdapter(view.getContext(), users);

        lvUser = view.findViewById(R.id.lvUser);

        lvUser.setAdapter(userAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        users.clear();
        users = userDAO.getAllUser();
        userAdapter.refeshDataList(users);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_user_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_add_new_user:
                getActivity().onBackPressed();
                return true;
            case R.id.action_change_password_user:
                NavHostFragment.findNavController(this).navigate(R.id.action_listUser_to_changePassword2);
                return true;
            case R.id.action_log_out:
                NavHostFragment.findNavController(this).navigate(R.id.action_listUser_to_login);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
