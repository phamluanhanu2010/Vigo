/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/19/16 8:42 AM
 */

package com.strategy.intecom.vtc.vigo.config;

import android.app.Activity;

import com.strategy.intecom.vtc.vigo.BuildConfig;
import com.strategy.intecom.vtc.vigo.enums.TypeActionConnection;
import com.strategy.intecom.vtc.vigo.interfaces.RequestListener;
import com.strategy.intecom.vtc.vigo.view.base.AppCore;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Mr. Ha on 6/2/16.
 *
 * @author Mr. Ha
 */
public class VtcHttpConnection {

    private static String URL_CONNECT_SERVER = VtcHttpConnection.URL_CONNECT_SERVER_RELEASE;
    private static String URL_CONNECT_SERVER_SOCKET = VtcHttpConnection.URL_CONNECT_SOCKET_RELEASE;

    private static final String URL_CONNECT_SERVER_DEBUG = "http://117.103.207.42";

    private static final String URL_CONNECT_SERVER_RELEASE = "https://api.suado.vn";
    private static final String URL_CONNECT_SOCKET_RELEASE = "https://ws.suado.vn";

    public static final String URL_CONNECT_LINK_IMAGE = "http://files.suado.vn/";

    private final int CONNECT_TIME_OUT = 1000 * 30;

    private Activity context;

    private RequestListener requestConnection;

    private TypeActionConnection errorConnection = TypeActionConnection.TYPE_CONNECTION;

    private BlockingQueue<String> pollQueue = new LinkedBlockingQueue<>();
    private Map<String, VtcModelConnect> mapQueue = new HashMap<>();

    protected VtcModelConnect getVtcModelConnect(String sAPI) {

        if (mapQueue != null) {
            return mapQueue.get(sAPI);
        }
        return null;
    }

    protected String getApiQueue() {

        if (pollQueue != null) {
            return pollQueue.poll();
        }
        return "";
    }

    protected int getApiQueueSize() {

        if (pollQueue != null) {
            return pollQueue.size();
        }
        return 0;
    }

    protected void setPoolQueue(String sAPI, VtcModelConnect vtcModelConnect) {

        if (pollQueue != null) {
            pollQueue.add(sAPI);
        }

        if (mapQueue != null) {
            mapQueue.put(sAPI, vtcModelConnect);
        }
    }

    static {
        if (BuildConfig.DEBUG) {
            //////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////
            /////////////// TEST
            //////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////
//            VtcHttpConnection.URL_CONNECT_SERVER = VtcHttpConnection.URL_CONNECT_SERVER_DEBUG + ":8888";
//            VtcHttpConnection.URL_CONNECT_SERVER_SOCKET = VtcHttpConnection.URL_CONNECT_SERVER_DEBUG + ":3000";


            //////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////
            /////////////// RELEASE
            //////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////
            VtcHttpConnection.URL_CONNECT_SERVER = VtcHttpConnection.URL_CONNECT_SERVER_RELEASE;
            VtcHttpConnection.URL_CONNECT_SERVER_SOCKET = VtcHttpConnection.URL_CONNECT_SOCKET_RELEASE;

        } else {
            //////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////
            /////////////// PRODUCTIONS
            //////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////
            VtcHttpConnection.URL_CONNECT_SERVER = VtcHttpConnection.URL_CONNECT_SERVER_RELEASE;
            VtcHttpConnection.URL_CONNECT_SERVER_SOCKET = VtcHttpConnection.URL_CONNECT_SOCKET_RELEASE;
        }
    }

    protected Activity getContext() {
        return context;
    }

    protected RequestListener getRequestConnection() {
        return requestConnection;
    }

    protected TypeActionConnection getErrorConnection() {
        return errorConnection;
    }

    protected void setErrorConnection(TypeActionConnection errorConnection) {
        this.errorConnection = errorConnection;
    }

    protected VtcHttpConnection(Activity context, RequestListener requestConnection) {
        this.context = context;
        this.requestConnection = requestConnection;
    }

