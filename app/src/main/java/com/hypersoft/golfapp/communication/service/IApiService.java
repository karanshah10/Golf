package com.hypersoft.golfapp.communication.service;

import com.hypersoft.golfapp.communication.models.GetCourseRequest;
import com.hypersoft.golfapp.communication.models.GetCourseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Apple on 10/10/2017.
 */

public interface IApiService {
    @POST("getCourse")
    Call<GetCourseResponse> getCourse(@Body GetCourseRequest request);
}
