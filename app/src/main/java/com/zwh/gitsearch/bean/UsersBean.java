package com.zwh.gitsearch.bean;

import java.util.List;

/**
 * Created by zhengwenhui on 14/03/2017.
 */

public class UsersBean {
    private int total_count;
    private boolean incomplete_results;
    private List<ItemsEntity> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<ItemsEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemsEntity> items) {
        this.items = items;
    }

    public static class ItemsEntity {

        private String login;
        private String avatar_url;
        private String repos_url;
        private LanguageBean languageBean;

        public LanguageBean getLanguageBean() {
            return languageBean;
        }

        public void setLanguageBean(LanguageBean languageBean) {
            this.languageBean = languageBean;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getRepos_url() {
            return repos_url;
        }

        public void setRepos_url(String repos_url) {
            this.repos_url = repos_url;
        }
    }
}
