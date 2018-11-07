package com.test.ahsan.app.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.Set;

/**
 * Created by ahsan on 17/09/2018.
 */

public class SharedPref implements SharedPreferences.Editor{

    private String PREF_NAME = "PREFERENCES";
    private static SharedPref sharedPref = null;
    private static volatile SharedPreferences sharedPreferences;
    private static volatile SharedPreferences.Editor editor;

    public static SharedPref getSharedPrefrence(Context context) {
        if (sharedPref == null) {
            sharedPref = new SharedPref(context);
        }
        return sharedPref;
    }

    @SuppressLint("CommitPrefEdits")
    private SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public int getInteger(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    @Override
    public SharedPreferences.Editor putString(String key, @Nullable String value) {
        editor.putString(key, value);
        return null;
    }

    @Override
    public SharedPreferences.Editor putStringSet(String key, @Nullable Set<String> values) {
        return this;
    }

    @Override
    public SharedPreferences.Editor putInt(String key, int value) {
        return this;
    }

    @Override
    public SharedPreferences.Editor putLong(String key, long value) {
        return this;
    }

    @Override
    public SharedPreferences.Editor putFloat(String key, float value) {
        return this;
    }

    @Override
    public SharedPreferences.Editor putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);

        return this;
    }

    @Override
    public SharedPreferences.Editor remove(String key) {
        editor.remove(key);
        return this;
    }

    @Override
    public SharedPreferences.Editor clear() {
        return this;
    }

    @Override
    public boolean commit() {
        return editor.commit();
    }

    @Override
    public void apply() {
        editor.apply();
    }

//    public static void insertStringIntoSharedPref(String key, String value) {
//        editor.putString(key, value);
//    }
//
//    public static void insertBooleanIntoSharedPref(String key, boolean value) {
//        editor.putBoolean(key, value);
//    }
//
//    public static void insertIntegerIntoSharedPref(String key, int value) {
//        editor.putInt(key, value);
//    }
//
//    public static String getStringValue(String key) {
//        if (sharedPreferences.contains(key)) {
//            return sharedPreferences.getString(key, null);
//        }
//        return null;
//    }
//
//    public static String getString(String key) {
//        return sharedPreferences.getString(key, null);
//    }
//
//    public static Boolean getBoolean(String key) {
//        return sharedPreferences.getBoolean(key, false);
//    }
//
//    public static int getInteger(String key) {
//        return sharedPreferences.getInt(key, 0);
//    }

}
