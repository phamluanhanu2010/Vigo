/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/5/16 5:24 PM
 */

package com.strategy.intecom.vtc.vigo.adt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.model.VtcModelPosition;
import com.strategy.intecom.vtc.vigo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr. Ha on 5/18/16.
 *
 * @author Mr. Ha
 */
public class AdtPositionLst extends RecyclerView.Adapter<AdtPositionLst.ViewHolderItem> {
    private Activity context;

    private List<VtcModelPosition> lstItem;
    private onClickItem onClickItem;
    private int widthView = 0;

    private int heightImage = 0;

    public AdtPositionLst(Activity context, int[] screen) {
        this.context = context;
        this.lstItem = new ArrayList<>();

        int offset = screen[0] / 100;

        this.widthView = offset * 60;

        offset = this.widthView / 16;

        this.heightImage = offset * 9;
    }

    public void setData(List<VtcModelPosition> lstItem) {
        this.lstItem = lstItem;

        notifyDataSetChanged();
    }

    @Override
    public ViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tmp_position_lst, parent, false);
        return new ViewHolderItem(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolderItem holder, @SuppressLint("RecyclerView") final int position) {
        if (lstItem != null) {
            final VtcModelPosition items = lstItem.get(position);

            if (items != null) {

                holder.txt_title.setText(String.valueOf(items.getTitle()));
                holder.txt_address.setText(String.valueOf(items.getAddress()));
                holder.txt_price.setText(String.valueOf(items.getPrice()));

                AbsListView.LayoutParams tv_header_params = new AbsListView.LayoutParams(widthView, AbsListView.LayoutParams.WRAP_CONTENT);
                holder.view.setLayoutParams(tv_header_params);

                holder.cb_check_save.setChecked(items.isSave());
//
//
//                ImageLoadAsync loadAsync = new ImageLoadAsync(context, holder.img_thumb, widthView, this.heightImage);
//                loadAsync.executeOnExecutor(MediaAsync.THREAD_POOL_EXECUTOR, items.getAvatar());
//
                holder.img_thumb.setImageResource(Utils.initGetResource(context, items.getBackgroundResource()));

                RelativeLayout.LayoutParams tv_header_params_image = new RelativeLayout.LayoutParams(widthView, this.heightImage);
                holder.img_thumb.setLayoutParams(tv_header_params_image);
//
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getOnClickItem().onClickItem(items);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        if (lstItem == null) {
            return 0;
        }
        return lstItem.size();
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder {

        private View view;
        private ImageView img_thumb;
        private TextView txt_price;
        private TextView txt_title;
        private TextView txt_address;
        private CheckBox cb_check_save;

        public ViewHolderItem(View itemView) {
            super(itemView);
            this.view = itemView;
            img_thumb = (ImageView) itemView.findViewById(R.id.img_thumb);
            txt_price = (TextView) itemView.findViewById(R.id.txt_price);
            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            txt_address = (TextView) itemView.findViewById(R.id.txt_address);
            cb_check_save = (CheckBox) itemView.findViewById(R.id.cb_check_save);
        }
    }

    public AdtPositionLst.onClickItem getOnClickItem() {
        return onClickItem;
    }

    public void setOnClickItem(AdtPositionLst.onClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public interface onClickItem {
        void onClickItem(VtcModelPosition items);
    }
}
