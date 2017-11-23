/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/10/16 11:06 AM
 */

package com.strategy.intecom.vtc.vigo.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.adt.AdtPositionAudio;
import com.strategy.intecom.vtc.vigo.armodule.ARActivity;
import com.strategy.intecom.vtc.vigo.model.VtcModelPosition;
import com.strategy.intecom.vtc.vigo.model.VtcModelPositionAudio;
import com.strategy.intecom.vtc.vigo.utils.Const;
import com.strategy.intecom.vtc.vigo.utils.Utils;
import com.strategy.intecom.vtc.vigo.view.base.AppCore;
import com.strategy.intecom.vtc.vigo.view.custom.listcontent.ListViewWrapContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr. Ha on 5/19/16.
 *
 * @author Mr. Ha
 */

@SuppressLint("ValidFragment")
public class FMPositionDetail extends AppCore implements View.OnClickListener,
        ViewPager.OnPageChangeListener {

    private VtcModelPosition vtcModelPosition;

    private ImageView img_thumb;

    private TextView txt_title;
    private ImageView btn_back;
    private ImageView btn_camera;
    private ImageView btn_map_places;

    private TextView txt_description;
    private TextView txt_price;
    private TextView txt_price_description;
    private TextView txt_info_audio_guide;

    private List<VtcModelPositionAudio> audioList;
    private ListViewWrapContent lst_audio_guild;

    private AdtPositionAudio adtPositionAudio;

    private int width = 0;
    private int height = 0;

    public FMPositionDetail() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        savedInstanceState = getArguments();
        if (savedInstanceState != null) {
            vtcModelPosition = savedInstanceState.getParcelable(Const.BUNDLE_TYPE_DATA_TYPE);
        }
        return inflater.inflate(R.layout.ui_position_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Utils.hideKeyboard(getmActivity());
        initController(view);
    }

    private void initController(View view) {

        initDumpData();

        int[] screen = Utils.getSizeScreen(getmActivity());

        int offset = screen[0];

        width = offset;

        offset = width / 16;

        height = offset * 9;

        img_thumb = (ImageView) view.findViewById(R.id.img_thumb);

        LinearLayout.LayoutParams tv_header_params = new LinearLayout.LayoutParams(width, height);
        img_thumb.setLayoutParams(tv_header_params);

        img_thumb.setImageResource(Utils.initGetResource(getmActivity(), vtcModelPosition.getMapResourceThumb()));

        txt_title = (TextView) view.findViewById(R.id.txt_title);

        btn_back = (ImageView) view.findViewById(R.id.btn_back);

        btn_camera = (ImageView) view.findViewById(R.id.btn_camera);

        btn_map_places = (ImageView) view.findViewById(R.id.btn_map_places);

        txt_description = (TextView) view.findViewById(R.id.txt_description);

        txt_price = (TextView) view.findViewById(R.id.txt_price);

        txt_price_description = (TextView) view.findViewById(R.id.txt_price_description);

        txt_info_audio_guide = (TextView) view.findViewById(R.id.txt_info_audio_guide);

        lst_audio_guild = (ListViewWrapContent) view.findViewById(R.id.lst_audio_guild);

        adtPositionAudio = new AdtPositionAudio(getmActivity(), screen);

        initData();
    }

    private void initData() {

        txt_title.setText(vtcModelPosition.getTitle());
        txt_description.setText(vtcModelPosition.getDescription());
        txt_price.setText(getmActivity().getResources().getString(R.string.title_currency_usd, vtcModelPosition.getPrice()));
        txt_title.setSelected(true);

        long countDuration = 0;
        int itemSize = audioList.size();
        int countArticles = 0;

        for (int i = 0; i < audioList.size(); i++) {
            VtcModelPositionAudio positionAudio = audioList.get(i);
            countDuration += positionAudio.getDuration();
        }

        txt_price_description.setText(getmActivity().getResources().getString(R.string.title_detail_guide_price_content, Utils.getTimeFormatSemple(countDuration * 1000)));

        txt_info_audio_guide.setText(getmActivity().getResources().getString(R.string.title_detail_guide_count_sound, String.valueOf(itemSize), Utils.getTimeFormatSemple(countDuration * 1000), String.valueOf(countArticles)));

        adtPositionAudio.setData(audioList);
        lst_audio_guild.setAdapter(adtPositionAudio);

        initEvent();
    }

    private void initEvent() {
        adtPositionAudio.setOnClickItem(new AdtPositionAudio.onClickItem() {
            @Override
            public void onClickItem(VtcModelPositionAudio service) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Const.BUNDLE_TYPE_DATA_TYPE, service);
                AppCore.CallFragmentSection(Const.UI_ARCHITECTURE_DETAILS, bundle);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmdBack();
            }
        });

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ARActivity.class);
                startActivity(intent);
            }
        });

        btn_map_places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putParcelable(Const.BUNDLE_TYPE_DATA_TYPE, vtcModelPosition);
                AppCore.CallFragmentSection(Const.UI_POSITION_MAP, bundle);
            }
        });
    }

    private void initDumpData() {
        audioList = new ArrayList<>();

        VtcModelPositionAudio positionAudio = new VtcModelPositionAudio();
        positionAudio.setId(0);
        positionAudio.setTitle("Four Pillars");
        positionAudio.setDuration(180);
        positionAudio.setAvatar("ic_position_audio_1");
        audioList.add(positionAudio);

        positionAudio = new VtcModelPositionAudio();
        positionAudio.setId(1);
        positionAudio.setTitle("Great portico");
        positionAudio.setDuration(180);
        positionAudio.setAvatar("ic_position_audio_2");
        audioList.add(positionAudio);

        positionAudio = new VtcModelPositionAudio();
        positionAudio.setId(2);
        positionAudio.setTitle("Khuê Văn pavillion");
        positionAudio.setDuration(180);
        positionAudio.setAvatar("ic_position_audio_3");
        audioList.add(positionAudio);

        positionAudio = new VtcModelPositionAudio();
        positionAudio.setId(3);
        positionAudio.setTitle("82 doctor stelae & Well of Heavenly Clarity");
        positionAudio.setDuration(180);
        positionAudio.setAvatar("ic_position_audio_4");
        audioList.add(positionAudio);

        positionAudio = new VtcModelPositionAudio();
        positionAudio.setId(4);
        positionAudio.setTitle("Countyard of the Sage sanctuary");
        positionAudio.setDuration(180);
        positionAudio.setAvatar("ic_position_audio_5");
        audioList.add(positionAudio);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onResume() {
        super.onResume();
        Utils.hideKeyboard(getmActivity());
    }

}
