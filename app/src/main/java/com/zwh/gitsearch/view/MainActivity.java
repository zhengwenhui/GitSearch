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

    private EditText mUserNameEditText;
    private RecyclerView mUsersRecyclerView;

    private UserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mUserPresenter = new UserPresenter(this);
    }

    private void initView() {
        mUserNameEditText = (EditText) findViewById(R.id.user_name);
        mUsersRecyclerView = (RecyclerView) findViewById(R.id.user_info);
        mUsersRecyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void setUser(UsersBean user) {
        if (user == null) {
            Toast.makeText(this, R.string.find_nothing, Toast.LENGTH_LONG).show();
        }

        UserAdapter userAdapter = new UserAdapter(this, user);
        mUsersRecyclerView.setAdapter(userAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                mUserPresenter.loadUser(mUserNameEditText.getText().toString());
                mUsersRecyclerView.requestFocus();
                break;
        }
    }
}
