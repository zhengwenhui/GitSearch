package com.zwh.gitsearch.presenter;

import com.zwh.gitsearch.bean.LanguageBean;
import com.zwh.gitsearch.model.OnLoadRepoInfoListener;
import com.zwh.gitsearch.model.RepoModel;
import com.zwh.gitsearch.view.IRepoView;

/**
 * Created by zhengwenhui on 14/03/2017.
 */

public class RepoPresenter implements OnLoadRepoInfoListener {
    private IRepoView mRepoView;

    public RepoPresenter(IRepoView view) {
        mRepoView = view;
    }

    public void loadRepos(String userName) {
        new RepoModel(this).loadRepos(userName);
    }

    @Override
    public void onSuccess(LanguageBean languageBean) {
        mRepoView.setRepo(languageBean);
    }

    @Override
    public void onFailure(LanguageBean languageBean) {
        mRepoView.setRepo(languageBean);
    }
}
