package com.strategy.intecom.vtc.vigo.armodule.model;

import android.location.Location;

/**
 * Created by ntdat on 1/16/17.
 */

public class ARPoint {
    Location location;
    String name;

    public ARPoint() {

    }

    public ARPoint(String name, double lat, double lon, double altitude) {
        this.name = name;
        location = new Location("ARPoint");
        location.setLatitude(lat);
        location.setLongitude(lon);
        location.setAltitude(altitude);
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }
}
