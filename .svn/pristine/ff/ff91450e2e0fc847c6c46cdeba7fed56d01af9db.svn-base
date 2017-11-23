/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/28/16 4:00 PM
 */

package com.strategy.intecom.vtc.vigo;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;
import android.widget.ListView;

import com.strategy.intecom.vtc.vigo.adt.AdtMainTravelLst;
import com.strategy.intecom.vtc.vigo.dialog.DlChoicePhoto;
import com.strategy.intecom.vtc.vigo.model.VtcModelMainTravel;
import com.strategy.intecom.vtc.vigo.model.VtcModelNoti;
import com.strategy.intecom.vtc.vigo.utils.Const;
import com.strategy.intecom.vtc.vigo.utils.Utils;
import com.strategy.intecom.vtc.vigo.utils.UtilsBitmap;
import com.strategy.intecom.vtc.vigo.view.base.AppCore;
import com.strategy.intecom.vtc.vigo.view.base.AppCoreBase;
import com.strategy.intecom.vtc.vigo.view.base.Controller;
import com.strategy.intecom.vtc.vigo.view.custom.listcontent.ListViewWrapContent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by Mr. Ha on 5/16/16.
 *
 * @author Mr. Ha
 */
public class MainScreen extends FragmentActivity {

    private String FILE_PATH = "";
    private String FILE_NAME = "";

    public Bundle bundle = null;

    private List<VtcModelMainTravel> lstData;
    private ListView lst_main_travel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        bundle = savedInstanceState;

        AppCore.setmActivity(this);

        setContentView(R.layout.ui_main_screen);

        AppCore.setmTracker(((Controller) getApplication()).getDefaultTracker());

        // Create APP_PATH folder
        if (isExternalStorageWritable()) {
            AppCoreBase.APP_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            AppCoreBase.APP_PATH = MainScreen.this.getCacheDir().getAbsolutePath();
        }

        File file = new File(AppCoreBase.APP_PATH, getAlbumName());
        if (!file.exists()) {
            file.mkdirs();
        }

        AppCoreBase.APP_PATH = file.getAbsolutePath();

        Utils.getHashKey(this);

        AppCore.getGpsTracker(this, "MainScreen");

