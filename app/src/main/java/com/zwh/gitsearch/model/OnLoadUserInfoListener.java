package com.zwh.gitsearch.model;

import com.zwh.gitsearch.bean.UsersBean;

/**
 * Created by zhengwenhui on 14/03/2017.
 */

public interface OnLoadUserInfoListener {
    void onSuccess(UsersBean usersBean);
    void onFailure();
}
