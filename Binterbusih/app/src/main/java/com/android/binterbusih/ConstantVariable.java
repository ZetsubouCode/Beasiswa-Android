package com.android.binterbusih;

public class ConstantVariable {
    public static final String ROOT_URL = "http://192.168.18.9";

    private static String FCM_TOKEN = "";

    public void setFCMToken(String token){
        this.FCM_TOKEN = token;
    }
    public String getFCMToken(){
        return this.FCM_TOKEN;
    }
    public String getRootUrl(){
        return this.ROOT_URL;
    }

}
