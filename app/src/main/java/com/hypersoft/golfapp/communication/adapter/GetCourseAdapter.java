package com.hypersoft.golfapp.communication.adapter;

import com.hypersoft.golfapp.communication.models.APIRequest;
import com.hypersoft.golfapp.communication.models.APIResponse;
import com.hypersoft.golfapp.communication.models.GetCourseRequest;
import com.hypersoft.golfapp.communication.models.GetCourseResponse;
import com.hypersoft.golfapp.communication.service.IApiService;

import retrofit2.Call;

/**
 * Created by Apple on 10/9/2017.
 */

public class GetCourseAdapter<E extends APIResponse> extends ApiAdapter<GetCourseResponse> {
    @Override
    protected Call<GetCourseResponse> getCallInstance(IApiService service, APIRequest apiRequest) {
         return service.getCourse((GetCourseRequest) apiRequest);
    }
}
