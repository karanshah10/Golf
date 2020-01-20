package com.hypersoft.golfapp.communication.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hypersoft.golfapp.BR;

/**
 * Created by Apple on 8/3/2017.
 */

public class User extends BaseObservable {
    private String loginId;
    private String password;
    private String rePassword;
    private String firstName;
    private String lastName;

    @Bindable
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
        notifyPropertyChanged(BR.loginId);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
        notifyPropertyChanged(BR.rePassword);
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }
}
