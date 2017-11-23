/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/10/16 11:06 AM
 */

package com.strategy.intecom.vtc.vigo.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.adt.AdtPlaceSlidesImage;
import com.strategy.intecom.vtc.vigo.adt.AdtPositionLst;
import com.strategy.intecom.vtc.vigo.interfaces.Callback;
import com.strategy.intecom.vtc.vigo.model.VtcModelMainTravel;
import com.strategy.intecom.vtc.vigo.model.VtcModelPosition;
import com.strategy.intecom.vtc.vigo.utils.Const;
import com.strategy.intecom.vtc.vigo.utils.Utils;
import com.strategy.intecom.vtc.vigo.view.base.AppCore;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr. Ha on 5/19/16.
 *
 * @author Mr. Ha
 */
@SuppressLint("ValidFragment")
public class FMLstInTravel extends AppCore implements View.OnClickListener,
        ViewPager.OnPageChangeListener {

    private VtcModelMainTravel vtcModelMainTravel;

    private RecyclerView lst_1;

    private RecyclerView lst_2;

    private ViewPager mPager;

    private PageIndicator mIndicator;

    private Handler handlerBanner = new Handler();

    private AdtPlaceSlidesImage mAdapterBanner;

    private List<VtcModelMainTravel> travelBannerList;

    private List<VtcModelPosition> travelPositionList_1;
    private AdtPositionLst adtPositionLstLst1;

    private List<VtcModelPosition> travelPositionList_2;
    private AdtPositionLst adtPositionLstLst2;

    private int indexPageBanner = -1;
    private int indexPageHighlights1 = -1;
    private int indexPageHighlights2 = -1;

    private TextView txt_description;

    private int width = 0;
    private int height = 0;

    public FMLstInTravel() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        savedInstanceState = getArguments();
        if (savedInstanceState != null) {
            vtcModelMainTravel = savedInstanceState.getParcelable(Const.BUNDLE_TYPE_DATA_TYPE);
        }
        return inflater.inflate(R.layout.ui_lst_in_travel, container, false);
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

        mPager = (ViewPager) view.findViewById(R.id.pager);

        int offset = screen[0];

        width = offset;

        offset = width / 16;

        height = offset * 9;

        RelativeLayout.LayoutParams tv_header_params = new RelativeLayout.LayoutParams(width, height);
        mPager.setLayoutParams(tv_header_params);

        mIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);

        mAdapterBanner = new AdtPlaceSlidesImage(new Callback() {
            @SafeVarargs
            @Override
            public final <T> void onHandlerCallBack(int key, T... t) {

            }
        }, getFragmentManager(), travelBannerList, getmActivity(), width, height);

        if (mPager != null) {
            mPager.setAdapter(mAdapterBanner);
        }

        if (mIndicator != null) {
            mIndicator.setViewPager(mPager);

            ((CirclePageIndicator) mIndicator).setSnap(true);
        }

        lst_1 = (RecyclerView) view.findViewById(R.id.lst_1);

        lst_1.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getmActivity(), LinearLayoutManager.HORIZONTAL, false);
        lst_1.setLayoutManager(layoutManager);

        adtPositionLstLst1 = new AdtPositionLst(getmActivity(), screen);
        adtPositionLstLst1.setData(travelPositionList_1);

        lst_1.setAdapter(adtPositionLstLst1);


        lst_2 = (RecyclerView) view.findViewById(R.id.lst_2);

        lst_2.setHasFixedSize(true);
        LinearLayoutManager layoutManagerLst2 = new LinearLayoutManager(getmActivity(), LinearLayoutManager.HORIZONTAL, false);
        lst_2.setLayoutManager(layoutManagerLst2);

        adtPositionLstLst2 = new AdtPositionLst(getmActivity(), screen);
        adtPositionLstLst2.setData(travelPositionList_2);

        lst_2.setAdapter(adtPositionLstLst2);

        if (handlerBanner != null && ViewPagerVisibleScroll != null) {
            handlerBanner.post(ViewPagerVisibleScroll);
        }


        if (lst_1 != null && pagerScrollHighlights1 != null) {
            lst_1.post(pagerScrollHighlights1);
        }

        if (lst_2 != null && pagerScrollHighlights2 != null) {
            lst_2.post(pagerScrollHighlights2);
        }

        txt_description = (TextView) view.findViewById(R.id.txt_description);
        txt_description.setText(vtcModelMainTravel.getDescription());


        initData();
    }

    private void initData() {

        initEvent();
    }

    private void initEvent() {
        adtPositionLstLst1.setOnClickItem(new AdtPositionLst.onClickItem() {
            @Override
            public void onClickItem(VtcModelPosition items) {

                Bundle bundle = new Bundle();
                bundle.putParcelable(Const.BUNDLE_TYPE_DATA_TYPE, items);
                AppCore.CallFragmentSection(Const.UI_POSITION_DETAIL, bundle);
            }
        });

        adtPositionLstLst2.setOnClickItem(new AdtPositionLst.onClickItem() {
            @Override
            public void onClickItem(VtcModelPosition items) {

                Bundle bundle = new Bundle();
                bundle.putParcelable(Const.BUNDLE_TYPE_DATA_TYPE, items);
                AppCore.CallFragmentSection(Const.UI_POSITION_DETAIL, bundle);
            }
        });
    }

    private void initDumpData() {
        travelBannerList = new ArrayList<>();

        VtcModelMainTravel vtcModelMainTravel = new VtcModelMainTravel();
        vtcModelMainTravel.setId(Const.KEY_FLAG_TYPE_HANOI);
        vtcModelMainTravel.setTitle(this.getResources().getString(R.string.title_main_travel_hanoi));
        vtcModelMainTravel.setBackgroundResource("ic_main_travel_hanoi");
        travelBannerList.add(vtcModelMainTravel);

        vtcModelMainTravel = new VtcModelMainTravel();
        vtcModelMainTravel.setId(Const.KEY_FLAG_TYPE_HOIAN);
        vtcModelMainTravel.setTitle(this.getResources().getString(R.string.title_main_travel_hoian));
        vtcModelMainTravel.setBackgroundResource("ic_main_travel_hoian");
        travelBannerList.add(vtcModelMainTravel);

        vtcModelMainTravel = new VtcModelMainTravel();
        vtcModelMainTravel.setId(Const.KEY_FLAG_TYPE_DANANG);
        vtcModelMainTravel.setTitle(this.getResources().getString(R.string.title_main_travel_danang));
        vtcModelMainTravel.setBackgroundResource("ic_main_travel_danang");
        travelBannerList.add(vtcModelMainTravel);

        vtcModelMainTravel = new VtcModelMainTravel();
        vtcModelMainTravel.setId(Const.KEY_FLAG_TYPE_QUANGBINH);
        vtcModelMainTravel.setTitle(this.getResources().getString(R.string.title_main_travel_quangbinh));
        vtcModelMainTravel.setBackgroundResource("ic_main_travel_quangbinh");
        travelBannerList.add(vtcModelMainTravel);

        vtcModelMainTravel = new VtcModelMainTravel();
        vtcModelMainTravel.setId(Const.KEY_FLAG_TYPE_NHATRANG);
        vtcModelMainTravel.setTitle(this.getResources().getString(R.string.title_main_travel_nhatrang));
        vtcModelMainTravel.setBackgroundResource("ic_main_travel_nhatrang");
        travelBannerList.add(vtcModelMainTravel);


        travelPositionList_1 = new ArrayList<>();

        VtcModelPosition vtcModelPosition = new VtcModelPosition();
        vtcModelPosition.setId(0);
        vtcModelPosition.setTitle("Temple of Literature copy");
        vtcModelPosition.setPrice("200.000");
        vtcModelPosition.setSave(true);
        vtcModelPosition.setDescription("The Temple of Literature (Vietnamese: Văn Miếu) is a Temple of ");
        vtcModelPosition.setBackgroundResource("ic_travel_position_1");
        vtcModelPosition.setMapResourceThumb("ic_location_res_thumb");
        vtcModelPosition.setMapResourceFull("ic_location_res_full");
        vtcModelPosition.setAddress("địa chỉ");
        travelPositionList_1.add(vtcModelPosition);

        vtcModelPosition = new VtcModelPosition();
        vtcModelPosition.setId(1);
        vtcModelPosition.setTitle("Hoan Kiem Lake");
        vtcModelPosition.setPrice("190.000");
        vtcModelPosition.setSave(false);
        vtcModelPosition.setDescription("The Temple of Literature (Vietnamese: Văn Miếu) is a Temple of ");
        vtcModelPosition.setBackgroundResource("ic_travel_position_2");
        vtcModelPosition.setMapResourceThumb("ic_location_res_thumb");
        vtcModelPosition.setMapResourceFull("ic_location_res_full");
        vtcModelPosition.setAddress("địa chỉ");
        travelPositionList_1.add(vtcModelPosition);

        vtcModelPosition = new VtcModelPosition();
        vtcModelPosition.setId(2);
        vtcModelPosition.setTitle("One Pillar Pagoda");
        vtcModelPosition.setPrice("390.000");
        vtcModelPosition.setSave(true);
        vtcModelPosition.setDescription("The Temple of Literature (Vietnamese: Văn Miếu) is a Temple of ");
        vtcModelPosition.setBackgroundResource("ic_travel_position_3");
        vtcModelPosition.setMapResourceThumb("ic_location_res_thumb");
        vtcModelPosition.setMapResourceFull("ic_location_res_full");
        vtcModelPosition.setAddress("địa chỉ");
        travelPositionList_1.add(vtcModelPosition);

        vtcModelPosition = new VtcModelPosition();
        vtcModelPosition.setId(3);
        vtcModelPosition.setTitle("Hoan Kiem Lake");
        vtcModelPosition.setPrice("120.000");
        vtcModelPosition.setSave(false);
        vtcModelPosition.setDescription("The Temple of Literature (Vietnamese: Văn Miếu) is a Temple of ");
        vtcModelPosition.setBackgroundResource("ic_travel_position_1");
        vtcModelPosition.setMapResourceThumb("ic_location_res_thumb");
        vtcModelPosition.setMapResourceFull("ic_location_res_full");
        vtcModelPosition.setAddress("địa chỉ");
        travelPositionList_1.add(vtcModelPosition);

        vtcModelPosition = new VtcModelPosition();
        vtcModelPosition.setId(4);
        vtcModelPosition.setTitle("Temple of Literature copy");
        vtcModelPosition.setPrice("230.000");
        vtcModelPosition.setSave(true);
        vtcModelPosition.setDescription("The Temple of Literature (Vietnamese: Văn Miếu) is a Temple of ");
        vtcModelPosition.setBackgroundResource("ic_travel_position_2");
        vtcModelPosition.setMapResourceThumb("ic_location_res_thumb");
        vtcModelPosition.setMapResourceFull("ic_location_res_full");
        vtcModelPosition.setAddress("địa chỉ");
        travelPositionList_1.add(vtcModelPosition);


        travelPositionList_2 = new ArrayList<>();

        vtcModelPosition = new VtcModelPosition();
        vtcModelPosition.setId(0);
        vtcModelPosition.setTitle("Temple of Literature copy");
        vtcModelPosition.setPrice("200.000");
        vtcModelPosition.setSave(true);
        vtcModelPosition.setDescription("The Temple of Literature (Vietnamese: Văn Miếu) is a Temple of ");
        vtcModelPosition.setBackgroundResource("ic_travel_position_1");
        vtcModelPosition.setMapResourceThumb("ic_location_res_thumb");
        vtcModelPosition.setMapResourceFull("ic_location_res_full");
        vtcModelPosition.setAddress("địa chỉ");
        travelPositionList_2.add(vtcModelPosition);

        vtcModelPosition = new VtcModelPosition();
        vtcModelPosition.setId(1);
        vtcModelPosition.setTitle("Hoan Kiem Lake");
        vtcModelPosition.setPrice("190.000");
        vtcModelPosition.setSave(false);
        vtcModelPosition.setDescription("The Temple of Literature (Vietnamese: Văn Miếu) is a Temple of ");
        vtcModelPosition.setBackgroundResource("ic_travel_position_2");
        vtcModelPosition.setMapResourceThumb("ic_location_res_thumb");
        vtcModelPosition.setMapResourceFull("ic_location_res_full");
        vtcModelPosition.setAddress("địa chỉ");
        travelPositionList_2.add(vtcModelPosition);

        vtcModelPosition = new VtcModelPosition();
        vtcModelPosition.setId(2);
        vtcModelPosition.setTitle("One Pillar Pagoda");
        vtcModelPosition.setPrice("390.000");
        vtcModelPosition.setSave(true);
        vtcModelPosition.setDescription("The Temple of Literature (Vietnamese: Văn Miếu) is a Temple of ");
        vtcModelPosition.setBackgroundResource("ic_travel_position_3");
        vtcModelPosition.setMapResourceThumb("ic_location_res_thumb");
        vtcModelPosition.setMapResourceFull("ic_location_res_full");
        vtcModelPosition.setAddress("địa chỉ");
        travelPositionList_2.add(vtcModelPosition);

        vtcModelPosition = new VtcModelPosition();
        vtcModelPosition.setId(3);
        vtcModelPosition.setTitle("Hoan Kiem Lake");
        vtcModelPosition.setPrice("120.000");
        vtcModelPosition.setSave(false);
        vtcModelPosition.setAddress("địa chỉ");
        vtcModelPosition.setDescription("The Temple of Literature (Vietnamese: Văn Miếu) is a Temple of ");
        vtcModelPosition.setBackgroundResource("ic_travel_position_1");
        vtcModelPosition.setMapResourceThumb("ic_location_res_thumb");
        vtcModelPosition.setMapResourceFull("ic_location_res_full");
        travelPositionList_2.add(vtcModelPosition);

        vtcModelPosition = new VtcModelPosition();
        vtcModelPosition.setId(4);
        vtcModelPosition.setTitle("Temple of Literature copy");
        vtcModelPosition.setPrice("230.000");
        vtcModelPosition.setSave(true);
        vtcModelPosition.setDescription("The Temple of Literature (Vietnamese: Văn Miếu) is a Temple of ");
        vtcModelPosition.setBackgroundResource("ic_travel_position_2");
        vtcModelPosition.setMapResourceThumb("ic_location_res_thumb");
        vtcModelPosition.setMapResourceFull("ic_location_res_full");
        vtcModelPosition.setAddress("địa chỉ");
        travelPositionList_2.add(vtcModelPosition);
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


    /**
     * Auto play page banner
     */
    Runnable ViewPagerVisibleScroll = new Runnable() {
        @Override
        public void run() {
            if (mAdapterBanner != null && mPager != null) {
                if (indexPageBanner < (mAdapterBanner.getCount() - 1)) {
                    indexPageBanner++;
                } else {
                    indexPageBanner = 0;
                }
                try {
                    mPager.setCurrentItem(indexPageBanner, true);
                } catch (IllegalArgumentException ignored) {

                }

                if (ViewPagerVisibleScroll != null && handlerBanner != null) {
                    handlerBanner.postDelayed(ViewPagerVisibleScroll, 5000);
                }
            } else {
                ViewPagerVisibleScroll = null;
                handlerBanner = null;
            }
        }
    };

    Runnable pagerScrollHighlights1 = new Runnable() {
        @Override
        public void run() {

            if (adtPositionLstLst1 != null && lst_1 != null) {
                if (indexPageHighlights1 < (adtPositionLstLst1.getItemCount() - 1)) {
                    indexPageHighlights1++;
                } else {
                    indexPageHighlights1 = 0;
                }
                try {
                    lst_1.smoothScrollToPosition(indexPageHighlights1);
                } catch (IllegalArgumentException ignored) {

                }
                if (pagerScrollHighlights1 != null && lst_1 != null) {
                    lst_1.postDelayed(pagerScrollHighlights1, 9000);
                }
            } else {
                pagerScrollHighlights1 = null;
                lst_1 = null;
            }
        }
    };

    Runnable pagerScrollHighlights2 = new Runnable() {
        @Override
        public void run() {

            if (adtPositionLstLst2 != null && lst_2 != null) {
                if (indexPageHighlights2 < (adtPositionLstLst2.getItemCount() - 1)) {
                    indexPageHighlights2++;
                } else {
                    indexPageHighlights2 = 0;
                }
                try {
                    lst_2.smoothScrollToPosition(indexPageHighlights2);
                } catch (IllegalArgumentException ignored) {

                }
                if (pagerScrollHighlights2 != null && lst_2 != null) {
                    lst_2.postDelayed(pagerScrollHighlights2, 7000);
                }
            } else {
                pagerScrollHighlights2 = null;
                lst_2 = null;
            }
        }
    };
}
