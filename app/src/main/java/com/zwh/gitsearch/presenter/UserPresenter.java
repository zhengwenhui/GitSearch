package com.zwh.gitsearch.presenter;

import com.zwh.gitsearch.bean.UsersBean;
import com.zwh.gitsearch.model.IUserModel;
import com.zwh.gitsearch.model.OnLoadUserInfoListener;
import com.zwh.gitsearch.model.UserModel;
import com.zwh.gitsearch.view.IUserView;

/**
 * Created by zhengwenhui on 14/03/2017.
 */

public class UserPresenter implements OnLoadUserInfoListener {
    private IUserModel mUserModel;
    private IUserView mUserView;

    public UserPresenter(IUserView view) {
        mUserView = view;
        mUserModel = new UserModel(this);
    }

    public void loadUser(String userName) {
        mUserModel.loadUsers(userName);
    }


    @Override
    public void onSuccess(UsersBean usersBean) {
        mUserView.setUser(usersBean);
    }

    @Override
    public void onFailure() {
        mUserView.setUser(null);
    }
}
