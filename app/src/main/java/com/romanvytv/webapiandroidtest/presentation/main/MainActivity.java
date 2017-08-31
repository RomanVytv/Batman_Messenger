package com.romanvytv.webapiandroidtest.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.romanvytv.webapiandroidtest.R;
import com.romanvytv.webapiandroidtest.models.ChatViewModel;
import com.romanvytv.webapiandroidtest.models.UserViewModel;
import com.romanvytv.webapiandroidtest.presentation.chat.ChatActivity;
import com.romanvytv.webapiandroidtest.presentation.friends.FriendsActivity;
import com.romanvytv.webapiandroidtest.presentation.login.LoginActivity;
import com.romanvytv.webapiandroidtest.presentation.search.SearchActivity;

import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements MainView, NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mainToolbar;
    private DrawerLayout drawer;
    private MainPresenter presenter;
    private String token;
    private NavigationView navigationView;
    private View headerView;
    private TextView navigationDrawerFullName;
    private TextView navigationDrawerEmail;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager  layoutManager;

    private TextView tvChatsNotFound;

    List<ChatViewModel> chats;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Hawk.init(getApplicationContext()).build();
        presenter = new MainPresenterImpl(this);
        mainToolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(mainToolbar);

        tvChatsNotFound = (TextView) findViewById(R.id.tvChatsNotFound);

        initNavigationDrawer();
        loadCurrentUser();
        loadUsersChats(token);


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUsersChats(token);
    }

    @Override
    public void onBackPressed() {
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view main_chats_item clicks here.
        int id = item.getItemId();

        if (id == R.id.friends) {
            startActivity(new Intent(this, FriendsActivity.class));
        } else if (id == R.id.invite_friends) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_TEXT, "Hi, let's use BatMessenger!");
            startActivity(intent);
        } else if (id == R.id.find_friends) {
            startActivity(new Intent(this, SearchActivity.class));
        } else if (id == R.id.settings) {
            //TODO: create settings activity
        } else if (id == R.id.log_out) {
            if(Hawk.contains("authToken")){
                Hawk.delete("authToken");
            }
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }

        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void loadCurrentUser() {

        if (getIntent().getStringExtra("TOKEN") != null) {
            token = getIntent().getExtras().get("TOKEN").toString();
        } else if(Hawk.contains("keepSignedIn") && Hawk.get("keepSignedIn").equals("false")){
            Hawk.delete("authToken");
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }else if (Hawk.contains("authToken")) {
            token = Hawk.get("authToken");
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        presenter.fillUserData(token);
    }

    @Override
    public void loadUsersChats(String token) {
        presenter.loadChats(token);
    }

    @Override
    public void showProgress() {
        getSupportActionBar().setTitle("Networking...");
    }

    @Override
    public void hideProgress() {
        getSupportActionBar().setTitle(R.string.app_name);
    }

    @Override
    public void fillCurrentUser(UserViewModel user) {
        navigationDrawerEmail.setText(user.getEmail());
        navigationDrawerFullName.setText(user.getName() + " " + user.getSurname());
    }

    @Override
    public void fillChats(List<ChatViewModel> chats){
        if(chats.isEmpty()){
            tvChatsNotFound.setVisibility(View.VISIBLE);
        }else{
            this.chats = chats;
            initRecycleView();
        }
    }

    @Override
    public void showError(String text) {
        //TODO: comment next line for presentation
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
        Log.i("RvError",text);
    }

    private void initRecycleView(){
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewMain);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        adapter =  new ChatsAdapter(chats, new ChatsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ChatViewModel item) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                intent.putExtra("chatId", item.getId());
                intent.putExtra("invokeby","main");
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

    }

    private void initNavigationDrawer() {
        // Enable status bar translucency (requires API 19)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        navigationDrawerFullName =  headerView.findViewById(R.id.nav_draw_full_name);
        navigationDrawerEmail =  headerView.findViewById(R.id.nav_draw_email);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                mainToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }
}