package com.romanvytv.webapiandroidtest.presentation.friends;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.romanvytv.webapiandroidtest.R;
import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;


public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(UserViewModel item);
    }


    private List<UserViewModel> friends;
    private final OnItemClickListener listener;

    public FriendsAdapter(List<UserViewModel> friends, OnItemClickListener listener) {
        this.friends = friends;
        this.listener = listener;
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        TextView friendFullName;
        TextView friendEmail;

        public FriendViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycleViewFreinds);
            friendFullName = itemView.findViewById(R.id.friendFullName);
            friendEmail = itemView.findViewById(R.id.friendEmail);
        }

        public void bind(final UserViewModel item, final FriendsAdapter.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_item, parent, false);
        FriendViewHolder vh = new FriendViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(FriendsAdapter.FriendViewHolder holder, int position) {
        holder.friendFullName.setText(friends.get(position).getFullName());
        holder.friendEmail.setText(friends.get(position).getEmail());
        holder.bind(friends.get(position), listener);
    }


    @Override
    public int getItemCount() {
        return friends.size();
    }
}
