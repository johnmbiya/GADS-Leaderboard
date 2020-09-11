package com.johnmbiya.gads.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.johnmbiya.gads.R;
import com.johnmbiya.gads.adapters.HoursAdapter;
import com.johnmbiya.gads.models.LeaderHour;
import com.johnmbiya.gads.viewmodels.HourViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HoursFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    private HourViewModel hourViewModel;
    private HoursAdapter hoursAdapter;
    private List<LeaderHour> leaderHourList = new ArrayList<>();
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;


    public static HoursFragment newInstance() {
        return new HoursFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hourViewModel = new ViewModelProvider(this).get(HourViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_hour, container, false);

        // setting recycler view
        RecyclerView recyclerView = root.findViewById(R.id.rv_hours);
        progressBar = root.findViewById(R.id.progress);
        refreshLayout = root.findViewById(R.id.swipe_hours);

        refreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        hoursAdapter = new HoursAdapter(leaderHourList);
        recyclerView.setAdapter(hoursAdapter);

        if(isOnline(Objects.requireNonNull(getActivity()))) {
            getHoursLeaders();
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
        }

        return root;
    }

    private void getHoursLeaders() {
        hourViewModel.init();
        hourViewModel.getHours().observe( getViewLifecycleOwner(), leaderHours -> {
            if(leaderHours != null) {
                leaderHourList.addAll(leaderHours);
            }

            hoursAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
            if (refreshLayout.isRefreshing()){
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        if(isOnline(Objects.requireNonNull(getActivity()))) {
            clearData();
            getHoursLeaders();
        } else {
            refreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
        }

    }

    private void clearData() {
        leaderHourList.clear();
    }

}