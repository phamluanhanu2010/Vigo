/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/12/16 11:15 AM
 */

package com.strategy.intecom.vtc.vigo.view.base;

import android.support.v4.app.Fragment;

import com.strategy.intecom.vtc.vigo.interfaces.Callback;
import com.strategy.intecom.vtc.vigo.utils.Const;
import com.strategy.intecom.vtc.vigo.view.fragment.FMArchitectureDetails;
import com.strategy.intecom.vtc.vigo.view.fragment.FMLstInTravel;
import com.strategy.intecom.vtc.vigo.view.fragment.FMPositionDetail;
import com.strategy.intecom.vtc.vigo.view.fragment.FMPositionMap;

/**
 * Create by Mr. Ha on 5/16/16.
 *
 * @author Mr. Ha
 */
public class VtcFragmentFactory {

    /**
     * All class in app will int below with key_value and return Fragment by
     * name class fragment and CallBack
     **/
    public static Fragment getFragmentWithCallbackByKey(final int key, Callback callback) {

        switch (key) {

            default:
                return null;
        }

    }

    /**
     * All class in app will int below with key_value and return Fragment by
     * name class fragment
     */
    public static Fragment getFragmentByKey(final int key) {

        switch (key) {
            case Const.UI_LIST_IN_TRAVEL:
                return new FMLstInTravel();
            case Const.UI_POSITION_DETAIL:
                return new FMPositionDetail();
            case Const.UI_POSITION_MAP:
                return new FMPositionMap();
            case Const.UI_ARCHITECTURE_DETAILS:
                return new FMArchitectureDetails();
            default:
                return null;
        }
    }
}
