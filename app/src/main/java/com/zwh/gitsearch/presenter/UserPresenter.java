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
    private IUserModel userModel;
    private IUserView userView;

    public UserPresenter(IUserView view) {
        userView = view;
        userModel = new UserModel(this);
    }

    public void loadUser(String userName) {
        userModel.load(userName);
    }

    @Override
    public void onSuccess(UsersBean usersBean) {
        userView.setUser(usersBean);
    }

    @Override
    public void onFailure() {
        userView.setUser(null);
    }
}
