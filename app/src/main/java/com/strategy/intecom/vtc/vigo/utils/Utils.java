/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/27/16 7:32 AM
 */

package com.strategy.intecom.vtc.vigo.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.view.base.AppCore;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * Created by Mr. Ha on 5/16/16.
 *
 * @author Mr. Ha
 */
public class Utils {

    private static final String TAG = "Utils";

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    /**
     * Checks if the device has Internet connection.
     *
     * @return <code>true</code> if the phone is connected to the Internet.
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }

        return false;
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    public static boolean checkPlayServices(final Activity context) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(context, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                AppCore.showLog(TAG, "This device is not supported.");
            }
            return false;
        }
        return true;
    }

    @SuppressLint("PackageManagerGetSignatures")
    public static void getHashKey(Context context) {

        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                AppCore.showLog("KeyHash ----- : ", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            AppCore.showLog(" ------ name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            AppCore.showLog(" --------- no such an algorithm " + e.toString());
        } catch (Exception e) {
            AppCore.showLog(" -------- exception" + e.toString());
        }
    }

    /**
     * Convert dp to Pixels
     */
    public static float convertDpToPixels(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    /**
     * Convert Pixel to dp
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }

    /**
     * Get Bitmap Resource
     */
    public static Bitmap getBitmapResource(Context context, int id) {
        if (context == null)
            return null;

        return BitmapFactory.decodeResource(context.getResources(), id);
    }

    /**
     * Get Bitmap Option
     */
    public static BitmapFactory.Options getBitmapOption() {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inDither = false;
        bitmapOptions.inJustDecodeBounds = false;
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
        bitmapOptions.inSampleSize = 1;
        bitmapOptions.inPurgeable = true;
        bitmapOptions.inPreferQualityOverSpeed = true;
        bitmapOptions.inTempStorage = new byte[32 * 1024];
        return bitmapOptions;
    }

    /**
     * Check File
     */
    public static File isFile(String sPath, String sFileName) {
        File f = new File(sPath);
        for (File temp : f.listFiles()) {
            if (temp.getName().equals(sFileName)) {
                return temp;
            }
        }
        return f;
    }

    /**
     * Get Size Screen
     */
    public static int[] getSizeScreen(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        int[] screen = new int[2];

        screen[0] = width;
        screen[1] = height;

        int screenSize = context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                AppCore.showLog("Large screen");
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                AppCore.showLog("Normal screen");
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                AppCore.showLog("Small screen");
                break;
            default:
                AppCore.showLog("Screen size is neither large, normal or small");
        }
        return screen;
    }

