/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/21/16 1:46 PM
 */

package com.strategy.intecom.vtc.vigo.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Create Mr. Ha on 6/2/16.
 * @author Mr. Ha
 */
public enum TypeActionConnection {

    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    ////  ACTION Connection
    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    TYPE_ACTION(0),

    TYPE_ACTION_LOGIN(1),

    TYPE_ACTION_REGISTER(2),

    TYPE_ACTION_FORGOT_PASSWORD(3),

    TYPE_ACTION_GET_LIST_COMMON_INFO(4),

    TYPE_ACTION_GET_LIST_FIELD(5),

    TYPE_ACTION_GET_ORDER_LIST(6),

    TYPE_ACTION_ORDER_DELETE(7),

    TYPE_ACTION_HIRE_LIST(8),

    TYPE_ACTION_CONFIRM_CODE(9),

    TYPE_ACTION_CONFIRM_PASSCODE(10),

    TYPE_ACTION_GET_PASSCODE(11),

    TYPE_ACTION_ORDER_ACCEPT(12),

    TYPE_ACTION_ORDER_UPDATE_FINISHED(13),

    TYPE_ACTION_ORDER_UPDATE_CANCEL(14),

    TYPE_ACTION_ORDER_UPDATE_WORKING(15),

    TYPE_ACTION_ORDER_UPDATE_COMING(16),

    TYPE_ACTION_UPDATE_IS_WORKING(17),

    TYPE_ACTION_UPDATE_AGENCY(18),

    TYPE_ACTION_SEARCH(19),

    TYPE_ACTION_SOCKET_ORDER_OFFER(20),

    TYPE_ACTION_SOCKET_ORDER_ACCEPT(21),

    TYPE_ACTION_SOCKET_RECEIVE_LOCATION(22),

    TYPE_ACTION_ORDER_GET_DETAIL(23),

    TYPE_ACTION_MESSAGE_READ(24),

    TYPE_ACTION_NOTIFICATION_LIST(25),

    TYPE_ACTION_SEND_FEEDBACK(26),

    TYPE_ACTION_LOGOUT(27),

    TYPE_ACTION_CHANGE_PASSWORD(28),

    TYPE_ACTION_UPLOAD_AVATAR(28),

    TYPE_ACTION_GET_COUNT_SKILL(29),

    TYPE_ACTION_CHECK_PHONE_NUM(30),

    TYPE_ACTION_GET_TIME_MY_JOB(31),

    TYPE_ACTION_GET_COUNT_NOTIFICATION(32),

    TYPE_ACTION_GET_REVENUES(33),

    TYPE_ACTION_GET_CURRENT_ORDER_WORKING(34),

    TYPE_ACTION_GET_COUNT_MY_JOB(35),

    TYPE_ACTION_GET_INFO_REVENUE_MONTH(36),

    TYPE_ACTION_GET_INFO_REVENUE_DAY(37),

    TYPE_ACTION_GET_INFO_REVENUE_DAY_DETAIL(38),

    TYPE_ACTION_GET_INFO_USER(39),

    TYPE_ACTION_GET_INFO_SLIDING_MENU(40),

    TYPE_ACTION_GET_INFO_REVENUE_BY_ID(41),

    TYPE_ACTION_GET_INFO_RECEIVE_CANCEL_BY_ID(42),

    TYPE_ACTION_GET_INFO_FINISH_BY_ID(43),

    TYPE_ACTION_GET_INFO_TOP_REVENUE_BY_ID(44),

    TYPE_ACTION_GET_INFO_TOP_FINISH_BY_ID(45),

    TYPE_ACTION_GET_INFO_TOP_CANCEL_BY_ID(46),

    TYPE_ACTION_GET_INFO_PHAN_CAP(47),

    TYPE_ACTION_GET_HISTORY_LEVEL(48),

    TYPE_ACTION_GET_GROUP_INFO(49),

    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    ////  STATUS Connection
    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    TYPE_CONNECTION(100),

    TYPE_CONNECTION_NO_INTERNET(102),

    TYPE_CONNECTION_NO_GPS(103),

    TYPE_CONNECTION_TIMEOUT(104),

    TYPE_CONNECTION_NOT_CONNECT_SERVER(105),

    TYPE_CONNECTION_ERROR(106),

    TYPE_CONNECTION_ERROR_FROM_SERVER(107),

    TYPE_CONNECTION_ERROR_CODE_JSON(108);

    private static final Map<Integer, TypeActionConnection> typesByValue = new HashMap<>();

    private final int valuesConnectionType;

    TypeActionConnection(int value) {
        this.valuesConnectionType = value;
    }

    public int getValuesTypeDialog() {
        return valuesConnectionType;
    }

    static {
        for (TypeActionConnection type : TypeActionConnection.values()) {
            typesByValue.put(type.valuesConnectionType, type);
        }
    }

    public static TypeActionConnection forValue(int value) {
        TypeActionConnection type = typesByValue.get(value);
        if (type == null)
            return TypeActionConnection.TYPE_ACTION;
        return typesByValue.get(value);
    }
}