        initController();
    }

    private void initController() {

        initDumpData();

        lst_main_travel = (ListView) findViewById(R.id.lst_main_travel);

        initDataController();
    }

    private void initDataController() {

        int screen[] = Utils.getSizeScreen(this);

        AppCore.showLog("Width---------1------ : " + screen[0]);
        AppCore.showLog("Height--------1------- : " + screen[1]);

        AdtMainTravelLst adtMainTravelLst = new AdtMainTravelLst(this, screen);
        adtMainTravelLst.setData(lstData);

        adtMainTravelLst.setOnClickItem(new AdtMainTravelLst.onClickItem() {
            @Override
            public void onClickItem(VtcModelMainTravel service) {

                Bundle bundle = new Bundle();
                bundle.putParcelable(Const.BUNDLE_TYPE_DATA_TYPE, service);
                AppCore.CallFragmentSection(Const.UI_LIST_IN_TRAVEL, bundle);
            }
        });

        lst_main_travel.setAdapter(adtMainTravelLst);
    }

    private void initDumpData(){
        lstData = new ArrayList<>();

        VtcModelMainTravel vtcModelMainTravel = new VtcModelMainTravel();
        vtcModelMainTravel.setId(Const.KEY_FLAG_TYPE_HANOI);
        vtcModelMainTravel.setTitle(this.getResources().getString(R.string.title_main_travel_hanoi));
        vtcModelMainTravel.setDescription("Hanoi has been the capital city of Vietnam for over 1,000 years. It is undoubtedly one of the must-visit locations in Vietnam. Hanoi is the best starting point of your journey in Vietnam for its extremely...");
        vtcModelMainTravel.setBackgroundResource("ic_main_travel_hanoi");
        lstData.add(vtcModelMainTravel);

        vtcModelMainTravel = new VtcModelMainTravel();
        vtcModelMainTravel.setId(Const.KEY_FLAG_TYPE_HOIAN);
        vtcModelMainTravel.setTitle(this.getResources().getString(R.string.title_main_travel_hoian));
        vtcModelMainTravel.setDescription("Hanoi has been the capital city of Vietnam for over 1,000 years. It is undoubtedly one of the must-visit locations in Vietnam. Hanoi is the best starting point of your journey in Vietnam for its extremely...");
        vtcModelMainTravel.setBackgroundResource("ic_main_travel_hoian");
        lstData.add(vtcModelMainTravel);

        vtcModelMainTravel = new VtcModelMainTravel();
        vtcModelMainTravel.setId(Const.KEY_FLAG_TYPE_DANANG);
        vtcModelMainTravel.setTitle(this.getResources().getString(R.string.title_main_travel_danang));
        vtcModelMainTravel.setDescription("Hanoi has been the capital city of Vietnam for over 1,000 years. It is undoubtedly one of the must-visit locations in Vietnam. Hanoi is the best starting point of your journey in Vietnam for its extremely...");
        vtcModelMainTravel.setBackgroundResource("ic_main_travel_danang");
        lstData.add(vtcModelMainTravel);

        vtcModelMainTravel = new VtcModelMainTravel();
        vtcModelMainTravel.setId(Const.KEY_FLAG_TYPE_QUANGBINH);
        vtcModelMainTravel.setTitle(this.getResources().getString(R.string.title_main_travel_quangbinh));
        vtcModelMainTravel.setDescription("Hanoi has been the capital city of Vietnam for over 1,000 years. It is undoubtedly one of the must-visit locations in Vietnam. Hanoi is the best starting point of your journey in Vietnam for its extremely...");
        vtcModelMainTravel.setBackgroundResource("ic_main_travel_quangbinh");
        lstData.add(vtcModelMainTravel);

        vtcModelMainTravel = new VtcModelMainTravel();
        vtcModelMainTravel.setId(Const.KEY_FLAG_TYPE_NHATRANG);
        vtcModelMainTravel.setTitle(this.getResources().getString(R.string.title_main_travel_nhatrang));
        vtcModelMainTravel.setDescription("Hanoi has been the capital city of Vietnam for over 1,000 years. It is undoubtedly one of the must-visit locations in Vietnam. Hanoi is the best starting point of your journey in Vietnam for its extremely...");
        vtcModelMainTravel.setBackgroundResource("ic_main_travel_nhatrang");
        lstData.add(vtcModelMainTravel);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!this.isFinishing()) {

            if (bundle == null && this.getIntent() != null) {
                bundle = this.getIntent().getExtras();
            }

            initGetDataBundle();

            bundle = null;

            setIntent(null);
        }

    }

    private void initGetDataBundle() {
        if (bundle != null) {
            int type = bundle.getInt("type");
            String id_order = bundle.getString("id_order");
            String message = bundle.getString("message");
            String responseData = bundle.getString("responseData");

            VtcModelNoti vtcModelNoti = new VtcModelNoti();
            vtcModelNoti.setType(type);
            vtcModelNoti.setMessage(message);
            vtcModelNoti.setId_order(id_order);
            vtcModelNoti.setResponseData(responseData);
            vtcModelNoti.setTypeGoto(VtcModelNoti.TYPE_GOTO_MAIN);

            AppCore.initReceived(Const.UI_ACTION_NOTIFICATION, vtcModelNoti);
        }
    }

    /**
     * Call dialog when select photo
     *
     * @param context context current
     */
    public void initShowSelectPhoto(Context context) {

        int[] size = Utils.getSizeScreen((Activity) context);
        DlChoicePhoto dlTime = new DlChoicePhoto(context, size[0]);
        dlTime.setOnClickDialogPhoto(new DlChoicePhoto.IActionDialogPhoto() {
            @Override
            public void onClickGetPictureLibrary() {
                dispatchPictureLibraryIntent(Const.REQUEST_CODE_LOAD_IMAGE_AVATAR);
            }

            @Override
            public void onClickGetCamera() {
                initGetCamera(Const.REQUEST_CODE_CAM_AVATAR, "avatar");
            }
        });
        dlTime.show();
    }

    protected void dispatchPictureLibraryIntent(int requestCode) {

        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), requestCode);

    }

    protected void initGetCamera(int requestCode, String path) {
        final File filetmp = new File(AppCoreBase.APP_PATH, path);
        if (!filetmp.exists()) {
            filetmp.mkdirs();
        }
        FILE_PATH = filetmp.getAbsolutePath();
        FILE_NAME = String.valueOf(System.currentTimeMillis()) + ".jpg";
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File f = new File(FILE_PATH, FILE_NAME);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        startActivityForResult(intent, requestCode);
    }

    private boolean performCropImage(Uri mFinalImageUri, int hight, int width) {
        try {
            if (mFinalImageUri != null) {
                Intent cropIntent = new Intent("com.android.camera.action.CROP");
                cropIntent.setDataAndType(mFinalImageUri, "image/*");
                cropIntent.putExtra("crop", "true");
                cropIntent.putExtra("aspectX", width);
                cropIntent.putExtra("aspectY", hight);
                cropIntent.putExtra("scaleUpIfNeeded", false);
                cropIntent.putExtra("outputX", width * 50);
                cropIntent.putExtra("outputY", hight * 50);
                cropIntent.putExtra("return-data", false);

                FILE_NAME = "CROP_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
                File f = new File(FILE_PATH, FILE_NAME);
                cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(cropIntent, Const.REQUEST_CODE_CROP_IMAGE);
                return true;
            }
        } catch (ActivityNotFoundException anfe) {
            return false;
        }
        return false;
    }

    private String getAlbumName() {
        Activity activity = MainScreen.this;
        if (activity == null) {
            return "";
        }
        return activity.getResources().getString(R.string.app_name);
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {

                case Const.REQUEST_CODE_CAM_AVATAR:
                    File fRequestCamAvatar = UtilsBitmap.isFileOnList(FILE_PATH, FILE_NAME);
                    if (fRequestCamAvatar.exists()) {
                        Uri picUri = Uri.fromFile(fRequestCamAvatar);
                        performCropImage(picUri, 3, 3);
                    }
                    break;
                case Const.REQUEST_CODE_LOAD_IMAGE_AVATAR:
                    if (data != null) {

                        FILE_PATH = UtilsBitmap.getRealPathFromURI(MainScreen.this, data.getData());

                        if (FILE_PATH != null) {
                            String[] strPath = FILE_PATH.split(File.separator);

                            FILE_NAME = strPath[strPath.length - 1];

                            FILE_PATH = FILE_PATH.replace(String.valueOf(File.separator + FILE_NAME), "");

                            if (FILE_PATH != null && !FILE_PATH.isEmpty()) {

                                File fLoadImageAvatar = UtilsBitmap.isFileOnList(FILE_PATH, FILE_NAME);
                                if (fLoadImageAvatar.exists()) {
                                    Uri picUri = Uri.fromFile(fLoadImageAvatar);
                                    performCropImage(picUri, 3, 3);
                                }
                            }
                        }
                    }

                    break;

                case Const.REQUEST_CODE_CROP_IMAGE:

//                    FILE_PATH + File.separator + FILE_NAME

                    break;
            }
        }
    }
}