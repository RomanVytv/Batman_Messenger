package com.romanvytv.webapiandroidtest.presentation.chat;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.romanvytv.webapiandroidtest.R;
import com.romanvytv.webapiandroidtest.models.MessageViewModel;
import com.romanvytv.webapiandroidtest.models.UserViewModel;
import com.romanvytv.webapiandroidtest.utils.DateUtil;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessegesViewHolder> {

    public static final int CURRENT = 0;
    public static final int OTHER = 1;

    private UserViewModel currentUser;
    private List<UserViewModel> members;
    private List<MessageViewModel> messages;

    public MessagesAdapter(UserViewModel currentUser, List<MessageViewModel> messages,List<UserViewModel> members) {
        this.currentUser = currentUser;
        this.messages = messages;
        this.members = members;
    }

    public class MessegesViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        TextView sender;
        TextView text;
        TextView time;

        public MessegesViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycleViewChat);
            sender = (TextView) itemView.findViewById(R.id.sender);
            text = (TextView) itemView.findViewById(R.id.text);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }

    @Override
    public MessegesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 0){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_self, parent, false);
            MessegesViewHolder vh = new MessegesViewHolder(v);
            return vh;
        }else{
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_other, parent, false);
            MessegesViewHolder vh = new MessegesViewHolder(v);
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(MessegesViewHolder holder, int position) {
        holder.sender.setText(getMemberById(messages.get(position).getUserId()).getFullName());
        holder.text.setText(messages.get(position).getText());
        holder.time.setText(DateUtil.parseTimeForChat(messages.get(position).getTime()));
    }

    private UserViewModel getMemberById(String userId) {
        UserViewModel member = null;
        for(int i = 0; i < members.size(); i++){
            if(members.get(i).getId().equals(userId)){
                member = members.get(i);
            }
        }
        return member;
    }

    @Override
    public int getItemViewType(int position) {
        MessageViewModel message = messages.get(position);

        if (message.getUserId().equals(currentUser.getId())) {
            return CURRENT;
        } else {
            return OTHER;
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

}
