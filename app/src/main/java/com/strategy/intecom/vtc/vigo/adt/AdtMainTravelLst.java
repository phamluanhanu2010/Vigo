/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/12/16 1:05 PM
 */

package com.strategy.intecom.vtc.vigo.adt;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.model.VtcModelMainTravel;
import com.strategy.intecom.vtc.vigo.utils.Utils;

import java.util.List;

/**
 * Created by Mr. Ha on 5/18/16.
 *
 * @author Mr. Ha
 */
public class AdtMainTravelLst extends BaseAdapter {

    private LayoutInflater mLayoutInflater;

    private Activity context;

    private onClickItem onClickItem;

    private List<VtcModelMainTravel> lst = null;

    private int height = 0;

    public void setOnClickItem(AdtMainTravelLst.onClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public AdtMainTravelLst.onClickItem getOnClickItem() {
        return onClickItem;
    }

    public AdtMainTravelLst(Activity context, int[] screen) {
        this.context = context;
        int offset = screen[0] / 3;
        this.height = offset + (offset / 8);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<VtcModelMainTravel> lst) {
        this.lst = null;
        this.lst = lst;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (lst == null) {
            return 0;
        }
        return lst.size();
    }

    @Override
    public VtcModelMainTravel getItem(int position) {
        if (lst == null) {
            return null;
        }
        return lst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolderItem vhItem;

        if (null == convertView) {
            convertView = mLayoutInflater.inflate(R.layout.tmp_main_travel, parent, false);
            vhItem = new ViewHolderItem(convertView);

            LinearLayout.LayoutParams tv_header_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
            vhItem.lout_container_travel_main.setLayoutParams(tv_header_params);

        } else {
            vhItem = (ViewHolderItem) convertView.getTag();
        }

        final VtcModelMainTravel modelOrder = getItem(position);

        if (modelOrder != null) {

            vhItem.lout_container_travel_main.setBackgroundResource(Utils.initGetResource(context, modelOrder.getBackgroundResource()));

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnClickItem().onClickItem(modelOrder);
                }
            });
        }
        return convertView;
    }

    public interface onClickItem {
        void onClickItem(VtcModelMainTravel service);
    }

    public static class ViewHolderItem {

        private LinearLayout lout_container_travel_main;

        public ViewHolderItem(View itemView) {
            itemView.setTag(this);
            lout_container_travel_main = (LinearLayout) itemView.findViewById(R.id.lout_container_travel_main);
        }
    }

}



