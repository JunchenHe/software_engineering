package com.angine.www.zxingtest;

/**
 * Created by asus-pc on 2018/4/22.
 */

public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);

}