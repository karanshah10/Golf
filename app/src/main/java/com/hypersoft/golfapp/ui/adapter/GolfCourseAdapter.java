package com.hypersoft.golfapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hypersoft.golfapp.R;
import com.hypersoft.golfapp.communication.models.GolfCourse;
import com.hypersoft.golfapp.communication.models.GolfCourse1;
import com.hypersoft.golfapp.databinding.CourseGolfSinglerowBinding;
import com.hypersoft.golfapp.ui.activity.MapActivity;
import com.hypersoft.golfapp.ui.activity.PlayBookDialog;

import java.util.List;


/**
 * Created by Apple on 7/19/2017.
 */

public class GolfCourseAdapter extends RecyclerView.Adapter<GolfCourseAdapter.GolfViewHolder> {
    private List<GolfCourse1> golfCourseList;
    private Context mContext;

    public GolfCourseAdapter(List<GolfCourse1> golfCourseList, Context mContext) {
        this.mContext = mContext;
        this.golfCourseList = golfCourseList;
    }

    @Override
    public GolfViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CourseGolfSinglerowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.course_golf_singlerow, parent, false);
        return new GolfViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(GolfViewHolder holder, int position) {

        holder.binding.setGolfCourse(golfCourseList.get(position).getCourse());
        holder.binding.setGolfCoursedAdapter(this);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return golfCourseList.size();
    }

    public class GolfViewHolder extends RecyclerView.ViewHolder {
        private CourseGolfSinglerowBinding binding;

        public GolfViewHolder(CourseGolfSinglerowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void onMapClick() {
        Intent intent = new Intent(mContext, MapActivity.class);
        intent.putExtra("MAPACTIVITY", 0);
        mContext.startActivity(intent);
    }

    public void onPlayClick() {
        PlayBookDialog playBookDialog = new PlayBookDialog(mContext);
        playBookDialog.show();
    }
}
