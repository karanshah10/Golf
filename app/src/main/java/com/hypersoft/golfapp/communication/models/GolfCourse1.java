package com.hypersoft.golfapp.communication.models;

import android.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Apple on 10/9/2017.
 */

public class GolfCourse1 extends BaseObservable {
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @SerializedName("course")
    @Expose
    private Course course;
}
