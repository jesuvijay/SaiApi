package com.example.saiapi.utils.preference;

import android.content.Context;

import androidx.annotation.NonNull;

public class Prefs {

    private static SharedPrefs sharedPrefs;


    private Prefs() {
    }

    // singleton class
    public static void init(@NonNull Context context) {
        if (sharedPrefs == null) {
            synchronized (Prefs.class) {
                if (sharedPrefs == null)
                    sharedPrefs = new SharedPrefs(context);

            }
        }

    }

    @NonNull
    private static SharedPrefs getSharedPrefs() {
        if (sharedPrefs == null)
            throw new RuntimeException("Prefs has not been instantiated. Call init() with context");
        return sharedPrefs;
    }


    // for int preference
    public static int getIntPref(String key, int defaultValue) {
        if (checkKey(key))
            return getSharedPrefs().get(key, defaultValue);
        else
            return 0;
    }

    public static void putIntPref(String key, int defaultValue) {

        getSharedPrefs().put(key, defaultValue);
    }


    // for string reference
    public static String getStrPref(String key, String defaultValue) {
        if (checkKey(key))
            return getSharedPrefs().get(key, defaultValue);
        else
            return "";
    }

    public static void putStrPref(String key, String defaultValue) {
        getSharedPrefs().put(key, defaultValue);
    }

    //    for boolean
    public static boolean getBoolPref(String key, boolean defaultValue) {
        if (checkKey(key))
            return getSharedPrefs().get(key, defaultValue);
        else return false;
    }

    public static void putBoolPref(String key, boolean defaultValue) {
        getSharedPrefs().put(key, defaultValue);

    }
    // for check key
    public static boolean checkKey(String mArgs) {
        return getSharedPrefs().contains(mArgs);
    }

}
