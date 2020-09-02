package com.johnmbiya.gads.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.johnmbiya.gads.R;
import com.johnmbiya.gads.adapters.HoursAdapter;
import com.johnmbiya.gads.models.LeaderHour;
import com.johnmbiya.gads.viewmodels.HourViewModel;

import java.util.ArrayList;
import java.util.List;


public class HoursFragment extends Fragment {


    private HourViewModel hourViewModel;
    private HoursAdapter hoursAdapter;
    private List<LeaderHour> leaderHourList = new ArrayList<>();

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

        RecyclerView recyclerView = root.findViewById(R.id.rv_hours);
        ProgressBar progressBar = root.findViewById(R.id.progress);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        hoursAdapter = new HoursAdapter(leaderHourList);
        recyclerView.setAdapter(hoursAdapter);

        hourViewModel.init();
        hourViewModel.getHours().observe( getViewLifecycleOwner(), leaderHours -> {
            if(leaderHours != null) {
                leaderHourList.addAll(leaderHours);
            }

            hoursAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
        });

        return root;
    }
}