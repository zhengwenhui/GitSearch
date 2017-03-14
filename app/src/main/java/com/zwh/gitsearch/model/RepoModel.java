package com.zwh.gitsearch.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zwh.gitsearch.bean.LanguageBean;
import com.zwh.gitsearch.bean.ReposBean;
import com.zwh.gitsearch.utils.PostClient;
import com.zwh.gitsearch.utils.StringHttpResponseHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengwenhui on 14/03/2017.
 */

public class RepoModel implements IRepoModel {
    private static final String SEARCH_REPOS = "https://api.github.com/users/%s/repos?client_id=dfa17476b53e45502739&client_secret=e173504caed51047c2d59e7c4daf0216003f526e";
    private OnLoadRepoInfoListener mOnLoadRepoInfoListener;
    private String mUserName;

    public RepoModel(OnLoadRepoInfoListener result) {
        mOnLoadRepoInfoListener = result;
    }

    @Override
    public void loadRepos(String userName) {
        this.mUserName = userName;
        PostClient.get(String.format(SEARCH_REPOS, userName), new StringHttpResponseHandler() {
            @Override
            public void onFailure(String responseString, Throwable throwable) {
                mOnLoadRepoInfoListener.onFailure(new LanguageBean(mUserName));
                throwable.printStackTrace();
            }

            @Override
            public void onSuccess(String responseString) {
                parseReposInfo(responseString);
            }
        });
    }

    private void parseReposInfo(String responseString) {
        ReposBean[] reposBeen = new Gson().fromJson(responseString, new TypeToken<ReposBean[]>() {
        }.getType());

        if (reposBeen == null || reposBeen.length == 0) {
            mOnLoadRepoInfoListener.onFailure(new LanguageBean(mUserName));
            return;
        }

        //统计使用语言的仓库个数
        Map<String, Integer> map = new HashMap<>();
        for (ReposBean repo : reposBeen) {
            if (!map.containsKey(repo.getLanguage())) {
                map.put(repo.getLanguage(), 1);
            } else {
                int count = map.get(repo.getLanguage());
                map.put(repo.getLanguage(), ++count);
            }
        }

        //按使用次数排序
        List<Map.Entry<String, Integer>> infoIds = new ArrayList<>(map.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });


        List<String> mostUsedLanguage = new ArrayList<>();
        mostUsedLanguage.add(infoIds.get(0).getKey());

        int value = infoIds.get(0).getValue();
        for (int i = 1; i < infoIds.size(); i++) {
            if ((infoIds.get(i).getValue().equals(value))) {
                mostUsedLanguage.add(infoIds.get(i).getKey());
            } else {
                break;
            }
        }

        //返回结果
        LanguageBean languageBean = new LanguageBean(mUserName);
        languageBean.setLanguage(map.keySet().toString());
        languageBean.setMostUsedLanguage(mostUsedLanguage.toString());
        mOnLoadRepoInfoListener.onSuccess(languageBean);
    }
}
