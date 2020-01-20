package com.hypersoft.golfapp.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hypersoft.golfapp.R;
import com.hypersoft.golfapp.communication.adapter.GetCourseAdapter;
import com.hypersoft.golfapp.communication.listener.APIResponseHandler;
import com.hypersoft.golfapp.communication.models.GetCourseRequest;
import com.hypersoft.golfapp.communication.models.GetCourseResponse;
import com.hypersoft.golfapp.communication.models.GolfCourse1;
import com.hypersoft.golfapp.communication.service.BusinessLogic;
import com.hypersoft.golfapp.databinding.LayoutFragmentBinding;
import com.hypersoft.golfapp.ui.activity.MainActivity;
import com.hypersoft.golfapp.ui.adapter.GolfCourseAdapter;
import com.hypersoft.golfapp.ui.communicator.FragmentReplaceCommunicator;

import java.util.ArrayList;

/**
 * Created by Apple on 7/19/2017.
 */
public class GolfCourseListFragment extends BaseFragment {
    private LayoutFragmentBinding binding;
    private Context mContext;
    ArrayList<GolfCourse1> golfCourse1s = new ArrayList<>();
    MainActivity mainActivity;
    private FragmentReplaceCommunicator fragmentReplaceCommunicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainActivity = (MainActivity) getActivity();
        mContext = getActivity();
        showResult();
    }

    private void showResult() {
//        List<GolfCourse> golfCourseList = BusinessLogic.getGolfCourseList();
        BusinessLogic businessLogic = new BusinessLogic();
        businessLogic.callGetGolfCourseAPI(mainActivity);
//
//        golfCourse1s = businessLogic.callGetGolfCourseAPI();

        GetCourseRequest getGolfCouraseRequest = new GetCourseRequest();
        new GetCourseAdapter<GetCourseResponse>().invoke(getGolfCouraseRequest,mainActivity,true, new APIResponseHandler<GetCourseResponse>() {
            @Override
            public void OnSuccess(GetCourseResponse response) {
                golfCourse1s = (ArrayList<GolfCourse1>) response.getCourses();
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                binding.mRecyclerView.setLayoutManager(layoutManager);

                GolfCourseAdapter adapter = new GolfCourseAdapter(golfCourse1s, getActivity());
                binding.mRecyclerView.setAdapter(adapter);
            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentReplaceCommunicator = (FragmentReplaceCommunicator) getActivity();
    }

}
