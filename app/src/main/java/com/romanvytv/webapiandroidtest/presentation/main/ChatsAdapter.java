package com.romanvytv.webapiandroidtest.presentation.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.romanvytv.webapiandroidtest.R;
import com.romanvytv.webapiandroidtest.models.ChatViewModel;

import java.util.List;


public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ChatViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(ChatViewModel item);
    }

    private List<ChatViewModel> chats;
    private final OnItemClickListener listener;


    public ChatsAdapter(List<ChatViewModel> chats, OnItemClickListener listener) {
        this.chats = chats;
        this.listener = listener;
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        TextView chatTitle;

        public ChatViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycleViewMain);
            chatTitle = itemView.findViewById(R.id.chatTitle);
        }

        public void bind( final ChatViewModel item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    @Override
    public ChatsAdapter.ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_chats_item, parent, false);
        ChatViewHolder vh = new ChatViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, final int position) {
        holder.chatTitle.setText(chats.get(position).getName());
        holder.bind(chats.get(position), listener);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }


    }

