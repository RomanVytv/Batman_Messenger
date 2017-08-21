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
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.romanvytv.webapiandroidtest.R;
import com.romanvytv.webapiandroidtest.models.ChatViewModel;
import com.romanvytv.webapiandroidtest.models.UserViewModel;
import com.romanvytv.webapiandroidtest.presentation.chat.ChatActivity;
import com.romanvytv.webapiandroidtest.presentation.login.LoginActivity;
import com.romanvytv.webapiandroidtest.utils.RecyclerItemClickListener;

import java.util.Arrays;
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

    List<ChatViewModel> chats = Arrays.asList(
            new ChatViewModel[]{
                    new ChatViewModel("131232", "Chat 1", "01.01.1234"),
                    new ChatViewModel("131232", "Chat 2", "01.01.1234"),
                    new ChatViewModel("131232", "Chat 3", "01.01.1234"),
                    new ChatViewModel("131232", "Chat 4", "01.01.1234"),
                    new ChatViewModel("131232", "Chat 5", "01.01.1234"),
                    new ChatViewModel("131232", "Chat 6", "01.01.1234"),
                    new ChatViewModel("131232", "Chat 7", "01.01.1234"),
                    new ChatViewModel("131232", "Chat 8", "01.01.1234"),
                    new ChatViewModel("131232", "Chat 9", "01.01.1234")});

    @Override
    //TODO: зробити status bar прозорим для Navdrawer'a
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Hawk.init(getApplicationContext()).build();
        presenter = new MainPresenterImpl(this);
        mainToolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(mainToolbar);

        initNavigationDrawer();

        initRecycleView();

        loadCurrentUser();
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.friends) {

        } else if (id == R.id.invite_friends) {
            //TODO: send SMS-invite
        } else if (id == R.id.find_friends) {

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
            token = getIntent().getStringExtra("TOKEN");
        } else if (Hawk.contains("authToken")) {
            token = Hawk.get("authToken");
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        presenter.fillUserData(token);
    }

    @Override
    public void loadUsersChats() {

    }

    @Override
    public void showProgress() {
        mainToolbar.setTitle("Networking...");
    }

    @Override
    public void hideProgress() {
        mainToolbar.setTitle(R.string.app_name);
    }

    @Override
    public void fillCurrentUser(UserViewModel user) {
        navigationDrawerEmail.setText(user.getEmail());
        navigationDrawerFullName.setText(user.getName() + " " + user.getSurname());
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
        adapter =  new ChatsAdapter(chats);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                        intent.putExtra("chatTitle",chats.get(position).getName());
                        intent.putExtra("chatId", chats.get(position).getId());
                        startActivity(intent);
                    }
                }));
    }

    private void initNavigationDrawer() {
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