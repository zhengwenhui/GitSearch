package com.zwh.gitsearch.model;

import com.zwh.gitsearch.bean.LanguageBean;

/**
 * Created by zhengwenhui on 14/03/2017.
 */

public interface OnLoadRepoInfoListener {
    void onSuccess(LanguageBean languageBean);

    void onFailure(LanguageBean languageBean);
}
