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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.model.VtcModelMainTravel;
import com.strategy.intecom.vtc.vigo.model.VtcModelPositionAudio;
import com.strategy.intecom.vtc.vigo.utils.Utils;

import java.util.List;

/**
 * Created by Mr. Ha on 5/18/16.
 *
 * @author Mr. Ha
 */
public class AdtPositionAudio extends BaseAdapter {

    private LayoutInflater mLayoutInflater;

    private Activity context;

    private onClickItem onClickItem;

    private List<VtcModelPositionAudio> lst = null;

    private int width = 0;
    private int height = 0;

    public void setOnClickItem(AdtPositionAudio.onClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public AdtPositionAudio.onClickItem getOnClickItem() {
        return onClickItem;
    }

    public AdtPositionAudio(Activity context, int[] screen) {
        this.context = context;

        this.width = screen[0] / 4;

        this.height = width;

        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<VtcModelPositionAudio> lst) {
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
    public VtcModelPositionAudio getItem(int position) {
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
            convertView = mLayoutInflater.inflate(R.layout.tmp_position_audio, parent, false);
            vhItem = new ViewHolderItem(convertView);

            LinearLayout.LayoutParams tv_header_params = new LinearLayout.LayoutParams(width, height);
            vhItem.img_thumb.setLayoutParams(tv_header_params);

        } else {
            vhItem = (ViewHolderItem) convertView.getTag();
        }

        final VtcModelPositionAudio modelOrder = getItem(position);

        if (modelOrder != null) {

            vhItem.img_thumb.setBackgroundResource(Utils.initGetResource(context, modelOrder.getAvatar()));
            vhItem.txt_count.setText(String.valueOf(position + 1));
            vhItem.txt_title.setText(modelOrder.getTitle());
            vhItem.txt_duration.setText(context.getResources().getString(R.string.title_audio_length, Utils.getTimeFormatSemple(modelOrder.getDuration() * 1000)));

            vhItem.btn_buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnClickItem().onClickItem(modelOrder);
                }
            });

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
        void onClickItem(VtcModelPositionAudio service);
    }

    public static class ViewHolderItem {

        private TextView txt_count;
        private ImageView img_thumb;
        private TextView txt_title;
        private TextView txt_duration;
        private TextView btn_buy;

        public ViewHolderItem(View itemView) {
            itemView.setTag(this);
            txt_count = (TextView) itemView.findViewById(R.id.txt_count);
            img_thumb = (ImageView) itemView.findViewById(R.id.img_thumb);
            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            txt_duration = (TextView) itemView.findViewById(R.id.txt_duration);
            btn_buy = (TextView) itemView.findViewById(R.id.btn_buy);
        }
    }

}



