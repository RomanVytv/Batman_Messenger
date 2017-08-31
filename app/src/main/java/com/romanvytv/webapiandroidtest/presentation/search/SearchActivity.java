package com.romanvytv.webapiandroidtest.presentation.search;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.romanvytv.webapiandroidtest.R;
import com.romanvytv.webapiandroidtest.models.UserViewModel;
import com.romanvytv.webapiandroidtest.presentation.friends.FriendsAdapter;
import com.romanvytv.webapiandroidtest.presentation.main.MainActivity;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements ISearchView {

    @BindView(R.id.searchToolbar) Toolbar searchToolbar;
    @BindView(R.id.progressSearch) ProgressBar progressBar;
    @BindView(R.id.spinner) Spinner spinner;
    @BindView(R.id.tvUsersNotFound) TextView tvUsersNotFound;

    private SearchPresenter presenter;
    private List<UserViewModel> users;

    private String selectedItem;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(searchToolbar);
        presenter = new SearchPresenterImpl(this);
        initToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                selectedItem = spinner.getSelectedItem().toString();
                find(Hawk.get("authToken").toString(),selectedItem, query);
                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });
        return true;
    }

    @Override
    public void find(String token, String type, String value) {
        presenter.find(token,type,value);
    }

    @Override
    public void fillUsers(List<UserViewModel> users) {
        if(users == null || users.size() < 1){
            tvUsersNotFound.setVisibility(View.VISIBLE);
        }else{
            this.users = users;
            initRecycleView();
        }
    }

    private void initToolbar() {
        getSupportActionBar().setTitle("Search");
        searchToolbar.setTitleTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorTeal50));
        searchToolbar.setNavigationIcon(R.drawable.ic_arrow_back_teal_50_18dp);
        searchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initRecycleView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewSearch);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        adapter =  new FriendsAdapter(users, new FriendsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserViewModel item) {
                addFriendDialog(item.getId());
            }
        });
        recyclerView.setAdapter(adapter);

        /*recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        addFriendDialog(position);
                    }
                }))*/
    }

    @Override
    public void addFriend(String chatName, String userId) {
        presenter.addFriend(
                Hawk.get("authToken").toString(),
                chatName,
                userId);
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
        if(users == null || users.size() < 1){
            tvUsersNotFound.setVisibility(View.VISIBLE);
        }
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void addFriendDialog(final String userId){
        new AlertDialog.Builder(SearchActivity.this)
                .setTitle("Add friend")
                .setMessage("Do you want to add this user to friends and create chat?")
                .setNegativeButton(android.R.string.cancel, null) // dismisses by default
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        createChatDialog(userId);
                    }
                })
                .create()
                .show();
    }

    private void createChatDialog(final String userId) {

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        new AlertDialog.Builder(SearchActivity.this)
                .setTitle("Please, enter chat title.")
                .setView(input)
                .setNegativeButton(android.R.string.cancel, null) // dismisses by default
                .setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addFriend(input.getText().toString(), userId);
                    }
                })
                .create()
                .show();
    }

}
