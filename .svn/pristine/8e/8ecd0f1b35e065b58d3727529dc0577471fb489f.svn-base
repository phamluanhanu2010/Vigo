/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/10/16 11:06 AM
 */

package com.strategy.intecom.vtc.vigo.view.fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.Voice;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.model.VtcModelPosition;
import com.strategy.intecom.vtc.vigo.utils.Const;
import com.strategy.intecom.vtc.vigo.utils.Utils;
import com.strategy.intecom.vtc.vigo.view.base.AppCore;


/**
 * Created by Mr. Ha on 5/19/16.
 *
 * @author Mr. Ha
 */

@SuppressLint("ValidFragment")
public class FMPositionMap extends AppCore {

    private VtcModelPosition vtcModelPosition;

    private ImageView img_thumb;

    private TextView txt_title;
    private ImageView btn_back;
    private ImageView img_camera;

    public FMPositionMap() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        savedInstanceState = getArguments();
        if (savedInstanceState != null) {
            vtcModelPosition = savedInstanceState.getParcelable(Const.BUNDLE_TYPE_DATA_TYPE);
        }
        return inflater.inflate(R.layout.ui_position_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Utils.hideKeyboard(getmActivity());
        initController(view);
    }

    private void initController(View view) {

        img_thumb = (ImageView) view.findViewById(R.id.img_thumb);

        txt_title = (TextView) view.findViewById(R.id.txt_title);

        btn_back = (ImageView) view.findViewById(R.id.btn_back);

        img_camera = (ImageView) view.findViewById(R.id.img_camera);

        initData();
    }

    private void initData() {

        txt_title.setText(vtcModelPosition.getTitle());
        txt_title.setSelected(true);

//        new LoadImageRes().execute();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                getmActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        img_thumb.setImageResource(Utils.initGetResource(getmActivity(), vtcModelPosition.getMapResourceFull()));
                    }
                });
            }
        }, 300);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmdBack();
            }
        });

        img_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Utils.hideKeyboard(getmActivity());
    }

    class LoadImageRes extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            getmActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    img_thumb.setImageResource(Utils.initGetResource(getmActivity(), vtcModelPosition.getMapResourceFull()));
                }
            });
            return null;
        }
    }

}
