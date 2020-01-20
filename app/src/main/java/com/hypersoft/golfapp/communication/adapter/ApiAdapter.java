package com.hypersoft.golfapp.communication.adapter;

import com.hypersoft.golfapp.communication.listener.APIResponseHandler;
import com.hypersoft.golfapp.communication.mock.MockAdapter;
import com.hypersoft.golfapp.communication.models.APIRequest;
import com.hypersoft.golfapp.communication.models.APIResponse;
import com.hypersoft.golfapp.communication.service.IApiService;
import com.hypersoft.golfapp.restapi.RestApiClient;
import com.hypersoft.golfapp.ui.activity.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Apple on 10/6/2017.
 */

public abstract class ApiAdapter<E extends APIResponse> {

    public void invoke(APIRequest apiRequest, final BaseActivity baseActivity, final boolean showProgress
            , final APIResponseHandler<E> apiResponseHandler) {

        // API Invocation
        IApiService service = (IApiService) RestApiClient.getApiService(IApiService.class,
                "http://192.168.0.138:1245", MockAdapter.class);

        if (showProgress)
            baseActivity.showProgressDialog(baseActivity, "Please Wait");


        Call<E> call = getCallInstance(service, apiRequest);

        //Response handling
        call.enqueue(new Callback<E>() {
            @Override
            public void onResponse(Call<E> call, Response<E> response) {
                if (showProgress)
                    baseActivity.dismissProgressDialog();
                if (response.isSuccessful()) {
                    E apiResponse = response.body();
                    if (!apiResponse.isSuccess()) {
//                            apiResponseHandler.OnError(baseActivity, apiResponse.getBusinessErrorMessages());
                    } else {
                        apiResponseHandler.OnSuccess(apiResponse);
                    }

                } else {
                    String message = response.message();
                    //Log.e(TAG, message);
//                        message = !TextUtils.isEmpty(message) ? message : getServerErrorMsg();
//                        apiResponseHandler.OnError(baseActivity, message);

                    boolean unauthorized = response.code() == 401;
                    if (unauthorized) {

                    }
                }
            }

            @Override
            public void onFailure(Call<E> call, Throwable t) {
                //Log.e(TAG, t.getMessage());
//                    apiResponseHandler.OnError(baseActivity, getServerErrorMsg());
//                    if (showProgress)
//                        baseActivity.dismissProgressDialog();
            }
        });
    }

    protected abstract Call<E> getCallInstance(IApiService service, APIRequest apiRequest);

}
