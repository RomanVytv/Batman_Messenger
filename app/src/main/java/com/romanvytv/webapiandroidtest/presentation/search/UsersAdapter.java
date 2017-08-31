package com.romanvytv.webapiandroidtest.presentation.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.romanvytv.webapiandroidtest.R;
import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<UserViewModel> users;

    public UsersAdapter(List<UserViewModel> users) {
        this.users = users;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        TextView userFullName;
        TextView userEmail;

        public UserViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycleViewFreinds);
            userFullName = itemView.findViewById(R.id.friendFullName);
            userEmail = itemView.findViewById(R.id.friendEmail);
        }
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_item, parent, false);
        UserViewHolder vh = new UserViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(UsersAdapter.UserViewHolder holder, int position) {
        holder.userFullName.setText(users.get(position).getFullName());
        holder.userEmail.setText(users.get(position).getEmail());
    }


    @Override
    public int getItemCount() {
        return users.size();
    }
}
