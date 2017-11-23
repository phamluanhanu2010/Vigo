/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/11/16 1:47 PM
 */

package com.strategy.intecom.vtc.vigo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.enums.TypeShowDialog;

/**
 * Created by Mr. Ha on 5/20/16.
 *
 * @author Mr. Ha
 */
public class DlMessage extends Dialog {

    private Context context;

    private TextView tv_message_content;
    private TextView btn_cancel;
    private TextView btn_enable_gps;
    private TextView btn_enable_wifi;

    private TypeShowDialog typekey;

    private String content = "";

    private onClickDialogItem onClickDialogItem;

    private int width;

    public DlMessage(Context context, int width, TypeShowDialog typekey) {
        super(context, R.style.DialogTheme);
        this.context = context;
        this.typekey = typekey;
        this.width = (int) (width - (context.getResources().getDimension(R.dimen.confirm_ui_padding_w) * 2));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dl_message);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);

        setCancelable(false);

        initController();
    }

    public void initController() {

        tv_message_content = (TextView) findViewById(R.id.tv_message_content);
        btn_cancel = (TextView) findViewById(R.id.btn_cancel);
        btn_enable_gps = (TextView) findViewById(R.id.btn_enable_gps);
        btn_enable_wifi = (TextView) findViewById(R.id.btn_enable_wifi);

        initData();
    }

    private void initData() {
        btn_enable_gps.setVisibility(TextView.GONE);
        btn_enable_wifi.setVisibility(TextView.GONE);

        if (typekey == TypeShowDialog.TYPE_SHOW_ENABLE_GPS) {
            content = context.getResources().getString(R.string.gps_not_enabled);

            btn_enable_gps.setVisibility(TextView.VISIBLE);

            btn_enable_gps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getOnClickDialogItem().onClickDialogItemEnableGPS();
                }
            });
        } else if (typekey == TypeShowDialog.TYPE_SHOW_ENABLE_NETWORK) {
            content = context.getResources().getString(R.string.network_not_enabled);

            btn_enable_wifi.setVisibility(TextView.VISIBLE);

            btn_enable_wifi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getOnClickDialogItem().onClickDialogItemEnableWifi();
                }
            });
        }

        tv_message_content.setText(content);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnClickDialogItem().onClickDialogItemCancel();
            }
        });
    }

    public DlMessage.onClickDialogItem getOnClickDialogItem() {
        return onClickDialogItem;
    }

    public void setOnClickDialogItem(DlMessage.onClickDialogItem onClickDialogItem) {
        this.onClickDialogItem = onClickDialogItem;
    }

    public interface onClickDialogItem {
        void onClickDialogItemCancel();

        void onClickDialogItemEnableWifi();

        void onClickDialogItemEnableGPS();
    }

}
