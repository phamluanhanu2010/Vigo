/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/31/16 1:31 PM
 */

package com.strategy.intecom.vtc.vigo.view.base;

import android.os.Bundle;

import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.config.VtcNWConnection;
import com.strategy.intecom.vtc.vigo.enums.TypeActionConnection;
import com.strategy.intecom.vtc.vigo.enums.TypeShowDialog;
import com.strategy.intecom.vtc.vigo.interfaces.RequestListener;
import com.strategy.intecom.vtc.vigo.utils.ParserJson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Mr. Ha on 6/29/16.
 *
 * @author Mr. Ha
 */
public class AppCoreAPI extends AppCoreBase implements RequestListener {

    protected void initChangePassword(RequestListener requestListener, String userID, String oldPassword, String newPassword) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put(ParserJson.API_PARAMETER_AUTH_TOKEN, AppCore.getPreferenceUtil(getmActivity()).getValueString(""));

            JSONObject jsonObject = new JSONObject();

            jsonObject.put(ParserJson.API_PARAMETER_OLD_PASSWORD, oldPassword);
            jsonObject.put(ParserJson.API_PARAMETER_NEW_PASSWORD, newPassword);

//            getConnection(requestListener).onExecuteProcess(TypeActionConnection.TYPE_ACTION_CHANGE_PASSWORD, RequestListener.API_CONNECTION_FORGOT_PASSWORD + userID + RequestListener.API_CONNECTION_CHANGE_PASSWORD + VtcNWConnection.urlEncodeUTF8(map), jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPostExecuteConnectError(TypeActionConnection keyType) {
        Bundle bundle = new Bundle();

        switch (keyType) {

            case TYPE_CONNECTION_NOT_CONNECT_SERVER:
                bundle.putString("msg", getmActivity().getResources().getString(R.string.confirm_server_not_found));

                initShowDialogOption(getmActivity(), TypeShowDialog.TYPE_SHOW_MESSAGE_INFO, bundle);
                break;
            case TYPE_CONNECTION_NO_INTERNET:
                initShowDialogOption(getmActivity(), TypeShowDialog.TYPE_SHOW_ENABLE_NETWORK);
                break;
            case TYPE_CONNECTION_NO_GPS:
                initShowDialogOption(getmActivity(), TypeShowDialog.TYPE_SHOW_ENABLE_GPS);
                break;
            case TYPE_CONNECTION_TIMEOUT:
                bundle.putString("msg", getmActivity().getResources().getString(R.string.confirm_server_timeout));

                initShowDialogOption(getmActivity(), TypeShowDialog.TYPE_SHOW_MESSAGE_INFO, bundle);
                break;
            case TYPE_CONNECTION_ERROR:
                bundle.putString("msg", getmActivity().getResources().getString(R.string.confirm_server_error_connect));

                initShowDialogOption(getmActivity(), TypeShowDialog.TYPE_SHOW_MESSAGE_INFO, bundle);
                break;
            default:
                bundle.putString("msg", getmActivity().getResources().getString(R.string.confirm_server_error_connect));

                initShowDialogOption(getmActivity(), TypeShowDialog.TYPE_SHOW_MESSAGE_INFO, bundle);
                break;
        }
    }

    @Override
    public void onPostExecuteError(TypeActionConnection key_connect, int errorCode, String msg) {
        if (!msg.equals("")) {
            Bundle bundle = new Bundle();
            bundle.putString("msg", msg);
            initShowDialogOption(getmActivity(), TypeShowDialog.TYPE_SHOW_MESSAGE_INFO, bundle);
        }
    }

    @Override
    public void onPostExecuteSuccess(TypeActionConnection key_connect, String response, String message) {

    }
}
