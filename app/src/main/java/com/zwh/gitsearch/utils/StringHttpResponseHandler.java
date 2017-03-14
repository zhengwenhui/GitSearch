package com.zwh.gitsearch.utils;

import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zhengwenhui on 14/03/2017.
 */

public abstract class StringHttpResponseHandler extends TextHttpResponseHandler {

    public abstract void onFailure(String responseString, Throwable throwable);

    /**
     * Called when request succeeds
     *
     * @param responseString string response of given charset
     */
    public abstract void onSuccess(String responseString);

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        onFailure(responseString, throwable);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
        onSuccess(responseString);
    }
}

