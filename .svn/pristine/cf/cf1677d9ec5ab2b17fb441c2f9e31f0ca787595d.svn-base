package com.strategy.intecom.vtc.vigo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by macbookpro on 5/29/17.
 * <p>
 *
 * @author Mr. Ha
 */

public class VtcModelArchitetureAudio implements Parcelable {
    private int id = 0;
    private String title = "";
    private int duration = 0;
    private String fileName = "";
    private String path = "";
    private String status = "Play";
    private String content = "";

    public VtcModelArchitetureAudio() {

    }


    protected VtcModelArchitetureAudio(Parcel in) {
        id = in.readInt();
        title = in.readString();
        fileName = in.readString();
        status = in.readString();
        path = in.readString();
        content = in.readString();
        duration = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(fileName);
        dest.writeString(status);
        dest.writeString(path);
        dest.writeString(content);
        dest.writeInt(duration);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VtcModelArchitetureAudio> CREATOR = new Creator<VtcModelArchitetureAudio>() {
        @Override
        public VtcModelArchitetureAudio createFromParcel(Parcel in) {
            return new VtcModelArchitetureAudio(in);
        }

        @Override
        public VtcModelArchitetureAudio[] newArray(int size) {
            return new VtcModelArchitetureAudio[size];
        }
    };

    public String getFileName() {
        return fileName;
    }

    public String getPath() {
        return path;
    }

    public String getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

