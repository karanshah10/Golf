package com.hypersoft.golfapp.communication.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hypersoft.golfapp.BR;

/**
 * Created by Apple on 7/19/2017.
 */

public class GolfCourse extends BaseObservable {
    private String description;
    private String location;
    private int golfCourse;

    public GolfCourse(String description, String location, int golfCourse) {
        this.description = description;
        this.location = location;
        this.golfCourse = golfCourse;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        notifyPropertyChanged(BR.location);
    }

    @Bindable
    public int getGolfCourse() {
        return golfCourse;
    }

    public void setGolfCourse(int golfCourse) {
        this.golfCourse = golfCourse;
        notifyPropertyChanged(BR.golfCourse);
    }
}
