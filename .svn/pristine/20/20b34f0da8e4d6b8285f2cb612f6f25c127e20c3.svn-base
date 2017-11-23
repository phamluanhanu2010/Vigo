package com.strategy.intecom.vtc.vigo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by macbookpro on 5/29/17.
 * <p>
 *
 * @author Mr. Ha
 */

public class VtcModelMainTravel implements Parcelable {
    private int id = 0;
    private String title = "";
    private String avatar = "";
    private String description = "";
    private String backgroundResource = "";

    public VtcModelMainTravel() {

    }

    protected VtcModelMainTravel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        avatar = in.readString();
        description = in.readString();
        backgroundResource = in.readString();
    }

    public static final Creator<VtcModelMainTravel> CREATOR = new Creator<VtcModelMainTravel>() {
        @Override
        public VtcModelMainTravel createFromParcel(Parcel in) {
            return new VtcModelMainTravel(in);
        }

        @Override
        public VtcModelMainTravel[] newArray(int size) {
            return new VtcModelMainTravel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(avatar);
        dest.writeString(description);
        dest.writeString(backgroundResource);
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackgroundResource() {
        return backgroundResource;
    }

    public void setBackgroundResource(String backgroungResource) {
        this.backgroundResource = backgroungResource;
    }
}
