package com.zwh.gitsearch.model;

import com.zwh.gitsearch.bean.UsersBean;

/**
 * Created by zhengwenhui on 14/03/2017.
 */

public interface IUserModel {
    void load(String userName);
    void save(UsersBean userBean);
}
