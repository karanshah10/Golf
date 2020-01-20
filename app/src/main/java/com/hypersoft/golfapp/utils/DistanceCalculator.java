package com.hypersoft.golfapp.utils;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Apple on 6/28/2017.
 */

public class DistanceCalculator {

    public static float countDistance(List<LatLng> points) {
        int size = points.size();

        if (size > 1) {
            LatLng latLng = points.get(size - 1);
            LatLng latLng1 = points.get(size - 2);

            Location location = new Location("");
            location.setLatitude(latLng.latitude);
            location.setLongitude(latLng.longitude);

            Location location1 = new Location("");
            location1.setLatitude(latLng1.latitude);
            location1.setLongitude(latLng1.longitude);

            return location.distanceTo(location1);
        }
        return 0;
    }
}
