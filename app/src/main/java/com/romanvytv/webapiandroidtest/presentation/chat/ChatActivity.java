package com.romanvytv.webapiandroidtest.presentation.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.romanvytv.webapiandroidtest.R;
import com.romanvytv.webapiandroidtest.models.ChatViewModel;
import com.romanvytv.webapiandroidtest.models.MessageViewModel;
import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity implements ChatView {

    @BindView(R.id.chatToolbar) Toolbar chatToolbar;
    @BindView(R.id.editMessege) EditText editMessage;
    @BindView(R.id.progressChat) ProgressBar progressBar;

    private ChatPresenterImpl presenter;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;

    private ChatViewModel chat;
    private UserViewModel currentUser;
    private List<UserViewModel> members;
    private List<MessageViewModel> messages;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        Hawk.init(getApplicationContext()).build();
        presenter = new ChatPresenterImpl(this);

        initToolbar();
        loadChat();
    }

    @Override
    public void loadCurrentUser() {
        presenter.loadCurrentUser(Hawk.get("authToken").toString());
    }

    @Override
    public void loadMembers() {
        presenter.loadMembers(Hawk.get("authToken").toString(),chat.getId());
    }

    @Override
    public void loadChat(){
        Intent intent = getIntent();
        if(intent.getStringExtra("invokeby").equals("main")){

            presenter.loadChatById(Hawk.get("authToken").toString(),intent.getIntExtra("chatId",-1));

        } else if(intent.getStringExtra("invokeby").equals("user")) {

            presenter.loadChatByFriendId(Hawk.get("authToken").toString(),intent.getStringExtra("friendId"));
        }
    }

    @Override
    public void loadMessages() {
        presenter.openChatById(Hawk.get("authToken").toString(),chat.getId());
    }

    @Override
    public void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewChat);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter =  new MessagesAdapter(currentUser,messages,members);
        recyclerView.setAdapter(adapter);
        layoutManager.scrollToPosition(messages.size() - 1);
    }

    private void initToolbar(){
        setSupportActionBar(chatToolbar);
        chatToolbar.setTitleTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorTeal50));
        chatToolbar.setNavigationIcon(R.drawable.ic_arrow_back_teal_50_18dp);
        chatToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setTitle(getIntent().getStringExtra("Networking..."));
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void showError(String text) {
        hideProgress();
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }

    @Override
    public void initChat(ChatViewModel chat) {
        this.chat = chat;
        getSupportActionBar().setTitle(chat.getName());
        loadMembers();
    }

    @Override
    public void initMembers(List<UserViewModel> users) {
        this.members = users;
        loadCurrentUser();
    }

    @Override
    public void initCurrentUser(UserViewModel user) {
        this.currentUser = user;
        if(members.size() < 3) {
            getSupportActionBar().setTitle(getAnotherMember().getFullName());
        }
        loadMessages();
    }

    @Override
    public void initMessages(List<MessageViewModel> messages) {
        this.messages = messages;
        initRecyclerView();
    }

    @OnClick(R.id.send)
    @Override
    public void sendMessage() {
        String message = editMessage.getText().toString();
        if(!message.isEmpty()){
            presenter.sendMessage(Hawk.get("authToken").toString(),chat.getId(),message);
            editMessage.setText("");
        }
    }

    private UserViewModel getAnotherMember(){
        UserViewModel member = null;
        for(int i = 0; i < members.size(); i++){
            if(!members.get(i).getId().equals(currentUser.getId()) && members.size() < 3){
                member = members.get(i);
            }
        }
        return member;
    }

}
