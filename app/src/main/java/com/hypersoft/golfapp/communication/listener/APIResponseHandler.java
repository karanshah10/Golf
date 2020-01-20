package com.hypersoft.golfapp.communication.listener;

import com.hypersoft.golfapp.communication.models.APIResponse;

/**
 * Created by Apple on 10/9/2017.
 */

public abstract class APIResponseHandler<E extends APIResponse> {
    public abstract void OnSuccess(E response);
}
