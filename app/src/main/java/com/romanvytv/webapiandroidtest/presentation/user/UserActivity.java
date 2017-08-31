package com.romanvytv.webapiandroidtest.presentation.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.romanvytv.webapiandroidtest.R;
import com.romanvytv.webapiandroidtest.models.UserViewModel;
import com.romanvytv.webapiandroidtest.presentation.chat.ChatActivity;
import com.romanvytv.webapiandroidtest.utils.DateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UserActivity extends AppCompatActivity implements UserView {

    @BindView(R.id.userToolbar) Toolbar userToolbar;
    @BindView(R.id.progressUser) ProgressBar progressBar;
    @BindView(R.id.userImageView) View imageUserView;
    @BindView(R.id.tvUserFullName) TextView userFullName;
    @BindView(R.id.valueEmail) TextView tvEmail;
    @BindView(R.id.valuePhone) TextView tvPhone;
    @BindView(R.id.valueDate) TextView tvDate;
    @BindView(R.id.fabOpenChat) FloatingActionButton fabOpenChat;

    private UserPresenter presenter;
    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        setSupportActionBar(userToolbar);
        initToolbar();
        presenter = new UserPresenterImpl(this);
        userId = getIntent().getStringExtra("userId");
        loadUser(Hawk.get("authToken").toString(),userId);
    }

    private void initToolbar() {
        setSupportActionBar(userToolbar);
        userToolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        userToolbar.setTitleTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorTeal50));
        userToolbar.setNavigationIcon(R.drawable.ic_arrow_back_teal_50_18dp);
        userToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setTitle("");
        //imageUserView.setBackgroundResource(R.drawable.user_background);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    }

    @Override
    public void loadUser(String token, String userId) {
        presenter.loadUser(token,userId);
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
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }

    @Override
    public void fillUser(UserViewModel user) {
        userFullName.setText(user.getFullName());
        tvEmail.setText(user.getEmail());
        tvPhone.setText(user.getPhoneNumber());
        tvDate.setText(DateUtil.parseDate(user.getBornDate()));
    }

    @OnClick(R.id.fabOpenChat)
    @Override
    public void openChat() {
        Intent intent = new Intent(UserActivity.this, ChatActivity.class);
        intent.putExtra("invokeby","user");
        intent.putExtra("friendId",userId);
        startActivity(intent);
        finish();
    }

    // A method to find height of the status bar
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
