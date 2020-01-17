package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;

import com.example.myapplication.R;
import com.example.myapplication.databinding.AdapterListUserBinding;
import com.example.myapplication.model.User;

import java.util.List;

public class UserAdapter extends BaseAdapter implements View.OnClickListener {
    private List<User> users;
    private LayoutInflater inflater;

    private AdapterListUserBinding adapterListUserBinding;

    public UserAdapter(Context context, List<User> users) {
        this.users = users;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            adapterListUserBinding = DataBindingUtil.inflate(inflater, R.layout.adapter_list_user, parent, false);
            User user = (User) getItem(position);
            adapterListUserBinding.setUser(user);
            UserImage userImage;
            if (position % 3 == 0)
                userImage = new UserImage(R.drawable.emone);
            else if (position % 3 == 1)
                userImage = new UserImage(R.drawable.emtwo);
            else
                userImage = new UserImage(R.drawable.emthree);
            ImageView viewAvatarUser = adapterListUserBinding.getRoot().findViewById(R.id.viewAvatarUser);
            viewAvatarUser.setImageResource(userImage.getImage());
        }

        return adapterListUserBinding.getRoot();
    }

    public void refeshDataList(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.deleteUser:
                break;
        }
    }

    public class UserImage {
        private int image;

        public UserImage(int image) {
            this.image = image;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }
    }
}
