/*
 * Created by Hadvlop@gmail.com on 11/1/16 1:13 PM
 * Copyright © 2016, All Rights Reserved.
 *
 * Last modified 10/7/16 7:40 AM
 */

package com.strategy.intecom.vtc.vigo.view.custom.loadimage.ultilLoad;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by Mr. Ha on 5/26/16.
 * @author Mr. Ha
 */
public class BitmapTransform extends BitmapTransformation {

    private int maxWidth;
    private int maxHeight;

    public BitmapTransform(Context context, int maxWidth, int maxHeight) {
        super(context);
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }

    @Override
    public String getId() {
        return getClass().getName();
    }

    private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        int targetWidth;
        int targetHeight;
        double aspectRatio;

//        int size = Math.min(source.getWidth(), source.getHeight());

        if (source.getWidth() > source.getHeight()) {
            targetWidth = maxWidth;
            aspectRatio = (double) source.getHeight() / (double) source.getWidth();
            targetHeight = (int) (targetWidth * aspectRatio);
        } else if (source.getWidth() < source.getHeight()) {
            targetHeight = maxHeight;
            aspectRatio = (double) source.getWidth() / (double) source.getHeight();
            targetWidth = (int) (targetHeight * aspectRatio);
        }else {
            targetWidth = maxWidth;
            targetHeight = maxHeight;
        }

        Bitmap result = pool.get(targetWidth, targetHeight, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.ARGB_8888);
        }
        return result;
    }
}