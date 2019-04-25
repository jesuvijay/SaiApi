package com.example.saiapi.utils.preference;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

final class SharedPrefs {

    private static final String PREFERNCE_NAME = "com.saiapi.SHARED_PREFS";
    private static final int PREFERENCES_MODE = Context.MODE_PRIVATE;

    private final SharedPreferences sharedPrefs;

    public SharedPrefs(@NonNull Context context) {
        sharedPrefs = context.getApplicationContext().getSharedPreferences(PREFERNCE_NAME, PREFERENCES_MODE);
    }

    private SharedPreferences.Editor getEditor() {
        return sharedPrefs.edit();
    }

    int get(@NonNull String key, int defaultValue) {
        return sharedPrefs.getInt(key, defaultValue);
    }


    void put(String key, int defaultValue) {

        getEditor().putInt(key, defaultValue).apply();
    }


    String get(@NonNull String key, String defaultValue) {
        return sharedPrefs.getString(key, defaultValue);
    }

    void put(String key, String defaultValue) {
        getEditor().putString(key, defaultValue).apply();
    }


    boolean get(@NonNull String key, boolean defaultValue) {
        return sharedPrefs.getBoolean(key, defaultValue);
    }

    void put(String key, boolean defaultValue) {

        getEditor().putBoolean(key, defaultValue).apply();
    }


    boolean contains(String mArgs){
        return sharedPrefs.contains(mArgs);
    }


}
