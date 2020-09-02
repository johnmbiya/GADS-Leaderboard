package com.johnmbiya.gads.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.johnmbiya.gads.R;
import com.johnmbiya.gads.adapters.HoursAdapter;
import com.johnmbiya.gads.adapters.SkillsAdapter;
import com.johnmbiya.gads.models.LeaderHour;
import com.johnmbiya.gads.models.LeaderSkill;
import com.johnmbiya.gads.viewmodels.HourViewModel;
import com.johnmbiya.gads.viewmodels.SkillViewModel;

import java.util.ArrayList;
import java.util.List;


public class SkillsFragment extends Fragment {

    private SkillViewModel skillViewModel;
    private SkillsAdapter skillsAdapter;
    private List<LeaderSkill> leaderSkillList = new ArrayList<>();

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        skillsAdapter = new SkillsAdapter(leaderSkillList);
        recyclerView.setAdapter(skillsAdapter);

        skillViewModel.init();
        skillViewModel.getSkills().observe( getViewLifecycleOwner(), leaderSkills -> {
            if(leaderSkills != null) {
                leaderSkillList.addAll(leaderSkills);
            }
            skillsAdapter.notifyDataSetChanged();
        });

        return root;
    }
}