package com.hypersoft.golfapp.communication.mock;

import com.google.gson.Gson;
import com.hypersoft.golfapp.communication.models.GetCourseRequest;
import com.hypersoft.golfapp.communication.models.GetCourseResponse;
import com.hypersoft.golfapp.communication.service.IApiService;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.mock.BehaviorDelegate;

/**
 * Created by Apple on 10/10/2017.
 */

public class MockAdapter implements IApiService {

    private final BehaviorDelegate<IApiService> delegate;

    public MockAdapter(BehaviorDelegate<IApiService> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Call<GetCourseResponse> getCourse(@Body GetCourseRequest request) {
        String response = "{\"courses\":[{\"course\":{\"courseName\":\"Course 1 Play Golf\",\"location\":\"Location:vadodara\"}}," +
                "{\"course\":{\"courseName\":\"Course 2 Play Golf\",\"location\":\"Location:Ahemdabad\"}}," +
                "{\"course\":{\"courseName\":\"Course 3 Play Golf\",\"location\":\"Location:JAPAN\"}}," +
                "{\"course\":{\"courseName\":\"Course 4 Play Golf\",\"location\":\"Location:India\"}}," +
                "{\"course\":{\"courseName\":\"Course 5 Play Golf\",\"location\":\"Location:USA\"}}," +
                "{\"course\":{\"courseName\":\"Course 6 Play Golf\",\"location\":\"Location:Canada\"}}," +
                "{\"course\":{\"courseName\":\"Course 7 Play Golf\",\"location\":\"Location:new Zealand\"}}," +
                "{\"course\":{\"courseName\":\"Course 8 Play Golf\",\"location\":\"Location:Australia\"}}," +
                "{\"course\":{\"courseName\":\"Course 9 Play Golf\",\"location\":\"Location:Denmarak\"}}]}";
        GetCourseResponse getCourseResponse = new Gson().fromJson(response, GetCourseResponse.class);
        return delegate.returningResponse(getCourseResponse).getCourse(request);
    }
}
