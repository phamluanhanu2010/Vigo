/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/12/16 1:05 PM
 */

package com.strategy.intecom.vtc.vigo.adt;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.model.VtcModelArchitetureAudio;
import com.strategy.intecom.vtc.vigo.utils.Utils;

import java.util.List;

/**
 * Created by Mr. Ha on 5/18/16.
 *
 * @author Mr. Ha
 */
public class AdtArchitectureAudio extends BaseAdapter {

    private LayoutInflater mLayoutInflater;

    private Activity context;

    private onClickItem onClickItem;

    private List<VtcModelArchitetureAudio> lst = null;

    private int width = 0;
    private int height = 0;

    private int currentItemId = 0;

    public void setOnClickItem(AdtArchitectureAudio.onClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public AdtArchitectureAudio.onClickItem getOnClickItem() {
        return onClickItem;
    }

    public AdtArchitectureAudio(Activity context, int[] screen) {
        this.context = context;

        this.width = screen[0] / 4;

        this.height = width;

        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<VtcModelArchitetureAudio> lst) {
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
    public VtcModelArchitetureAudio getItem(int position) {
        if (lst == null) {
            return null;
        }
        return lst.get(position);
    }

    public void updaStatus(int position) {
        for (int i = 0; i < lst.size(); i++) {
            if (i == position) {
                lst.get(i).setStatus("Playing");
            } else {
                lst.get(i).setStatus("Play");
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolderItem vhItem;

        if (null == convertView) {
            convertView = mLayoutInflater.inflate(R.layout.tmp_architecture_audio, parent, false);
            vhItem = new ViewHolderItem(convertView);

        } else {
            vhItem = (ViewHolderItem) convertView.getTag();
        }

        final VtcModelArchitetureAudio modelOrder = getItem(position);

        if (modelOrder != null) {
            vhItem.txt_count.setText(String.valueOf(position + 1));
            vhItem.txt_title.setText(modelOrder.getTitle());
            vhItem.txt_duration.setText(context.getResources().getString(R.string.title_audio_length, Utils.getTimeFormatSemple(modelOrder.getDuration() * 1000)));
            vhItem.btn_play.setText(modelOrder.getStatus());

            vhItem.btn_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnClickItem().onClickItem(modelOrder);
                }
            });
        }
        return convertView;
    }

    public interface onClickItem {
        void onClickItem(VtcModelArchitetureAudio service);
    }

    public static class ViewHolderItem {

        private TextView txt_count;
        private TextView txt_title;
        private TextView txt_duration;
        private TextView btn_play;

        public ViewHolderItem(View itemView) {
            itemView.setTag(this);
            txt_count = (TextView) itemView.findViewById(R.id.txt_count);
            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            txt_duration = (TextView) itemView.findViewById(R.id.txt_duration);
            btn_play = (TextView) itemView.findViewById(R.id.btn_play);
        }
    }

}



