/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 5/30/16 1:29 PM
 */

package com.strategy.intecom.vtc.vigo.view.custom.textviewcus;

import android.content.Context;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Mr. Ha on 5/25/16.
 *
 * TEXTVIEW CÓ STYLE GẠCH CHÂN
 * hiển thị đoạn TextView trên layout có gạch chân.
 */

public class TextViewUnderline extends TextView {

    protected boolean m_modifyingText = false;

    public TextViewUnderline(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Do nothing here... we don't care
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Do nothing here... we don't care
            }

            @Override
            public void afterTextChanged(Editable s) {
                applySpannable();
            }
        });

        applySpannable();
    }

    private SpannableString getSpannableString() {
        SpannableString finalText = new SpannableString(getText().toString());
        finalText.setSpan(new UnderlineSpan(), 0, finalText.length(), 0);
        return finalText;
    }

    private void applySpannable() {
        if (m_modifyingText)
            return;

        m_modifyingText = true;
        super.setText(getSpannableString(), BufferType.SPANNABLE);
        m_modifyingText = false;
    }
}