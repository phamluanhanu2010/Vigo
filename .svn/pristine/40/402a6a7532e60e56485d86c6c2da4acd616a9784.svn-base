/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/12/16 11:03 AM
 */

package com.strategy.intecom.vtc.vigo.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.interfaces.Callback;
import com.strategy.intecom.vtc.vigo.model.VtcModelMainTravel;
import com.strategy.intecom.vtc.vigo.utils.Utils;


/**
 * Created by Mr. Ha on 6/1/16.
 *
 * @author Mr. Ha
 */
@SuppressLint("ValidFragment")
public final class FMPlaceSlideImage extends Fragment {
    private VtcModelMainTravel mbModelImageBanner;
    private Activity context;

    private int width = 0;
    private int height = 0;

    private Callback callback;

    public FMPlaceSlideImage() {

    }

    public FMPlaceSlideImage(Callback callback, Activity context, VtcModelMainTravel mbModelImageBanner, int width, int height) {
        this.mbModelImageBanner = mbModelImageBanner;
        this.context = context;
        this.callback = callback;
//
//        int[] screen = Utils.getSizeScreen(context, "FMPlaceSlideImage");
//
//        int offset = screen[0];
//
//        this.width = offset;
//
//        offset = this.width / 16;
//
//        this.height = offset * 9;

        this.width = width;
        this.height = height;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.tmp_sliding_image_item, container, false);

        final ImageView image = (ImageView) viewRoot.findViewById(R.id.img_icon);

        if (context != null && mbModelImageBanner != null) {

//            ImageLoadAsync loadAsync = new ImageLoadAsync(context, image, width, height);
//            loadAsync.executeOnExecutor(MediaAsync.THREAD_POOL_EXECUTOR, mbModelImageBanner.getBackgroundResource());

            image.setImageResource(Utils.initGetResource(context, mbModelImageBanner.getBackgroundResource()));

            RelativeLayout.LayoutParams tv_header_params = new RelativeLayout.LayoutParams(width, height);
            image.setLayoutParams(tv_header_params);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callback != null) {
                        callback.onHandlerCallBack(-1, mbModelImageBanner);
                    }
                }
            });
        }

        return viewRoot;
    }
}
