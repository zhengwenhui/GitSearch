package com.zwh.gitsearch.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by zhengwenhui on 14/03/2017.
 */

public class PostClient {
    private static AsyncHttpClient client = null;

    static {
        client = new AsyncHttpClient();
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        try {
            client.get(url, params, responseHandler);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static void get(String url, AsyncHttpResponseHandler responseHandler) {
        try {
            client.get(url, responseHandler);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static void setUserAgent(String userAgent) {
        try {
            client.setUserAgent(userAgent);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
