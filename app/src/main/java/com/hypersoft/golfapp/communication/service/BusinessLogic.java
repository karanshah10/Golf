package com.hypersoft.golfapp.communication.service;

import com.hypersoft.golfapp.R;
import com.hypersoft.golfapp.communication.adapter.GetCourseAdapter;
import com.hypersoft.golfapp.communication.listener.APIResponseHandler;
import com.hypersoft.golfapp.communication.models.GetCourseRequest;
import com.hypersoft.golfapp.communication.models.GetCourseResponse;
import com.hypersoft.golfapp.communication.models.GolfCourse;
import com.hypersoft.golfapp.communication.models.GolfCourse1;
import com.hypersoft.golfapp.ui.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apple on 10/9/2017.
 */

public class BusinessLogic {
    ArrayList<GolfCourse1> allCourse = new ArrayList<>();
    ArrayList<GolfCourse1> getAllCourse = new ArrayList<>();
    static List<GolfCourse> golfCourseList = new ArrayList<>();

    public static List<GolfCourse> getGolfCourseList() {


        GolfCourse golfCourse = new GolfCourse("Course 1 Play Golf", "Location:vadodara", R.drawable.golf_course);
        golfCourseList.add(golfCourse);
        golfCourse = new GolfCourse("Course 2 Play Golf", "Location:Ahemdabad", R.drawable.golf_course);
        golfCourseList.add(golfCourse);
        golfCourse = new GolfCourse("Course 3 Play Golf", "Location:JAPAN", R.drawable.golf_course);
        golfCourseList.add(golfCourse);
        golfCourse = new GolfCourse("Course 4 Play Golf", "Location:India", R.drawable.golf_course);
        golfCourseList.add(golfCourse);
        golfCourse = new GolfCourse("Course 5 Play Golf", "Location:USA", R.drawable.golf_course);
        golfCourseList.add(golfCourse);
        golfCourse = new GolfCourse("Course 6 Play Golf", "Location:Canada", R.drawable.golf_course);
        golfCourseList.add(golfCourse);
        golfCourse = new GolfCourse("Course 7 Play Golf", "Location:new Zealand", R.drawable.golf_course);
        golfCourseList.add(golfCourse);
        golfCourse = new GolfCourse("Course 8 Play Golf", "Location:Australia", R.drawable.golf_course);
        golfCourseList.add(golfCourse);
        golfCourse = new GolfCourse("Course 9 Play Golf", "Location:Denmarak", R.drawable.golf_course);
        golfCourseList.add(golfCourse);
        return golfCourseList;
    }

    public ArrayList<GolfCourse1> callGetGolfCourseAPI(BaseActivity context) {
        GetCourseRequest getGolfCouraseRequest = new GetCourseRequest();

        new GetCourseAdapter<GetCourseResponse>().invoke(getGolfCouraseRequest, context, true, new APIResponseHandler<GetCourseResponse>() {
            @Override
            public void OnSuccess(GetCourseResponse response) {
                allCourse = new ArrayList<GolfCourse1>(response.getCourses());
                getAllCourse = allCourse;
            }
        });
        return getAllCourse;
    }
}
