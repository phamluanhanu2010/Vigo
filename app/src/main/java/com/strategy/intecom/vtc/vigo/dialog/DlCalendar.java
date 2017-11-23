/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/24/16 8:55 AM
 */

package com.strategy.intecom.vtc.vigo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.utils.Utils;
import com.strategy.intecom.vtc.vigo.view.custom.calendarview.CalendarListener;
import com.strategy.intecom.vtc.vigo.view.custom.calendarview.CustomCalendarView;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Mr. Ha on 5/20/16.
 *
 * @author Mr. Ha
 */
public class DlCalendar extends Dialog {

    private CustomCalendarView calendarView;
    private Context context;
    private onClickItemCalendar onClickItemCalendar;
    private Button txt_dialog_edit_name;
    private Button btn_dialog_edit_save;
    private ImageView imgCloseDialog;

    private long day_offset = 90;

    private Date currentDate;

    private boolean TYPE_FROM_TO = false;
    private int width;

    public DlCalendar.onClickItemCalendar getOnClickItemCalendar() {
        return onClickItemCalendar;
    }

    public void setOnClickItemCalendar(DlCalendar.onClickItemCalendar onClickItemCalendar) {
        this.onClickItemCalendar = onClickItemCalendar;
    }

    public DlCalendar(Context context, int width, boolean TYPE_FROM_TO) {
        super(context);
        this.context = context;
        this.TYPE_FROM_TO = TYPE_FROM_TO;
        this.width = (int) (width - (context.getResources().getDimension(R.dimen.confirm_ui_padding_w) * 2));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        setCancelable(false);
        setContentView(R.layout.dl_calendar);

        initController();
    }

    public void initController() {

        currentDate = new Date();

        txt_dialog_edit_name = (Button) findViewById(R.id.txt_dialog_edit_name);
        btn_dialog_edit_save = (Button) findViewById(R.id.btn_dialog_edit_save);
        imgCloseDialog = (ImageView) findViewById(R.id.img_close_dialog);

        if (TYPE_FROM_TO) {
            txt_dialog_edit_name.setText(context.getResources().getString(R.string.title_from_day));
        } else {
            txt_dialog_edit_name.setText(context.getResources().getString(R.string.title_to_day));
        }

        //Initialize CustomCalendarView from layout
        calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);

        //Initialize calendar with date
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());

//        day_offset = System.currentTimeMillis() - (day_offset * (24 * (60 * (60 * 1000))));
//
//        currentCalendar.setTimeInMillis(day_offset);


        //Setting custom font
//        final Typeface typeface = Typeface.createFromAsset(context.getResources().getAssets(), "fonts/Arch_Rival_Bold.ttf");
//        if (null != typeface) {
//            calendarView.setCustomTypeface(typeface);
//            calendarView.refreshCalendar(currentCalendar);
//        }

        //Show Monday as first date of week
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

        //Show/hide overflow days of a month
        calendarView.setShowOverflowDate(false);

        //call refreshCalendar to update calendar the view
        calendarView.refreshCalendar(currentCalendar);

//        calendarView.markDayAsSelectedDay(currentCalendar.getTime());

//        calendarView.markDayAsCurrentDay(currentCalendar);

        //Handling custom calendar events
        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                currentDate = date;
            }

            @Override
            public void onMonthChanged(Date date) {
//                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
//                Toast.makeText(context, df.format(date), Toast.LENGTH_SHORT).show();
            }
        });

        btn_dialog_edit_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
//                SimpleDateFormat dfDisplay = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//                SimpleDateFormat dfSendSV = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
//
//                AppCore.showLog("---------- : ");

//                getOnClickItemCalendar().onClickItem(TYPE_FROM_TO, dfDisplay.format(currentDate), dfSendSV.format(currentDate));


                getOnClickItemCalendar().onClickItem(TYPE_FROM_TO, Utils.getCurrentTimeGMT(currentDate), Utils.getCurrentTimeISO(currentDate));
            }
        });

        imgCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface onClickItemCalendar {
        void onClickItem(boolean TYPE_FROM_TO, String dateDisplay, String dateSendSV);
    }
}
