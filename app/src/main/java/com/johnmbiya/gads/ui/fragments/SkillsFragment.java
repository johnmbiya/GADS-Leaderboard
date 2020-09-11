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
import com.johnmbiya.gads.adapters.SkillsAdapter;
import com.johnmbiya.gads.models.LeaderSkill;
import com.johnmbiya.gads.viewmodels.SkillViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class SkillsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private SkillViewModel skillViewModel;
    private SkillsAdapter skillsAdapter;
    private List<LeaderSkill> leaderSkillList = new ArrayList<>();
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;

    public static SkillsFragment newInstance() {
        return new SkillsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        skillViewModel = new ViewModelProvider(this).get(SkillViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_skill, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.rv_skills);
        progressBar = root.findViewById(R.id.progress);
        refreshLayout = root.findViewById(R.id.swipe_skills);

        refreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        skillsAdapter = new SkillsAdapter(leaderSkillList);
        recyclerView.setAdapter(skillsAdapter);

        if(isOnline(Objects.requireNonNull(getActivity()))) {
            getSkillsLeaders();
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
        }

        return root;
    }

    private void getSkillsLeaders() {
        skillViewModel.init();
        skillViewModel.getSkills().observe( getViewLifecycleOwner(), leaderSkills -> {
            if(leaderSkills != null) {
                leaderSkillList.addAll(leaderSkills);
            }
            skillsAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
            if (refreshLayout.isRefreshing()){
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        clearData();
        if(isOnline(Objects.requireNonNull(getActivity()))) {
            clearData();
            getSkillsLeaders();
        } else {
            refreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
        }
    }

    private void clearData() {
        leaderSkillList.clear();
    }
}