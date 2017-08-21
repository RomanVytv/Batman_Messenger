package com.romanvytv.webapiandroidtest.presentation.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.romanvytv.webapiandroidtest.R;
import com.romanvytv.webapiandroidtest.presentation.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity implements ChatView {

    @BindView(R.id.chatToolbar)
    Toolbar chatToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar(){
        setSupportActionBar(chatToolbar);
        chatToolbar.setTitleTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorTeal50));
        chatToolbar.setNavigationIcon(R.drawable.ic_arrow_back_teal_50_18dp);
        chatToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChatActivity.this,MainActivity.class));
                finish();
            }
        });
        getSupportActionBar().setTitle(getIntent().getStringExtra("chatTitle"));
    }
}
