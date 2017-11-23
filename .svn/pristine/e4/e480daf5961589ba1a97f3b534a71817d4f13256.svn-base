/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/11/16 8:09 AM
 */

package com.strategy.intecom.vtc.vigo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mr. Ha on 5/16/16.
 *
 * @author Mr. Ha
 */
public class PreferenceUtil {

    private SharedPreferences IShare = null;

    public static final String DEVICE_ID = "device_id";

    public PreferenceUtil(Context context) {
        if (context != null)
            IShare = context.getSharedPreferences(context.getApplicationInfo().packageName, Context.MODE_PRIVATE);
    }

    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    ////// Remove Share Preferences
    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    public void PreferenceUtilRemove(Context context) {
        if (context != null)
            IShare = context.getSharedPreferences(context.getApplicationInfo().packageName, Context.MODE_PRIVATE);
        IShare.edit().clear().apply();
    }


    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    ////// Remove Data When Logout User
    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    public void RemoveDataWhenLogOut() {
        removeValue(DEVICE_ID);
    }

    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    ////// Get Set By Other Key
    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    public void setValueLong(String key, long val) {
        IShare.edit().putLong(key, val).apply();
    }

    public void setValueString(String key, String val) {
        IShare.edit().putString(key, val).apply();
    }

    public void setValueBoolean(String key, boolean val) {
        IShare.edit().putBoolean(key, val).apply();
    }

    public void setValueInteger(String key, int val) {
        IShare.edit().putInt(key, val).apply();
    }

    public int getValueInteger(String key) {
        return IShare.getInt(key, -1);
    }

    public long getValueLong(String key) {
        return IShare.getLong(key, -1);
    }

    public boolean getValueBoolean(String key) {
        return IShare.getBoolean(key, false);
    }

    public String getValueString(String key) {
        return IShare.getString(key, "").trim();
    }

    public void removeValue(String key) {
        IShare.edit().remove(key).apply();
    }

}
