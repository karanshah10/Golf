package com.hypersoft.golfapp.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hypersoft.golfapp.R;
import com.hypersoft.golfapp.databinding.ActivitySigninBinding;
import com.hypersoft.golfapp.utils.LocaleHelper;

/**
 * Created by Apple on 7/18/2017.
 */
public class SignInActivity extends BaseActivity {

    private ActivitySigninBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signin);
        binding.setSignInActivity(this);
    }

    public void onLoginClick() {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onRegistrationClick() {
        startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
    }

    public void ontxtLocalClick() {
        String currentLocale = LocaleHelper.getLanguage(this);
        if (currentLocale.equalsIgnoreCase("en"))
            LocaleHelper.setLocale(this, "ja");
        else
            LocaleHelper.setLocale(this, "en");
        recreate();
    }

    public void ontForgotPasswordClick() {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}
