/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/11/16 10:09 AM
 */

package com.strategy.intecom.vtc.vigo.view.base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.strategy.intecom.vtc.vigo.R;

/**
 * Created by Mr. Ha on 5/16/16.
 * @author Mr. Ha
 */
public class Controller extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
//        DeployGate.install(this);
//        FacebookSdk.sdkInitialize(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private Tracker mTracker;

    /**
     * Gets the default {@link Tracker} for this {@link MultiDexApplication}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }
}