    public static void hideKeyboard(Activity activity) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showKeyboard(Activity activity) {
        if (activity == null) {
            return;
        }
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        } catch (RuntimeException e) {
            AppCore.showLog(TAG + " showKeyboard RuntimeException : " + e.getMessage());
        }
    }

    @SuppressLint("HardwareIds")
    public static String getIMEI(Context context) {

        TelephonyManager mngr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return mngr.getDeviceId();
    }

    public static void initPlaySoundDefaultNoti(Activity activity) {
        Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        MediaPlayer mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(activity, defaultRingtoneUri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            mediaPlayer.start();
        } catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        Vibrator v = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(1500);
    }

    public static String getTimeFormatSemple(long timeData) {
        SimpleDateFormat df = new SimpleDateFormat("mm:ss", Locale.getDefault());
        return df.format(timeData);
    }

    public static String initConvertTimeDisplayLong(String strTime) {
        if (strTime == null || strTime.isEmpty()) {
            return "";
        }
        String formattedDate = "";
        try {
            Locale.setDefault(new Locale("vi", "VN"));
            TimeZone tz = TimeZone.getTimeZone("GMT+14:00");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            DateFormat dateFormat2 = new SimpleDateFormat("EE, HH:mm dd/MM/yyyy", Locale.getDefault());
            dateFormat2.setTimeZone(tz);
            Date date = dateFormat.parse(strTime);
            formattedDate = dateFormat2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

    public static String initConvertTimeDisplayNormal(String strTime) {
        if (strTime == null || strTime.isEmpty()) {
            return "";
        }
        String formattedDate = "";
        try {
            Locale.setDefault(new Locale("vi", "VN"));
            TimeZone tz = TimeZone.getTimeZone("GMT+14:00");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            DateFormat dateFormat2 = new SimpleDateFormat("EEEE, dd/MM/yyyy", Locale.getDefault());
            dateFormat2.setTimeZone(tz);
            Date date = dateFormat.parse(strTime);
            formattedDate = dateFormat2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

    public static String initConvertTimeDisplayShort(String strTime) {
        if (strTime == null || strTime.isEmpty()) {
            return "";
        }
        String formattedDate = "";
        try {
            Locale.setDefault(new Locale("vi", "VN"));
            TimeZone tz = TimeZone.getTimeZone("GMT+14:00");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            DateFormat dateFormat2 = new SimpleDateFormat("HH:mm dd/MM", Locale.getDefault());
            dateFormat2.setTimeZone(tz);
            Date date = dateFormat.parse(strTime);
            formattedDate = dateFormat2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

    public static String initConvertTimeBanAcc(String strTime) {

        if (strTime == null || strTime.isEmpty()) {
            return "";
        }
        try {
            Locale.setDefault(new Locale("vi", "VN"));
            TimeZone tz = TimeZone.getTimeZone("GMT+14:00");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            DateFormat dateFormat2 = new SimpleDateFormat("dd/MM", Locale.getDefault());
            dateFormat2.setTimeZone(tz);
            Date date = dateFormat.parse(strTime);
            return dateFormat2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return strTime;
    }

    private static long initGetTimeStamp(String strTime) {
        if (strTime == null || strTime.isEmpty()) {
            return 0;
        }
        try {
            Locale.setDefault(new Locale("vi", "VN"));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
            Date date = dateFormat.parse(strTime);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getCurrentTimeGMT(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(date);
    }

    public static String getCurrentTimeISO(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(date);
    }


    public static String getCurrentTimeISOFromFormat(String strTime) {

        String formattedDate = "";
        try {
            Locale.setDefault(new Locale("vi", "VN"));
            DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.getDefault());
            Date date = originalFormat.parse(strTime);

            formattedDate = targetFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

    private static long initConvertMinute(long time) {
        return (time / 1000) / 60;
    }

    public static String initCheckTime(String strNewTime, String strTime) {

        int minute = 60;

        long newTime = initGetTimeStamp(strNewTime);
        long time = initGetTimeStamp(strTime);

        if (initConvertMinute(newTime - time) > minute || (initConvertMinute(newTime - time) < (minute - (minute * 2)))) {
            return "";
        }

        return strTime;
    }

    public static boolean validateCMTND(String email) {
        return email.matches("^*[0-9]{9,12}");
    }

    public static boolean validateEmail(String email) {
        return email.matches("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$");
    }

    public static boolean validatePhoneNumber(String phoneNo) {
        return phoneNo.matches("^0[1-9][0-9]{8,9}");
    }

//    public static int isNameValid(String name) {
//
//        if (TextUtils.isEmpty(name)) {
//            return Const.VALIDATE_RESULT_NAME_NULL;
//        }
//
//        if (name.length() < Const.VALIDATE_NAME_MIN_LENGTH || name.length() > Const.VALIDATE_NAME_MAX_LENGTH) {
//            return Const.VALIDATE_RESULT_NAME_WRONG_LENGTH;
//        }
//
//        // TODO need to update logic to validate number, space and vietnamese character
//        Pattern p = Pattern.compile("[ a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+");
//
//        if (!p.matcher(name).matches()) {
//            return Const.VALIDATE_RESULT_NAME_NOT_CORRECT;
//        }
//
//        return Const.VALIDATE_RESULT_NAME_OK;
//    }

    public static String formatDecimal(float number) {
//        return String.format(Locale.getDefault(), "%,.0f", number);
        return NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(number);
    }

    public static String convertK(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));

        double result = (count / Math.pow(1000, exp));

        if (result <= ((double) exp)) {
            return String.format(Locale.getDefault(), "%.0f %c", count / Math.pow(1000, exp), "kMGTPE".charAt(exp - 1));
        } else {
            return String.format(Locale.getDefault(), "%.1f %c", count / Math.pow(1000, exp), "kMGTPE".charAt(exp - 1));
        }
    }

    private static final MessageFormat phoneFormatM = new MessageFormat("({0}) {1} {2} {3}");

    public static String formatPhoneNumber(String fot) {
        String[] arr = {
                fot.length() == 10 ? fot.substring(0, 4) : fot.substring(0, 3),
                fot.length() == 10 ? fot.substring(4, 7) : fot.substring(3, 6),
                fot.length() == 10 ? fot.substring(7) : fot.length() >= 11 ? fot.substring(6, 9) : fot.substring(6),
                fot.length() >= 11 ? fot.substring(9) : ""
        };

        return phoneFormatM.format(arr);
    }


    public static String getConvertPrice(Context context, String strPrice) {

        if (context == null) {
            return strPrice;
        }
        if (strPrice == null) {
            strPrice = "0";
        } else if (strPrice.equals("null")) {
            strPrice = "0";
        } else if (strPrice.equals("")) {
            strPrice = "0";
        }

        try {
            float price = Float.parseFloat(strPrice);
            return context.getResources().getString(R.string.title_currency, Utils.formatDecimal(price));
        } catch (NullPointerException e) {
        }

        return context.getResources().getString(R.string.title_currency, strPrice);
    }

    public static int initGetResourceMipmap(Context context, String resName) {
        return initGetResource(context, resName, "mipmap");
    }

    public static int initGetResource(Context context, String resName) {
        return initGetResource(context, resName, "drawable");
    }

    private static int initGetResource(Context context, String resName, String resFolder) {

        return context.getResources().getIdentifier(resName, resFolder, context.getPackageName());
    }

    public static String initTextBold(String str) {
        return String.valueOf("<b>" + str + "</b>");
    }
}
