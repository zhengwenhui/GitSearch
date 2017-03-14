package com.zwh.gitsearch.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zwh.gitsearch.bean.UsersBean;
import com.zwh.gitsearch.utils.PostClient;
import com.zwh.gitsearch.utils.StringHttpResponseHandler;

/**
 * Created by zhengwenhui on 14/03/2017.
 */

public class UserModel implements IUserModel {
    private static final String SEARCH_USERS = "https://api.github.com/search/users?q=%s";
    private OnLoadUserInfoListener mOnLoadUserInfoListener;

    public UserModel(OnLoadUserInfoListener result) {
        mOnLoadUserInfoListener = result;
    }

    @Override
    public void loadUsers(String userName) {

        PostClient.setUserAgent("Awesome-Octocat-App");
        PostClient.get(String.format(SEARCH_USERS, userName), new StringHttpResponseHandler() {
            @Override
            public void onFailure(String responseString, Throwable throwable) {
                mOnLoadUserInfoListener.onFailure();
            }

            @Override
            public void onSuccess(String responseString) {
                parseUsersInfo(responseString);
            }
        });
    }

    private void parseUsersInfo(String responseString) {
        UsersBean usersBean = new Gson().fromJson(responseString, new TypeToken<UsersBean>() {
        }.getType());
        mOnLoadUserInfoListener.onSuccess(usersBean);
    }
}
