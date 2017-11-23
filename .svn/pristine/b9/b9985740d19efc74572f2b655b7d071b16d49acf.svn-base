/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 11/1/16 7:43 AM
 */

package com.strategy.intecom.vtc.vigo.view.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.maps.model.LatLng;
import com.strategy.intecom.vtc.vigo.BuildConfig;
import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.config.VtcNWConnection;
import com.strategy.intecom.vtc.vigo.dialog.DlCalendar;
import com.strategy.intecom.vtc.vigo.dialog.DlChoicePhoto;
import com.strategy.intecom.vtc.vigo.dialog.DlMessage;
import com.strategy.intecom.vtc.vigo.enums.TypeShowDialog;
import com.strategy.intecom.vtc.vigo.interfaces.Callback;
import com.strategy.intecom.vtc.vigo.interfaces.RequestListener;
import com.strategy.intecom.vtc.vigo.utils.Const;
import com.strategy.intecom.vtc.vigo.utils.PreferenceUtil;
import com.strategy.intecom.vtc.vigo.utils.Utils;
import com.strategy.intecom.vtc.vigo.utils.map.GPSTracker;


/**
 * Created by Mr. Ha on 5/16/16.
 *
 * @author Mr. Ha
 */
public class AppCoreBase extends Fragment {

    private static final String TAG = "AppCoreBase";

    protected static Handler handlerReceiveMessageAction;
    private Handler mHandler;

    public static String APP_PATH;

    private static AlertDialog dialog;
    private DlMessage alertDialogConfirmNetWork;

    private static FragmentActivity mActivity;

    private static PreferenceUtil preferenceUtil;

    private static Tracker mTracker;

    public static PreferenceUtil getPreferenceUtil(Context context) {
        if (preferenceUtil == null) {
            preferenceUtil = new PreferenceUtil(context);
        }
        return preferenceUtil;
    }

    public static void setmActivity(FragmentActivity mActivity) {
        AppCoreBase.mActivity = mActivity;
    }

    public static FragmentActivity getmActivity() {
        return mActivity;
    }

    public static VtcNWConnection getConnection(RequestListener requestListener) {
        return new VtcNWConnection(getmActivity(), requestListener);
    }

    public static GPSTracker getGpsTracker(Activity context, String activityName) {
        AppCore.showLog("-------getGpsTracker----------------- : " + activityName);
        return new GPSTracker(context);
    }

    private static Tracker getmTracker() {
        return mTracker;
    }

    public static void setmTracker(Tracker mTracker) {
        AppCoreBase.mTracker = mTracker;
    }

