package com.hypersoft.golfapp.communication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apple on 10/10/2017.
 */

public class APIResponse {
    @SerializedName("errorCode")
    @Expose
    public int errorCode;
    @SerializedName("errorMessages")
    @Expose
    public List<String> errorMessages = new ArrayList<String>();

    /**
     * @return The errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode The ErrorCode
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return The errorMessages
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * @param errorMessages The ErrorMessages
     */
    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public boolean isSuccess() {
        return errorMessages != null && errorMessages.isEmpty();
    }

    public String getBusinessErrorMessages() {
        return errorMessages.toString();
    }
}
