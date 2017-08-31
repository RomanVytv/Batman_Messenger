package com.romanvytv.webapiandroidtest.presentation.friends;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.romanvytv.webapiandroidtest.R;
import com.romanvytv.webapiandroidtest.models.UserViewModel;
import com.romanvytv.webapiandroidtest.presentation.user.UserActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FriendsActivity extends AppCompatActivity implements FriendsView {

    @BindView(R.id.friendsToolbar) Toolbar friendsToolbar;
    @BindView(R.id.progressFriends) ProgressBar progressBar;
    @BindView(R.id.tvFriendsNotFound) TextView tvFriendsNotFound;

    private FriendsPresenter presenter;
    private List<UserViewModel> friends;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager  layoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ButterKnife.bind(this);
        Hawk.init(getApplicationContext()).build();
        setSupportActionBar(friendsToolbar);
        presenter = new FriendsPresenterImpl(this);
        initToolbar();

        loadFriends(Hawk.get("authToken").toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_friends_toolbar_menu, menu);
        return true;
    }

    private void initToolbar() {
        getSupportActionBar().setTitle("Friends");
        friendsToolbar.setTitleTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorTeal50));
        friendsToolbar.setNavigationIcon(R.drawable.ic_arrow_back_teal_50_18dp);
        friendsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.find_friends:
                //TODO:
                return true;

            case R.id.invite_friends:
                //TODO:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
    public void loadFriends(String token) {
        presenter.loadFriends(token);
    }

    @Override
    public void fillFriends(List<UserViewModel> friends) {
        if(friends == null || friends.size() < 1){
            tvFriendsNotFound.setVisibility(View.VISIBLE);
        }else{
            this.friends = friends;
            initRecycleView();
        }
    }

    private void initRecycleView(){
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewFreinds);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        adapter =  new FriendsAdapter(friends, new FriendsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserViewModel item) {
                Intent intent = new Intent(FriendsActivity.this, UserActivity.class);
                intent.putExtra("userId",item.getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

    }
}
