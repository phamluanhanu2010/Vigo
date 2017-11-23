package com.strategy.intecom.vtc.vigo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by macbookpro on 5/29/17.
 * <p>
 *
 * @author Mr. Ha
 */

public class VtcModelPositionAudio implements Parcelable {
    private int id = 0;
    private String title = "";
    private String avatar = "";
    private int duration = 0;

    public VtcModelPositionAudio() {

    }


    protected VtcModelPositionAudio(Parcel in) {
        id = in.readInt();
        title = in.readString();
        avatar = in.readString();
        duration = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(avatar);
        dest.writeInt(duration);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VtcModelPositionAudio> CREATOR = new Creator<VtcModelPositionAudio>() {
        @Override
        public VtcModelPositionAudio createFromParcel(Parcel in) {
            return new VtcModelPositionAudio(in);
        }

        @Override
        public VtcModelPositionAudio[] newArray(int size) {
            return new VtcModelPositionAudio[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

