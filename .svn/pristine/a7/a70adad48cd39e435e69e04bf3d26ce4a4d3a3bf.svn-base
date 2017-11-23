/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/25/16 9:44 AM
 */

package com.strategy.intecom.vtc.vigo.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Create Mr. Ha on 6/1/16.
 *
 * @author Mr. Ha
 */
public enum TypeShowDialog {

    TYPE_SHOW_MESSAGE_INFO(0),

    TYPE_SHOW_MESSAGE_CONFIRM(1),

    TYPE_SHOW_CALENDAR(2),

    TYPE_SHOW_MESSAGE_NEW_JOB_FAST(3),

//    TYPE_SHOW_CHOICE_CITY_DISTRICT(4),

    TYPE_SHOW_SHORTED_BY(5),

    TYPE_SHOW_CHOICE_IMAGE(6),

    TYPE_SHOW_CHOICE_SKILL(7),

    TYPE_SHOW_ENABLE_NETWORK(8),

    TYPE_SHOW_ENABLE_GPS(9),

    TYPE_SHOW_MESSAGE_NEW_JOB_NORMAL(10),

    TYPE_SHOW_MESSAGE_JOB_OVER_TIME(11),

    TYPE_SHOW_MESSAGE_RULE_LEVEL(12),

    TYPE_SHOW_MESSAGE_REPORT(13),

    TYPE_SHOW_MESSAGE_AGENCY_LIST(14),

    TYPE_SHOW_MESSAGE_INFO_FULL_SCREEN(15);

    private static final Map<Integer, TypeShowDialog> typesByValue = new HashMap<>();

    private final int valuesDialogType;

    TypeShowDialog(int value) {
        this.valuesDialogType = value;
    }

    public int getValuesTypeDialog() {
        return valuesDialogType;
    }

    static {
        for (TypeShowDialog type : TypeShowDialog.values()) {
            typesByValue.put(type.valuesDialogType, type);
        }
    }

    public static TypeShowDialog forValue(int value) {
        TypeShowDialog type = typesByValue.get(value);
        if (type == null)
            return TypeShowDialog.TYPE_SHOW_MESSAGE_INFO;
        return typesByValue.get(value);
    }
}