    public static String getExtraPathAvatar() {
        return String.valueOf("?avatar_agency_time=" + String.valueOf(System.currentTimeMillis()));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Call Fragment
     *
     * @param KEY the key for fragment on class VtcFragmentFactory.class
     */
    public static void CallFragmentSection(int KEY) {
        CallFragmentSection(KEY, true);
    }

    /**
     * Call Fragment
     *
     * @param KEY    the key for fragment on class VtcFragmentFactory.class
     * @param bundle the send to data
     */
    public static void CallFragmentSection(int KEY, Bundle bundle) {
        CallFragmentSection(KEY, bundle, true, true);
    }

    /**
     * Call Fragment
     *
     * @param KEY            the key for fragment on class VtcFragmentFactory.class
     * @param isAddBackStack add back stack yes or no
     */
    public static void CallFragmentSection(int KEY, boolean isAddBackStack) {
        CallFragmentSection(KEY, null, isAddBackStack, true);
    }

    /**
     * Call Fragment
     *
     * @param KEY            the key for fragment on class VtcFragmentFactory.class
     * @param bunds          put data using bundle
     * @param isAddBackStack add back stack yes or no
     */
    public static void CallFragmentSection(int KEY, final Bundle bunds, boolean isAddBackStack) {
        CallFragmentSection(KEY, bunds, isAddBackStack, true);
    }

    /**
     * Call Fragment
     *
     * @param KEY            the key for fragment on class VtcFragmentFactory.class
     * @param bunds          put data using bundle
     * @param isAddBackStack add back stack yes or no
     * @param isAddFrag      the replace for view or add view
     */
    public static void CallFragmentSection(final int KEY, final Bundle bunds, final boolean isAddBackStack, final boolean isAddFrag) {

        FragmentActivity activity = getmActivity();

        if (activity == null || activity.isFinishing()) {
            return;
        }

        try {

            Const.UI_CURRENT_CONTEXT = KEY;

            System.gc();

            FragmentTransaction fragmentTS = activity.getSupportFragmentManager().beginTransaction();

            // Get Fragment By Key
            Fragment fragment = VtcFragmentFactory.getFragmentByKey(KEY);

            if (fragment != null) {

                try {

                    // Set Bundle Data
                    fragment.setArguments(bunds);

                    // Init Animation for Fragment view
                    fragmentTS.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);

                    if (isAddFrag) {

                        // Add Fragment into tmp
                        fragmentTS.add(R.id.fragcontent, fragment);
                    } else {

                        // Add Fragment into Full Screen
                        fragmentTS.add(android.R.id.content, fragment);
                    }

                    if (isAddBackStack) {

                        // Add Fragment into back stack
                        fragmentTS.addToBackStack(fragment.getClass().getName());
                    }

                    fragmentTS.commit();

                } catch (IllegalStateException e) {

                    fragmentTS.show(fragment);
                }
            }
        } catch (Exception e) {
            AppCoreBase.showLog(TAG + " Exception : " + e.getMessage());
        }
    }

    /**
     * Call Fragment callBack
     *
     * @param KEY      the key for fragment on class VtcFragmentFactory.class
     * @param callback return data
     */
    public static void CallFragmentSectionWithCallback(int KEY, Callback callback) {
        CallFragmentSectionWithCallback(KEY, null, callback, true, true);
    }

    /**
     * Call Fragment callBack
     *
     * @param KEY      the key for fragment on class VtcFragmentFactory.class
     * @param bundle   put data using bundle
     * @param callback return data
     */
    public static void CallFragmentSectionWithCallback(int KEY, Bundle bundle, Callback callback) {
        CallFragmentSectionWithCallback(KEY, bundle, callback, true, true);
    }

    /**
     * Call Fragment callBack
     *
     * @param KEY      the key for fragment on class VtcFragmentFactory.class
     * @param bundle   put data using bundle
     * @param callback return data
     */
    public static void CallFragmentSectionWithCallback(int KEY, Bundle bundle, Callback callback, final boolean isAddBackStack) {
        CallFragmentSectionWithCallback(KEY, bundle, callback, isAddBackStack, true);
    }

