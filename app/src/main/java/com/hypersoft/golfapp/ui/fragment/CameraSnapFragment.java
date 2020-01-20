package com.hypersoft.golfapp.ui.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hypersoft.golfapp.R;
import com.hypersoft.golfapp.databinding.ActivityCameraSnapBinding;

import java.io.FileNotFoundException;

/**
 * Created by Apple on 4/12/2017.
 */
public class CameraSnapFragment extends BaseFragment {
    ActivityCameraSnapBinding binding;
    Bitmap bitmap = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_camera_snap, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        afterView();
    }

    private void afterView() {
        try {
            bitmap = BitmapFactory.decodeStream(getActivity().openFileInput("Camera"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        binding.ivImage.setImageBitmap(bitmap);
    }
}
