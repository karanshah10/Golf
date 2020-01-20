package com.hypersoft.golfapp.communication.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hypersoft.golfapp.BR;

/**
 * Created by Apple on 10/11/2017.
 */

public class Course extends BaseObservable {

    @SerializedName("courseName")
    @Expose
    private String courseName;

    @SerializedName("location")
    @Expose
    private String location;


    @Bindable
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
        notifyPropertyChanged(BR.courseName);
    }

    @Bindable
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        notifyPropertyChanged(BR.location);
    }
}
