/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/12/16 1:04 PM
 */

package com.strategy.intecom.vtc.vigo.adt;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.strategy.intecom.vtc.vigo.interfaces.Callback;
import com.strategy.intecom.vtc.vigo.model.VtcModelMainTravel;
import com.strategy.intecom.vtc.vigo.view.fragment.FMPlaceSlideImage;
import com.viewpagerindicator.IconPagerAdapter;

import java.util.List;

/**
 * Created by Mr. Ha on 6/1/16.
 *
 * @author Mr. Ha
 */
public class AdtPlaceSlidesImage extends FragmentPagerAdapter implements IconPagerAdapter {

    private Activity context;

    private List<VtcModelMainTravel> lstModelBanner;

    private Callback callback;

    private int width = 0;
    private int height = 0;

    public AdtPlaceSlidesImage(Callback callback, FragmentManager fm, List<VtcModelMainTravel> lstModelBanner, Activity context, int width, int height) {
        super(fm);
        this.lstModelBanner = lstModelBanner;
        this.context = context;
        this.callback = callback;
        this.width = width;
        this.height = height;
    }

    @Override
    public Fragment getItem(int position) {
        return new FMPlaceSlideImage(callback, context, lstModelBanner.get(position), width, height);
    }

//    Callback callbackInst = new Callback() {
//        @Override
//        public <T> void onHandlerCallBack(int key, T... t) {
//            if (callback != null && t != null && t.length > 0 && t[0] instanceof MBModelChannel.Items) {
//                callback.onHandlerCallBack(-1, t[0]);
//            }
//        }
//    };

    @Override
    public int getCount() {
        if (lstModelBanner == null) {
            return 0;
        }
        return lstModelBanner.size();
    }

    @Override
    public int getIconResId(int index) {
        return index;
    }

}