/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/10/16 8:37 AM
 */

package com.strategy.intecom.vtc.vigo.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by Mr. Ha on 9/23/16.
 *
 * @author Mr. Ha
 */
public enum GATrackingScreenName {

    SCREEN_MAIN("Màn hình chính"),
    SCREEN_SIGN_IN("Màn hình đăng nhập"),
    SCREEN_SIGN_UP("Màn hình đăng ký"),
    SCREEN_CHANGE_PASSWORD("Màn hình đổi mật khẩu"),
    SCREEN_FORGOT_PASSWORD("Màn hình quên mật khẩu"),
    SCREEN_FORGOT_PASSWORD_INPUT("Màn hình nhập mật khẩu mới"),
    SCREEN_USER_SUPPORT("Màn hình hỗ trợ"),
    SCREEN_SELECT_LOCATION("Màn hình chọn địa chỉ"),
    SCREEN_VERIFY("Màn hình xác thực tài khoản"),
    SCREEN_SYSTEM_NOTIFICATIONS("Màn hình danh sách thông báo"),
    SCREEN_SYSTEM_NOTIFICATIONS_DETAIL("Màn hình chi tiết thông báo"),
    SCREEN_ORDER_HISTORIES("Màn hình lịch sử việc"),
    SCREEN_RULE_AND_SECURITY("Màn hình điều khoản và chính sách"),
    SCREEN_ORDER_LIST("Màn hình danh sách việc"),
    SCREEN_ORDER_DETAIL("Màn hình chi tiết công việc"),
    SCREEN_ORDER_DETAIL_MAP("Màn hình chi tiết công việc (Bản đồ)"),
    SCREEN_ORDER_SEARCH("Màn hình tìm kiếm công việc"),
    SCREEN_ORDER_NEARBY("Màn hình việc xung quanh"),
    SCREEN_PROFILE_DETAIL("Màn hình chi tiết thông tin thợ"),
    SCREEN_AGENCY_RATE("Màn hình đánh giá"),
    SCREEN_AGENCY_MARK_HISTORIES("Màn hình lịch sử điểm"),
    SCREEN_AGENCY_LEVEL("Màn hình phân cấp thợ"),
    SCREEN_AGENCY_GROUP("Màn hình quản lý nhóm thợ"),
    SCREEN_MY_ORDER("Màn hình lịch làm việc"),
    SCREEN_REGISTER_FIELD("Màn hình đăng ký lĩnh vực"),
    SCREEN_ACCEPT_ORDER_FAST("Màn hình nhận việc nhanh"),
    SCREEN_MANAGER_REVENUE("Màn hình quản lý doanh thu"),
    SCREEN_RULE_LEVEL("Màn hình quy tắc phân cấp thợ"),

    BUTTON_REGISTER("Button Đăng ký"),
    BUTTON_LOGOUT("Button Đăng xuất"),
    BUTTON_ORDER_FAST_ACCEPT("Button Nhận việc nhanh"),
    BUTTON_ORDER_NORMAL_ACCEPT("Button Nhận việc nhanh"),
    BUTTON_ORDER_FAST_CANCEL("Button Hủy việc nhanh"),
    BUTTON_ORDER_NORMAL_CANCEL("Button Hủy việc đặt lịch"),
    BUTTON_WORKING_ON("Button Working On"),
    BUTTON_WORKING_OFF("Button Working Off"),
    BUTTON_ORDER_SEARCH("Button Search Order"),

    LABEL_LOGOUT("Đăng xuất"),
    LABEL_REGISTER("Đăng ký"),
    LABEL_REGISTER_SUCCESS("Đăng ký thành công"),
    LABEL_LOGIN("Đăng nhập thành công"),
    LABEL_FIELD_PARENT("Lĩnh vực sửa chữa"),
    LABEL_STATUS_ON("On trạng thái nhận việc"),
    LABEL_STATUS_OFF("Off trạng thái nhận việc"),
    LABEL_ORDER_FAST_CANCEL("Hủy giao dịch sửa ngay"),
    LABEL_ORDER_FAST_ACCEPT("Nhận việc nhanh"),
    LABEL_ORDER_NORMAL_CANCEL("Hủy giao dịch đặt chậm"),
    LABEL_ORDER_NORMAL_ACCEPT("Nhận việc đặt lịch"),
    LABEL_ORDER_SEARCH("Tìm việc"),
    LABEL_ORDER_LIST("Lịch làm việc"),
    LABEL_ORDER_LIST_HISTORY("Lịch sử việc làm"),
    LABEL_SUPPORT("Hỗ trợ"),
    LABEL_ORDER_DETAIL("Chi tiết việc"),
    LABEL_NOTIFICATION("Thông báo");

    private static final Map<String, GATrackingScreenName> typesByValue = new HashMap<>();

    private final String valuesStatus;

    GATrackingScreenName(String value) {
        this.valuesStatus = value;
    }

    public String getValues() {
        return valuesStatus;
    }

    static {
        for (GATrackingScreenName type : GATrackingScreenName.values()) {
            typesByValue.put(type.valuesStatus, type);
        }
    }

    public static GATrackingScreenName forValue(int value) {
        GATrackingScreenName type = typesByValue.get(value);
        if (type == null)
            return GATrackingScreenName.SCREEN_MAIN;
        return typesByValue.get(value);
    }

    public static GATrackingScreenName fromString(String text) {
        if (text != null) {
            for (GATrackingScreenName b : GATrackingScreenName.values()) {
                if (text.equalsIgnoreCase(b.valuesStatus)) {
                    return b;
                }
            }
        }
        return GATrackingScreenName.SCREEN_MAIN;
    }
}
