package com.zwh.gitsearch.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zwh.gitsearch.R;
import com.zwh.gitsearch.bean.UsersBean;
import com.zwh.gitsearch.presenter.UserPresenter;

public class MainActivity extends Activity implements IUserView, View.OnClickListener {

    private EditText userNameEditText;
    private RecyclerView usersRecyclerView;

    private UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        userPresenter = new UserPresenter(this);
    }

    private void initView() {
        userNameEditText = (EditText) findViewById(R.id.user_name);
        usersRecyclerView = (RecyclerView) findViewById(R.id.user_info);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public UsersBean getUser() {
        return null;
    }

    @Override
    public void setUser(UsersBean user) {
        if (user == null) {
            Toast.makeText(this, R.string.find_nothing, Toast.LENGTH_LONG).show();
        }

        UserAdapter userAdapter = new UserAdapter(this, user);
        usersRecyclerView.setAdapter(userAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                userPresenter.loadUser(userNameEditText.getText().toString());
                usersRecyclerView.requestFocus();
                break;
        }
    }
}
