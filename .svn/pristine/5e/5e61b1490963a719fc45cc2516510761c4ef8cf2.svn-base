/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/11/16 2:17 PM
 */

package com.strategy.intecom.vtc.vigo.config;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.enums.TypeActionConnection;
import com.strategy.intecom.vtc.vigo.interfaces.RequestListener;
import com.strategy.intecom.vtc.vigo.utils.ParserJson;
import com.strategy.intecom.vtc.vigo.view.base.AppCore;

import org.json.JSONObject;

/**
 * Created by Mr. Ha on 6/2/16.
 *
 * @author Mr. Ha
 */
public class VtcNWConnection extends VtcHttpConnection {

    private ProgressDialog progress;

    private Handler mHandler;

    public VtcNWConnection(Activity context, RequestListener requestConnection) {
        super(context, requestConnection);
    }

    /**
     * <d>Call when request Api server</d>
     * <d>show dialog process</d>
     */
    private void initShowDialogProcess() {

        if (mHandler != null) {
            mHandler = null;
        }

        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {

                if (progress != null) {

                    if (progress.isShowing()) {
                        progress.cancel();
                    }
                    progress = null;
                }
                if (getContext() != null) {
                    getContext().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progress = ProgressDialog.show(getContext(),
                                    getContext().getResources().getString(R.string.title_dialog_message),
                                    getContext().getResources().getString(R.string.title_dialog_process_confirm), true);

                            mHandler = null;
                        }
                    });
                }
            }
        };

        if (mHandler != null) {
            Message message = mHandler.obtainMessage();
            message.sendToTarget();
        }
    }

    /**
     * <d>Call when request Api server</d>
     * <d>Dismiss dialog process</d>
     */
    private void initCloseDialogProcess() {

        if (progress != null && progress.isShowing()) {

            progress.dismiss();
            progress = null;
        }
    }

    /**
     * <d>Call when wan connect server request API</d>
     *
     * @param actionConnection Type connection you wan request
     * @param sAPi             link Api connect to server
     * @param isPost           setting method post
     */
    public void onExecuteProcess(TypeActionConnection actionConnection, String sAPi, boolean isPost) {

        onExecuteProcess(actionConnection, sAPi, null, isPost, true);
    }

    /**
     * <d>Call when wan connect server request API</d>
     *
     * @param actionConnection Type connection you wan request
     * @param sAPi             link Api connect to server
     */
    public void onExecuteProcess(TypeActionConnection actionConnection, String sAPi) {

        onExecuteProcess(actionConnection, sAPi, null, true, true);
    }

    /**
     * <d>Call when wan connect server request API</d>
     *
     * @param actionConnection Type connection you wan request
     * @param parameter        out put
     * @param sAPi             link Api connect to server
     */
    public void onExecuteProcess(TypeActionConnection actionConnection, String sAPi, JSONObject parameter) {

        onExecuteProcess(actionConnection, sAPi, parameter, true, true);
    }

    /**
     * <d>Call when wan connect server request API</d>
     *
     * @param actionConnection Type connection you wan request
     * @param parameter        out put
     * @param sAPi             link Api connect to server
     * @param isPost           setting method post
     */
    public void onExecuteProcess(TypeActionConnection actionConnection, String sAPi, JSONObject parameter, boolean isPost) {

        onExecuteProcess(actionConnection, sAPi, parameter, isPost, true);
    }

    /**
     * <d>Call when wan connect server request API</d>
     *
     * @param actionConnection Type connection you wan request
     * @param sAPi             link Api connect to server
     * @param param            out put
     * @param isShowProcess    = true then show dialog process,
     *                         = false then dismiss dialog process.
     */
    public void onExecuteProcess(TypeActionConnection actionConnection, String sAPi, JSONObject param, boolean isPost, boolean isShowProcess) {

        VtcModelConnect vtcModelConnect = new VtcModelConnect();
        vtcModelConnect.setActionConnection(actionConnection);
        vtcModelConnect.setAPI(sAPi);
        vtcModelConnect.setParameter(param);
        vtcModelConnect.setPost(isPost);
        vtcModelConnect.setShowProcess(isShowProcess);

        onExecute(vtcModelConnect);
    }

    /**
     * <d>Call when wan connect server request API</d>
     *
     * @param actionConnection Type connection you wan request
     * @param sAPi             link Api connect to server
     * @param file             out put list file
     */
    public void onExecuteProcess(TypeActionConnection actionConnection, String sAPi, String file, boolean isShowProcess) {

        VtcModelConnect vtcModelConnect = new VtcModelConnect();
        vtcModelConnect.setActionConnection(actionConnection);
        vtcModelConnect.setAPI(sAPi);
        vtcModelConnect.setFile(file);

        vtcModelConnect.setShowProcess(isShowProcess);

        onExecute(vtcModelConnect);
    }

    private void onExecute(VtcModelConnect vtcModelConnect) {
        ProcessConnection execute = new ProcessConnection(vtcModelConnect);

        if (execute.getStatus() == AsyncTask.Status.RUNNING) {

            setPoolQueue(vtcModelConnect.getAPI(), vtcModelConnect);
        } else {

            int isStatusNetWork = AppCore.getGpsTracker(getContext(), "onExecute " + vtcModelConnect.getAPI()).isConnection();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

                execute.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, isStatusNetWork);
            } else {

                execute.execute(isStatusNetWork);
            }
        }
    }

    /**
     * <d>Call when wan connect server request API</d>
     * <p>
     * <d>Thread process connect and receiver data from server</d>
     */
    private class ProcessConnection extends AsyncTask<Integer, String, String> {

        private VtcModelConnect vtcModelConnect;

        public ProcessConnection(VtcModelConnect vtcModelConnect) {
            this.vtcModelConnect = vtcModelConnect;
        }

        private boolean isCheckNull() {
            if (vtcModelConnect == null) {
                return false;
            }
            return true;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (isCheckNull() && vtcModelConnect.isShowProcess()) {
                initShowDialogProcess();
            }
        }

        @Override
        protected String doInBackground(Integer... params) {
            String sData = "";

            int typeNetwork = params[0];

            if (typeNetwork == Integer.MIN_VALUE) {
                if (isCheckNull()) {

                    switch (vtcModelConnect.getActionConnection()) {
                        case TYPE_ACTION_UPLOAD_AVATAR:

                            return uploadFile(vtcModelConnect.getFile(), vtcModelConnect.getAPI());
                        default:

                            String parameter = "";

                            if (vtcModelConnect.getParameter() != null) {
                                parameter = String.valueOf(vtcModelConnect.getParameter());
                            }

                            AppCore.showLog("---------- : doInBackground " + vtcModelConnect.getAPI() + " -- " + parameter);

                            return initRequestConnection(vtcModelConnect.getAPI(), parameter, vtcModelConnect.isPost());
                    }
                } else {
                    setErrorConnection(TypeActionConnection.TYPE_CONNECTION_ERROR);
                }

            } else if (typeNetwork == 1) {
                // No GPS Connection
                setErrorConnection(TypeActionConnection.TYPE_CONNECTION_NO_GPS);
            } else if (typeNetwork == 2) {
                // No Internet Connection
                setErrorConnection(TypeActionConnection.TYPE_CONNECTION_NO_INTERNET);
            }

            return sData;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (getRequestConnection() != null) {

                if (getErrorConnection() == TypeActionConnection.TYPE_CONNECTION && !s.equals("")) {

                    if (ParserJson.getStatusSuccess(s)) {

                        getRequestConnection().onPostExecuteSuccess(vtcModelConnect.getActionConnection(), ParserJson.getResponseData(s), ParserJson.getStatusMsg(s));
                    } else {
                        int errorCode = ParserJson.getErrorCode(s);

                        getRequestConnection().onPostExecuteError(vtcModelConnect.getActionConnection(), errorCode, ParserJson.getStatusMsg(s));
                    }
                } else {
                    getRequestConnection().onPostExecuteConnectError(getErrorConnection());
                }
            } else {
                getRequestConnection().onPostExecuteConnectError(getErrorConnection());
            }

//            if (vtcModelConnect.isShowProcess()) {
            initCloseDialogProcess();
//            }

            if (getApiQueueSize() > 0) {

                String sApi = getApiQueue();

                onExecute(getVtcModelConnect(sApi));
            }
        }
    }
}
