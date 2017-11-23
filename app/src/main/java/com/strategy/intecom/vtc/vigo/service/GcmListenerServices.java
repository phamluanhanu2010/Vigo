/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/19/16 3:01 PM
 */

package com.strategy.intecom.vtc.vigo.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.google.android.gms.gcm.GcmListenerService;
import com.strategy.intecom.vtc.vigo.MainScreen;
import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.model.VtcModelNoti;
import com.strategy.intecom.vtc.vigo.utils.Const;
import com.strategy.intecom.vtc.vigo.utils.ParserJson;
import com.strategy.intecom.vtc.vigo.view.base.AppCore;
import com.strategy.intecom.vtc.vigo.view.base.AppCoreBase;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mr. Ha on 5/17/16.
 * @author Mr. Ha
 */
public class GcmListenerServices extends GcmListenerService {

    public final static int NOTI_SHOW_MESSAGE_SYSTEM = 1001;               // Thông báo khi sắp đến thời gian làm việc

    public GcmListenerServices(){

    }

    @Override
    public void onMessageReceived(String from, Bundle data) {

        String notification_type = data.getString("notification_type");
        String title = data.getString("gcm.notification.title");
        String body = data.getString("gcm.notification.body");
        String responseData = data.getString("responseData");
        String badge = data.getString("gcm.notification.badge");

        AppCoreBase.showLog(" onMessageReceived -----from--- : " + from + ":--body---:" + body + ":--title---:" + title + " ------------- notification_type : " + notification_type + " --------- responseData : " + responseData);

        wakeUpScreenDevice();

        String sID = "";
        int type = 0;

        if (notification_type != null && !notification_type.isEmpty()) {

            try {

                type = Integer.parseInt(notification_type);

            } catch (NumberFormatException e) {

            }
            try {
                if(responseData != null && !responseData.isEmpty()) {
                    JSONObject jsonObject = new JSONObject(responseData);

                    JSONObject orderObj = jsonObject.optJSONObject(ParserJson.API_PARAMETER_RESSPONSE_DATA);

                    if (orderObj == null) {
                        orderObj = jsonObject;
                    }

                    sID = orderObj.optString(ParserJson.API_PARAMETER_ID_);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            switch (type) {

                case NOTI_SHOW_MESSAGE_SYSTEM:
                default:

                    try {
                        VtcModelNoti vtcModelNoti = new VtcModelNoti();
                        vtcModelNoti.setType(type);
                        vtcModelNoti.setMessage(body);
                        vtcModelNoti.setId_order(sID);
                        vtcModelNoti.setResponseData(responseData);
                        vtcModelNoti.setTypeGoto(VtcModelNoti.TYPE_GOTO_NOTI);
                        AppCore.initReceived(Const.UI_ACTION_NOTIFICATION, vtcModelNoti);

                    } catch (ExceptionInInitializerError | NoClassDefFoundError error) {
                        error.printStackTrace();
                    }
                    initShowNotification(type, title, body, sID, responseData);
                    break;
            }
        }
    }

    /**
     *
     *  Show Big content Notification
     * @param msg -->> Message
     *
     **/
    public void initShowNotification(int type, String title, String msg, String idOrder, String responseData){

        Bitmap icon1 = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                getApplicationContext()).setAutoCancel(true)
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setDefaults(Notification.DEFAULT_ALL)
                .setLargeIcon(icon1)
                .setContentText(msg);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText(msg);
        bigText.setBigContentTitle(title);
        bigText.setSummaryText("Por: " + msg);
        mBuilder.setStyle(bigText);
        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(getApplicationContext(),
                MainScreen.class);
        resultIntent.putExtra("type", type);
        resultIntent.putExtra("message", msg);
        resultIntent.putExtra("responseData", responseData);
        resultIntent.putExtra("id_order", idOrder);

        // The stack builder object will contain an artificial back
        // stack for
        // the
        // started Activity.
        // getApplicationContext() ensures that navigating backward from
        // the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder
                .create(getApplicationContext());

        // Adds the back stack for the Intent (but not the Intent
        // itself)
        stackBuilder.addParentStack(MainScreen.class);

        // Adds the Intent that starts the Activity to the top of the
        // stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // mId allows you to update the notification later on.
        mNotificationManager.notify(100, mBuilder.build());
    }

    /**
     *
     * Call when received message priority
     *
     * Wake up screen display message
     *
     */
    private void wakeUpScreenDevice(){

        PowerManager.WakeLock screenLock = ((PowerManager)getSystemService(POWER_SERVICE)).newWakeLock(
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        screenLock.acquire();

        screenLock.release();
    }

    @Override
    public void onDeletedMessages() {
    }

    @Override
    public void onMessageSent(String msgId) {
        AppCoreBase.showLog(" onMessageSent -------- : " + msgId);
    }

    @Override
    public void onSendError(String msgId, String error) {

    }

}