    protected String initRequestConnection(String sApi, String urlParameters, boolean post_get) {
        try {

            String utf = "UTF-8";
            Charset charset = Charset.forName(utf);
            URL url = new URL(VtcHttpConnection.URL_CONNECT_SERVER + sApi);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json; charset=" + utf);
            connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
            connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
            connection.setRequestProperty("charset", utf);
            connection.setConnectTimeout(CONNECT_TIME_OUT);

            if (post_get) {
                connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
                connection.setRequestMethod(String.valueOf("POST"));
                connection.setDoOutput(true);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), charset));
                bw.write(urlParameters);
                bw.flush();
                bw.close();
            } else {
                connection.setRequestMethod(String.valueOf("GET"));
            }

            int responseCode = connection.getResponseCode();

            AppCore.showLog("----responseCode----- : " + responseCode + " ------ : " + connection.getRequestMethod());

            switch (responseCode) {

                case HttpURLConnection.HTTP_OK:

                    final StringBuilder output = new StringBuilder("Request URL " + url);
                    output.append(System.getProperty("line.separator") + "Request Parameters " + urlParameters);
                    output.append(System.getProperty("line.separator") + "Response Code " + responseCode);
                    output.append(System.getProperty("line.separator") + "Type " + connection.getRequestMethod());
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
                    String line = "";
                    StringBuilder responseOutput = new StringBuilder();
                    AppCore.showLog("output===============" + br);
                    while ((line = br.readLine()) != null) {
                        responseOutput.append(line);
                    }
                    br.close();

                    output.append(System.getProperty("line.separator") + "Response " + System.getProperty("line.separator") + System.getProperty("line.separator") + responseOutput.toString());

                    AppCore.showLog("------ : " + output.toString());

                    connection.disconnect();
                    setErrorConnection(TypeActionConnection.TYPE_CONNECTION);
                    return responseOutput.toString();
                case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:

                    setErrorConnection(TypeActionConnection.TYPE_CONNECTION_TIMEOUT); // retry

                    return "";
                case HttpURLConnection.HTTP_UNAVAILABLE:

                    setErrorConnection(TypeActionConnection.TYPE_CONNECTION_NOT_CONNECT_SERVER); // retry, server is unstable

                    return "";
                default:

                    setErrorConnection(TypeActionConnection.TYPE_CONNECTION_ERROR); // abort

                    return "";
            }
        } catch (ConnectException e) {
            setErrorConnection(TypeActionConnection.TYPE_CONNECTION_NOT_CONNECT_SERVER);
            AppCore.showLog("--initRequestConnection ConnectException---- : " + e.getMessage());
        } catch (ConnectTimeoutException e) {
            setErrorConnection(TypeActionConnection.TYPE_CONNECTION_TIMEOUT);
            AppCore.showLog("--initRequestConnection ConnectTimeoutException---- : " + e.getMessage());
        } catch (ProtocolException e) {
            setErrorConnection(TypeActionConnection.TYPE_CONNECTION_ERROR);
            AppCore.showLog("--initRequestConnection ProtocolException---- : " + e.getMessage());
        } catch (IOException e) {
            setErrorConnection(TypeActionConnection.TYPE_CONNECTION_ERROR);
            AppCore.showLog("---initRequestConnection IOException--- : " + e.getMessage());
        } catch (Exception e) {
            setErrorConnection(TypeActionConnection.TYPE_CONNECTION_ERROR);
            AppCore.showLog("---initRequestConnection Exception--- : " + e.getMessage());
        }
        return "";
    }

    public static String readUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            br.close();

        } catch (Exception e) {
            AppCore.showLog("Exception while reading url : " + e.toString());
        } finally {
            if (iStream != null) {
                iStream.close();
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return data;
    }

    public static byte[] urlParameterJson(Map<?, ?> map) {
        JSONObject jsonObject = new JSONObject();
        try {
            return jsonObject.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    public static String urlEncodeUTF8(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        try {
            if (map != null && !map.isEmpty()) {
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    if (entry != null) {
                        if (sb.length() > 0) {
                            sb.append("&");
                        } else {
                            sb.append("?");
                        }

                        String value = "";

                        String key = "";

                        if (entry.getValue() != null) {
                            value = String.valueOf(entry.getValue());
                        }
                        if (entry.getKey() != null) {
                            key = String.valueOf(entry.getKey());
                        }

                        String encodeValue = "";
                        String encodeKey = "";

                        if (null != value && !value.isEmpty()) {
                            encodeValue = urlEncodeUTF8(value);
                        }
                        if (null != key && !key.isEmpty()) {
                            encodeKey = urlEncodeUTF8(key);
                        }

                        sb.append(String.format("%s=%s", encodeKey, encodeValue));
                    }
                }
            }
        } catch (NumberFormatException e) {

        }
        return sb.toString();
    }

    private static String urlEncodeUTF8(String s) {
        try {
            if (s != null && !s.isEmpty()) {
                s = URLEncoder.encode(s, "UTF-8");
            } else {
                s = "";
            }
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String uploadFile(String imgPath, String sAPI) {
        final File uploadFile = new File(imgPath);
        try {
            final MultipartUtility http = new MultipartUtility(getContext(), getRequestConnection(), new URL(URL_CONNECT_SERVER + sAPI));
            http.addFormField("fileName", uploadFile.getName());
            http.addFormField("mimeType", "image/jpeg");
            http.addFilePart("file", uploadFile);
            return http.finish();
        } catch (IOException e) {
            e.printStackTrace();
            AppCore.showLog("-----uploadFile------------ : " + e.getMessage());
        }

        return "";
    }

}
