package com.hypersoft.golfapp.communication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Apple on 10/9/2017.
 */

public class GetCourseResponse extends APIResponse {

    @SerializedName("courses")
    @Expose
    private List<GolfCourse1> courses = null;

    public List<GolfCourse1> getCourses() {
        return courses;
    }

    public void setCourses(List<GolfCourse1> course) {
        courses = course;
    }

}
