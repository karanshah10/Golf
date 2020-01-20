package com.hypersoft.golfapp.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.hypersoft.golfapp.R;
import com.hypersoft.golfapp.communication.models.User;
import com.hypersoft.golfapp.databinding.ActivitySignupBinding;
import com.hypersoft.golfapp.utils.Utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by Apple on 7/18/2017.
 */
public class SignUpActivity extends BaseActivity {
    private ActivitySignupBinding binding;
    private User user = new User();
    String mailAddress, password, passwordRe, firstName, lastName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        setSupportActionBar(binding.toolbar.mainToolbar);
        binding.setUser(user);
        binding.setSignupActivity(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onSignupClick() {
        if (isValidate()) {
            showResult();
        }
    }

    private boolean isValidate() {
        boolean validate = false;
        mailAddress = binding.etMailAddress.getText().toString();
        password = binding.etRePassword.getText().toString();
        passwordRe = binding.etRePassword.getText().toString();
        firstName = binding.etFirstName.getText().toString();
        lastName = binding.etLastName.getText().toString();
        try {
            if (TextUtils.isEmpty(mailAddress)) {
                showToast("Enter Email Address");
            } else if (!Utils.isEmailValid(mailAddress)) {
                showToast("Invalid Email Address");
            } else if (TextUtils.isEmpty(password)) {
                showToast("Enter password");
            } else if (Utils.UTFDecode(password).length() < 8) {
                showToast("Minimum password length should be 8 characters.");
            } else if (TextUtils.isEmpty(passwordRe)) {
                showToast("Enter password(Re");
            } else if (!passwordRe.equals(passwordRe)) {
                showToast("Passwords do not match");
            } else if (TextUtils.isEmpty(lastName)) {
                showToast("Enter last name.");
            } else if (TextUtils.isEmpty(firstName)) {
                showToast("Enter first name.");
            } else {
                validate = true;
            }
        } catch (UnsupportedEncodingException e) {

        }
        return validate;
    }

    private void showResult() {
        navigateToHome();
    }

    private void navigateToHome() {
        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
    }
}
