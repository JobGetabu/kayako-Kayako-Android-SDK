package com.kayako.sample.store;

import android.content.Context;
import android.content.SharedPreferences;

import com.kayako.sdk.android.k5.core.Kayako;

public class Store {

    private static Store ourInstance = new Store();

    public static Store getInstance() {
        return ourInstance;
    }

    private static SharedPreferences sharedPreferences;
    private static final String ARG_HELP_CENTER_URL = "helpcenterurl";

    private Store() {
        sharedPreferences = Kayako.getApplicationContext().getSharedPreferences("sample-app-store", Context.MODE_PRIVATE);
    }

    public void setHelpCenterUrl(String helpCenterUrl) {
        sharedPreferences.edit().putString(ARG_HELP_CENTER_URL, helpCenterUrl).apply();
    }

    public String getHelpCenterUrl() {
        return sharedPreferences.getString(ARG_HELP_CENTER_URL, "https://support.kayako.com");
    }

}
