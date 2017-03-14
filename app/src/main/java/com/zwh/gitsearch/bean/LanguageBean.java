package com.zwh.gitsearch.bean;

/**
 * Created by zhengwenhui on 14/03/2017.
 */

public class LanguageBean {
    private String login;
    private String language;
    private String mostUsedLanguage;

    public LanguageBean(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMostUsedLanguage() {
        return mostUsedLanguage;
    }

    public void setMostUsedLanguage(String mostUsedLanguage) {
        this.mostUsedLanguage = mostUsedLanguage;
    }
}
