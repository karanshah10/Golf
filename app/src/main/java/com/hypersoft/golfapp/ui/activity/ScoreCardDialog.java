package com.hypersoft.golfapp.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.hypersoft.golfapp.R;
import com.hypersoft.golfapp.databinding.ScoreCardBinding;

/**
 * Created by Apple on 7/20/2017.
 */

public class ScoreCardDialog extends Dialog {
    private ScoreCardBinding binding;
    private Context mContext;

    public ScoreCardDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.score_card, null, false);
        setContentView(binding.getRoot());
        binding.setScoreCardDialog(this);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setCancelable(false);
    }

    public void onCloseClick() {
        dismiss();
    }
}
