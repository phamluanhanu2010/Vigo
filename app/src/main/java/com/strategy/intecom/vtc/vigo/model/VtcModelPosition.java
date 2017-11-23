package com.strategy.intecom.vtc.vigo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by macbookpro on 5/29/17.
 * <p>
 *
 * @author Mr. Ha
 */

public class VtcModelPosition implements Parcelable {
    private int id = 0;
    private String title = "";
    private String avatar = "";
    private String address = "";
    private String price = "";
    private String description = "";
    private String backgroundResource = "";
    private String mapResourceThumb = "";
    private String mapResourceFull = "";
    private boolean isSave = Boolean.FALSE;

    public VtcModelPosition() {

    }

    protected VtcModelPosition(Parcel in) {
        id = in.readInt();
        title = in.readString();
        avatar = in.readString();
        address = in.readString();
        price = in.readString();
        description = in.readString();
        backgroundResource = in.readString();
        mapResourceThumb = in.readString();
        mapResourceFull = in.readString();
    }

    public static final Creator<VtcModelPosition> CREATOR = new Creator<VtcModelPosition>() {
        @Override
        public VtcModelPosition createFromParcel(Parcel in) {
            return new VtcModelPosition(in);
        }

        @Override
        public VtcModelPosition[] newArray(int size) {
            return new VtcModelPosition[size];
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
        dest.writeString(address);
        dest.writeString(price);
        dest.writeString(description);
        dest.writeString(backgroundResource);
        dest.writeString(mapResourceThumb);
        dest.writeString(mapResourceFull);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isSave() {
        return isSave;
    }

    public void setSave(boolean save) {
        isSave = save;
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

    public void setBackgroundResource(String backgroundResource) {
        this.backgroundResource = backgroundResource;
    }

    public String getMapResourceThumb() {
        return mapResourceThumb;
    }

    public void setMapResourceThumb(String mapResourceThumb) {
        this.mapResourceThumb = mapResourceThumb;
    }

    public String getMapResourceFull() {
        return mapResourceFull;
    }

    public void setMapResourceFull(String mapResourceFull) {
        this.mapResourceFull = mapResourceFull;
    }
}

