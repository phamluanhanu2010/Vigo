/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/21/16 1:47 PM
 */

package com.strategy.intecom.vtc.vigo.interfaces;

import com.strategy.intecom.vtc.vigo.enums.TypeActionConnection;

/**
 * Created by Mr. Ha on 5/19/16.
 *
 * @author Mr. Ha
 */
public interface RequestListener {

    void onPostExecuteConnectError(TypeActionConnection keyType);

    void onPostExecuteError(TypeActionConnection key_connect, int errorCode, String msg);

    void onPostExecuteSuccess(TypeActionConnection key_connect, String response, String message);
}
