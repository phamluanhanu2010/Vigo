/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/24/16 3:20 PM
 */

package com.strategy.intecom.vtc.vigo.view.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.telephony.SmsManager;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.strategy.intecom.vtc.vigo.MainScreen;
import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.enums.TypeShowDialog;
import com.strategy.intecom.vtc.vigo.model.VtcModelNoti;
import com.strategy.intecom.vtc.vigo.service.GcmListenerServices;
import com.strategy.intecom.vtc.vigo.utils.Const;
import com.strategy.intecom.vtc.vigo.utils.Utils;

/**
 * Created by Mr. Ha on 5/18/16.
 *
 * @author Mr. Ha
 */
public abstract class AppCore extends AppCoreMap {

    @SuppressLint("HandlerLeak")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        handlerReceiveMessageAction = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                initReceiveMessage(msg);
            }
        };
    }

    /**
     * Handle When receive message action
     * Push action to UI Similar
     *
     * @param handlerMessage
     */
    protected synchronized void initReceiveMessage(Message handlerMessage) {

        FragmentActivity fragmentActivity = getmActivity();

        if (fragmentActivity == null) {
            return;
        }

        synchronized (this) {
            switch (handlerMessage.what) {
//                case Const.UI_ACTION_CHECK_INTERNET:
//
//                    int isStatusNetWork = AppCore.getGpsTracker(fragmentActivity, "initReceiveMessage").isConnection();
//
//                    if (isStatusNetWork == Integer.MIN_VALUE) {
//                        cmdOnRefreshLocation("");
//                    } else if (isStatusNetWork == 1) {
//                        // No GPS Connection
//                        initShowDialogOption(fragmentActivity, TypeShowDialog.TYPE_SHOW_ENABLE_GPS);
//                    } else if (isStatusNetWork == 2) {
//                        // No Internet Connection
//                        initShowDialogOption(fragmentActivity, TypeShowDialog.TYPE_SHOW_ENABLE_NETWORK);
//                    }
//                    break;

                case Const.UI_ACTION_NOTIFICATION:
                    if (handlerMessage.obj instanceof VtcModelNoti) {
                        initGotoFragmentFromNoti(fragmentActivity, (VtcModelNoti) handlerMessage.obj);
                    }
                    break;
            }
        }
    }

    /**
     * Handle When receive message action notification goto from Noti
     *
     * @param vtcModelNoti obj noti data
     */
    public void initGotoFragmentFromNoti(FragmentActivity fragmentActivity, VtcModelNoti vtcModelNoti) {

        if (vtcModelNoti != null) {

            int type = vtcModelNoti.getType();

            Bundle bundle = new Bundle();
            bundle.putString("msg", vtcModelNoti.getMessage());

            switch (type) {
                case GcmListenerServices.NOTI_SHOW_MESSAGE_SYSTEM:
                    initShowDialogOption(fragmentActivity, TypeShowDialog.TYPE_SHOW_MESSAGE_INFO, bundle);
                    break;
            }
        }
    }

    public static void setFormatCurrency(Context context, TextView textView, String strPrice) {

        if (textView == null) {
            return;
        }

        textView.setText(Html.fromHtml(Utils.getConvertPrice(context, strPrice)));
    }


    /**
     * Call when using service call
     *
     * @param num Phone number
     */
    public void initCallIntentPhone(Activity activity, String num) {

        try {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));
            activity.startActivity(intent);
        } catch (SecurityException ex) {
            AppCore.showLog("initCallIntentPhone ----- : " + ex.getMessage());
        }
    }

    /**
     * Call when using service send message
     *
     * @param num  Phone number
     * @param body Body message
     */
    public void initCallIntentMessage(String num, String body) {

        try {
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address", num);
            smsIntent.putExtra("sms_body", body);
            startActivity(smsIntent);
        } catch (ActivityNotFoundException ex) {
            AppCore.showLog("initCallIntentMessage ----- : " + ex.getMessage());
            try {
                PendingIntent sentIntent = PendingIntent.getActivity(getmActivity(), 0, new Intent(getmActivity(), MainScreen.class), 0);
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(num, null, body, sentIntent, null);
            } catch (SecurityException e) {
                AppCore.showLog("initCallIntentMessage ActivityNotFoundException ----- : " + e.getMessage());
            }
        }
    }

    /**
     * Feedback for Choose any app
     */
    public void CallSendEmailForChoose(String[] emailaddress, String content) {
        try {
            Intent sendmail = new Intent(Intent.ACTION_SEND);
            sendmail.setType("text/plain");
            sendmail.putExtra(Intent.EXTRA_EMAIL, emailaddress);
            sendmail.putExtra(Intent.EXTRA_SUBJECT, getmActivity().getResources().getString(R.string.app_name));
            sendmail.putExtra(Intent.EXTRA_TEXT, content);
            startActivity(Intent.createChooser(sendmail, "Send mail..."));
        } catch (ActivityNotFoundException ex) {
            AppCore.showLog(" CallSendEmailForChoose ActivityNotFoundException : " + ex.getMessage());
        }
    }

    /**
     * Feedback for Gmail
     */
    public void CallSendEmailForGmail(String emailaddress, String content) {
        try {
            Intent gmail = new Intent(Intent.ACTION_VIEW);
            gmail.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
            if (!emailaddress.equals(""))
                gmail.putExtra(Intent.EXTRA_EMAIL, emailaddress);
            gmail.putExtra(Intent.EXTRA_SUBJECT, getmActivity().getResources().getString(R.string.app_name));
            gmail.setType("plain/text");
            gmail.putExtra(Intent.EXTRA_TEXT, content);
            startActivity(gmail);
        } catch (ActivityNotFoundException ex) {
            AppCore.showLog(" CallSendEmailForGmail ActivityNotFoundException : " + ex.getMessage());
        }

//        Intent intent = new Intent(android.content.Intent.ACTION_SENDTO);
//        intent.setType("message/rfc822");
//        intent.putExtra(Intent.EXTRA_EMAIL, emailaddress);
//        intent.putExtra(Intent.EXTRA_SUBJECT, getmActivity().getResources().getString(R.string.app_name));
//        intent.putExtra(Intent.EXTRA_TEXT, content);
//
//        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    /**
     * code to post/handler request for permission
     */
    public final static int REQUEST_CODE = 101;

    @TargetApi(Build.VERSION_CODES.M)
    public boolean checkDrawOverlayPermission(Context context) {
        /** check if we already  have permission to draw over other apps */
        if (!Settings.canDrawOverlays(context)) {
            /** if not construct intent to request permission */
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + context.getPackageName()));
            /** request permission via start activity for result */
            startActivityForResult(intent, REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /** check if received result code
         is equal our requested code for draw permission  */
        if (requestCode == REQUEST_CODE) {
            if (Settings.canDrawOverlays(getmActivity())) {
                // continue here - permission was granted

                initShowDialogOption(getmActivity(), TypeShowDialog.TYPE_SHOW_MESSAGE_NEW_JOB_FAST);
            }
        }
    }
}
