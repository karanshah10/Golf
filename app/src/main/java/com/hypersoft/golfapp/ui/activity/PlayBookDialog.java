package com.hypersoft.golfapp.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.hypersoft.golfapp.R;
import com.hypersoft.golfapp.databinding.PlayBookBinding;

/**
 * Created by Apple on 7/20/2017.
 */

public class PlayBookDialog extends Dialog {

    private PlayBookBinding binding;
    Context mContext;

    public PlayBookDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.play_book, null, false);
        setContentView(binding.getRoot());
        binding.setPlayBookActivity(this);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setCancelable(false);
    }

    public void onBookSlotclick() {
    }

    public void onPlaynowClick() {
        mContext.startActivity(new Intent(mContext, MapActivity.class));
        dismiss();
    }

    public void onCancelClick() {
        dismiss();
    }
}