    /**
     * Call Fragment callBack
     *
     * @param KEY            the key for fragment on class VtcFragmentFactory.class
     * @param bund           put data using bundle
     * @param callback       return data
     * @param isAddBackStack add back stack yes or no
     * @param isAddFrag      the replace for view or add view
     */
    public static void CallFragmentSectionWithCallback(final int KEY, final Bundle bund, final Callback callback, final boolean isAddBackStack, final boolean isAddFrag) {

        FragmentActivity activity = getmActivity();

        if (activity == null || activity.isFinishing()) {
            return;
        }

        try {

            Const.UI_CURRENT_CONTEXT = KEY;

            System.gc();

            FragmentTransaction fragmentTS = activity.getSupportFragmentManager().beginTransaction();

            // Get Fragment By Key, Call Back Listener
            Fragment fragment = VtcFragmentFactory.getFragmentWithCallbackByKey(KEY, callback);

            if (fragment != null) {

                try {

                    // Set Bundle Data
                    fragment.setArguments(bund);

                    // Init Animation for Fragment view
                    fragmentTS.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);

                    if (isAddFrag) {

                        // Add Fragment into tmp
                        fragmentTS.add(R.id.fragcontent, fragment);
                    } else {

                        // Add Fragment into Full Screen
                        fragmentTS.add(android.R.id.content, fragment);
                    }

                    if (isAddBackStack) {

                        // Add Fragment into back stack
                        fragmentTS.addToBackStack(fragment.getClass().getName());
                    }

                    fragmentTS.commit();

                } catch (IllegalStateException e) {

                    fragmentTS.show(fragment);
                }
            }

        } catch (Exception e) {
            AppCoreBase.showLog(TAG + " Exception : " + e.getMessage());
        }
    }

    /**
     * <p>Call when wan using dialog message, confirm, option..</p>
     * <p/>
     * <p>Type call Dialog</p>
     * <p/>
     * <p>TYPE_SHOW_MESSAGE_INFO</p>
     * <p>TYPE_SHOW_MESSAGE_CONFIRM</p>
     * <p>TYPE_SHOW_CALENDAR</p>
     * <p>TYPE_SHOW_MESSAGE_NEW_JOB_FAST</p>
     * <p>TYPE_SHOW_CHOICE_CITY_DISTRICT</p>
     * <p>TYPE_SHOW_SHORTED_BY</p>
     * <p>TYPE_SHOW_CHOICE_IMAGE</p>
     * <p>TYPE_SHOW_CHOICE_SKILL</p>
     * <p>TYPE_SHOW_ENABLE_NETWORK</p>
     * <p>TYPE_SHOW_ENABLE_GPS</p>
     * <p>TYPE_SHOW_MESSAGE_NEW_JOB_NORMAL</p>
     * <p>TYPE_SHOW_MESSAGE_JOB_OVER_TIME</p>
     * <p>TYPE_SHOW_MESSAGE_RULE_LEVEL</p>
     * <p>TYPE_SHOW_MESSAGE_REPORT</p>
     * <p>TYPE_SHOW_MESSAGE_AGENCY_LIST</p>
     *
     * @param context context
     * @param typekey Key type call dialog
     */
    public void initShowDialogOption(final Activity context, final TypeShowDialog typekey) {

        initShowDialogOption(context, typekey, null);
    }


    /**
     * <p>Call when wan using dialog message, confirm, option..</p>
     * <p/>
     * <p>All key Bundle</p>
     * <p/>
     * <p>key typeProcess = type for process acton Dialog</p>
     * <p>key title = title for dialog</p>
     * <p>key msg = message for dialog</p>
     * <p>key listChoice = List data List<VtcModelFields></p>
     * <p>key listDataChild = List data HashMap<String, List<VtcModelFields>></p>
     * <p>key agencyList = List data List<VtcModelOrder.Agency></p>
     * <p/>
     * <p>Type call Dialog</p>
     * <p/>
     * <p>TYPE_SHOW_MESSAGE_INFO</p>
     * <p>TYPE_SHOW_MESSAGE_CONFIRM</p>
     * <p>TYPE_SHOW_CALENDAR</p>
     * <p>TYPE_SHOW_MESSAGE_NEW_JOB_FAST</p>
     * <p>TYPE_SHOW_CHOICE_CITY_DISTRICT</p>
     * <p>TYPE_SHOW_SHORTED_BY</p>
     * <p>TYPE_SHOW_CHOICE_IMAGE</p>
     * <p>TYPE_SHOW_CHOICE_SKILL</p>
     * <p>TYPE_SHOW_ENABLE_NETWORK</p>
     * <p>TYPE_SHOW_ENABLE_GPS</p>
     * <p>TYPE_SHOW_MESSAGE_NEW_JOB_NORMAL</p>
     * <p>TYPE_SHOW_MESSAGE_JOB_OVER_TIME</p>
     * <p>TYPE_SHOW_MESSAGE_RULE_LEVEL</p>
     * <p>TYPE_SHOW_MESSAGE_REPORT</p>
     * <p>TYPE_SHOW_MESSAGE_AGENCY_LIST</p>
     *
     * @param context context
     * @param typekey Key type call dialog
     * @param bundle  bundle data
     */
    public void initShowDialogOption(final Activity context, final TypeShowDialog typekey, final Bundle bundle) {

        if (context == null || context.isFinishing()) {
            return;
        }

        if (mHandler != null) {
            mHandler = null;
        }

//        Bundle bundle = new Bundle();
//        bundle.putString("typeProcess", typeProcess);
//        bundle.putString("title", title);
//        bundle.putString("msg", msg);

        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {

                String typeProcess = "";
                String title = "";
                String msg = "";

                if (bundle != null) {
                    typeProcess = bundle.getString("typeProcess");
                    title = bundle.getString("title");
                    msg = bundle.getString("msg");
                }
                if (typeProcess == null) {
                    typeProcess = "";
                }
                if (title == null) {
                    title = "";
                }
                if (msg == null) {
                    msg = "";
                }

                switch (typekey) {
                    case TYPE_SHOW_MESSAGE_INFO:
                        initShowMessageInfo(context, title, msg);
                        break;

                    case TYPE_SHOW_MESSAGE_CONFIRM:
                        initShowMessageAlert(context, title, msg, typeProcess);
                        break;

                    case TYPE_SHOW_CALENDAR:
                        initShowCalendar(context, typeProcess);
                        break;

                    case TYPE_SHOW_CHOICE_IMAGE:
                        initShowSelectPhoto(context);
                        break;
                    case TYPE_SHOW_ENABLE_NETWORK:
                    case TYPE_SHOW_ENABLE_GPS:
                        initShowMessageConfirmNetwork(typekey);
                        break;
                }
                mHandler = null;
            }
        };

        if (mHandler != null) {
            Message message = mHandler.obtainMessage();
            message.sendToTarget();
        }
    }

    public static void gaTrackScreen(String screenName) {
        if (getmTracker() == null) {
            return;
        }
        getmTracker().setScreenName(String.valueOf(Const.OS_TYPE + " " + screenName));
        getmTracker().send(new HitBuilders.ScreenViewBuilder().build());
    }

    public static void gaTrackEvent(String category, String action, String lable) {
        if (getmTracker() == null) {
            return;
        }
        getmTracker().send(new HitBuilders.EventBuilder()
                .setCategory(String.valueOf(Const.OS_TYPE + " " + category))
                .setAction(String.valueOf("CLICK " + action))
                .setLabel(String.valueOf(lable))
                .build());
    }


    /**
     * show the custom dialog
     *
     * @param context
     * @param title
     * @param message
     * @param okButton
     * @param cancelButton
     * @param okListenner
     * @param cancelListener
     */
    protected void initShowCustomAlertDialog(Activity context, String title, String message,
                                             String okButton, String cancelButton, DialogInterface.OnClickListener okListenner,
                                             DialogInterface.OnClickListener cancelListener) {
        if (context == null) {
            return;
        }
        if (context.isFinishing()) {
            return;
        }

        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog = null;
        }

        dialog = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.DialogTheme))
                .setCancelable(false)
                .setTitle(title.equals("") ? context.getResources().getString(R.string.title_dialog_confirm) : title)
                .setMessage(message)
                .setPositiveButton(okButton, (okListenner != null) ?
                        okListenner : new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.cancel();
                    }
                }).setNegativeButton(cancelButton, (cancelListener != null) ?
                        cancelListener : new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    /**
     * Called when show dialog calendar.
     * Show Dialog choice date
     */
    private void initShowCalendar(final Activity context, String msg) {

        boolean isTrueFromToday = Boolean.FALSE;
        if (msg.equals("true")) {
            isTrueFromToday = Boolean.TRUE;
        }
        int screen[] = Utils.getSizeScreen(context);

        DlCalendar dlCalendar = new DlCalendar(context, screen[0], isTrueFromToday);

        dlCalendar.setOnClickItemCalendar(new DlCalendar.onClickItemCalendar() {
            @Override
            public void onClickItem(boolean TYPE_FROM_TO, String dateDisplay, String dateSendSV) {
                cmdOnSetTime(TYPE_FROM_TO, dateDisplay, dateSendSV);
            }
        });

        dlCalendar.show();
    }

    /**
     * Call dialog when select photo
     *
     * @param context
     */
    public void initShowSelectPhoto(Context context) {

        int[] size = Utils.getSizeScreen((Activity) context);
        DlChoicePhoto dlTime = new DlChoicePhoto(context, size[0]);
        dlTime.setOnClickDialogPhoto(new DlChoicePhoto.IActionDialogPhoto() {
            @Override
            public void onClickGetPictureLibrary() {
                cmdOnLibrary();
            }

            @Override
            public void onClickGetCamera() {
                cmdOnCapture();
            }
        });
        dlTime.show();
    }

    /**
     * Call dialog when show dialog message info
     *
     * @param context context
     * @param smg     content message
     */
    private void initShowMessageInfo(Activity context, String title, String smg) {

        if (context == null) {
            return;
        }
        if (context.isFinishing()) {
            return;
        }

        if (dialog != null) {

            if (dialog.isShowing()) {
                dialog.cancel();
            }
            dialog = null;
        }

        dialog = new AlertDialog.Builder(context)
                .setTitle(title.equals("") ? context.getResources().getString(R.string.title_dialog_message) : title)
                .setMessage(smg)
                .setCancelable(false)
                .setPositiveButton(R.string.btn_accept, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        cmdPressDialogYesInfo();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    /**
     * Call dialog when show dialog message Alert
     *
     * @param context
     * @param smg
     */
    private void initShowMessageAlert(Activity context, String title, final String smg, final String typeProcess) {

        if (context == null) {
            return;
        }
        if (context.isFinishing()) {
            return;
        }
        if (dialog != null) {

            if (dialog.isShowing()) {
                dialog.cancel();
            }
            dialog = null;
        }


        dialog = new AlertDialog.Builder(context)
                .setTitle(title.equals("") ? context.getResources().getString(R.string.title_dialog_confirm) : title)
                .setMessage(smg)
                .setCancelable(false)
                .setPositiveButton(R.string.btn_accept, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (typeProcess.equals("") || typeProcess.equals("-1")) {
                            cmdPressDialogYes();
                        } else {
                            cmdPressDialogYes(typeProcess);
                        }
                    }
                }).setNegativeButton(R.string.btn_huy, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cmdPressDialogNo();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    /**
     * Call dialog when show dialog message Alert
     *
     * @param smg
     */
//    private void initShowMessageNewJobNormal(final String smg, final String response) {
//        final Activity activity = getmActivity();
//
//        if (activity == null) {
//            return;
//        }
//
//        if (activity.isFinishing()) {
//            return;
//        }
//
//        if (dialog != null) {
//
//            if (dialog.isShowing()) {
//                dialog.cancel();
//            }
//            dialog = null;
//        }
//
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                dialog = new AlertDialog.Builder(activity)
//                        .setTitle(activity.getResources().getString(R.string.title_dialog_confirm))
//                        .setMessage(smg)
//                        .setCancelable(false)
//                        .setPositiveButton(R.string.btn_job_detail, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                Utils.hideKeyboard(activity);
//                                cmdPressViewDetail(response);
//                            }
//                        }).setNegativeButton(R.string.btn_huy, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                            }
//                        })
//                        .setIcon(android.R.drawable.ic_dialog_info)
//                        .show();
//            }
//        });
//    }

    /**
     * Call dialog when show dialog message Alert setting network and GPS
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("NewApi")
    public void initShowMessageConfirmNetwork(TypeShowDialog typekey) {
        final Activity activity = getmActivity();

        if (activity == null) {
            return;
        }
        if (activity.isFinishing()) {
            return;
        }

        if (alertDialogConfirmNetWork != null) {
            if (alertDialogConfirmNetWork.isShowing()) {
                return;
            }
            alertDialogConfirmNetWork = null;
        }

        int screen[] = Utils.getSizeScreen(activity);

        alertDialogConfirmNetWork = new DlMessage(activity, screen[0], typekey);

        alertDialogConfirmNetWork.setOnClickDialogItem(new DlMessage.onClickDialogItem() {
            @Override
            public void onClickDialogItemCancel() {
                alertDialogConfirmNetWork.dismiss();
                if (getGpsTracker(activity, "initShowMessageConfirmNetwork").isConnection() == Integer.MIN_VALUE) {
                    cmdOnRefreshLocation("");
                } else {
                    activity.finish();
                }
            }

            @Override
            public void onClickDialogItemEnableWifi() {
                alertDialogConfirmNetWork.dismiss();
                Intent myIntent = new Intent(Settings.ACTION_SETTINGS);
                activity.startActivity(myIntent);
            }

            @Override
            public void onClickDialogItemEnableGPS() {
                alertDialogConfirmNetWork.dismiss();
                Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                activity.startActivity(myIntent);
            }
        });

        alertDialogConfirmNetWork.show();
    }

    public static boolean initReceived(int key) {
        return initReceived(key, -1, null, -1);
    }

    public static boolean initReceived(int key, Object object) {
        return initReceived(key, -1, object, -1);
    }

    public static boolean initReceived(int key, int arg1, Object object, int arg2) {
        if (AppCore.handlerReceiveMessageAction != null) {
            android.os.Message msg = AppCore.handlerReceiveMessageAction.obtainMessage();
            msg.what = key;
            msg.obj = object;
            msg.arg1 = arg1;
            msg.arg2 = arg2;
            AppCore.handlerReceiveMessageAction.sendMessage(msg);
            return true;
        }
        return false;
    }

    /**
     * Show Log toast
     * Enable when DEBUG is true
     *
     * @param view
     * @param msg  content message
     */
    public static void showLogToast(View view, String msg) {
        if (BuildConfig.DEBUG) {
//            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    /**
     * Show toast Long for message
     *
     * @param view
     * @param msg  content message
     */
    public static void showToastLong(View view, String msg) {
//        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    /**
     * Show toast Short for message
     *
     * @param view
     * @param msg  content message
     */
    public static void showToastShort(View view, String msg) {
//        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    /**
     * Show Log Bug
     * Enable when DEBUG is true
     *
     * @param msg content log
     */
    public static void showLog(String msg) {
        if (BuildConfig.DEBUG) {
            Log.i("Ha : ", "----------------- : " + msg);
        }
    }

    /**
     * Show Log Bug for Tag
     * Enable when DEBUG is true
     *
     * @param tag Tag Name
     * @param msg content log
     */
    public static void showLog(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, "----------------- : " + msg);
        }
    }

    /**
     * Call when action on message info yes
     */
    public void cmdPressDialogYesInfo() {

    }

    /**
     * Call when action on message info yes
     */
    public void cmdPressDialogYes(String key) {

    }

    /**
     * Call when action on message info yes
     */
    public void cmdPressDialogYes() {

    }

    /**
     * Call when action View Detail job
     */
    public void cmdPressViewDetail(String response) {

    }

    /**
     * Call when action on message info No
     */
    public void cmdPressDialogNo() {

    }

    /**
     * Call when action Get location
     */
    protected void cmdOnGetLocation(LatLng destPosition) {

    }

    /**
     * Call when action Refresh Location
     */
    protected void cmdOnRefreshLocation(String description) {

    }

    /**
     * Call when action Capture
     */
    protected void cmdOnCapture() {

    }

    /**
     * Call when action Choice Library
     */
    protected void cmdOnLibrary() {

    }

    /**
     * Call when action setTime from dialog calendar
     */
    protected void cmdOnSetTime(boolean TYPE_FROM_TO, String dateDisplay, String dateSendSV) {

    }

    /**
     * Call when back screen
     */
    public void cmdBack() {
        FragmentActivity activity = getmActivity();
        if (activity != null) {
            activity.getSupportFragmentManager().popBackStack();
        }
    }

    public static void initToolsBar(final Activity context, final boolean isTrue) {
        if (context == null) {
            return;
        }

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Window window = context.getWindow();

                    // clear FLAG_TRANSLUCENT_STATUS flag:
                    window.clearFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

                    // finally change the color
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
                        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                        if (isTrue) {
                            window.setStatusBarColor(context.getResources().getColor(R.color.colorPrimary));
                        } else {
                            window.setStatusBarColor(context.getResources().getColor(android.R.color.transparent));
                        }
                    }
                }
            }
        });
    }
}
