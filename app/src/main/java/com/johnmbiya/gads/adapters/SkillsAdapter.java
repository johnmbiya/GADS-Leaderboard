package com.johnmbiya.gads.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.johnmbiya.gads.R;
import com.johnmbiya.gads.holders.HoursViewHolder;
import com.johnmbiya.gads.holders.SkillsViewHolder;
import com.johnmbiya.gads.models.LeaderHour;
import com.johnmbiya.gads.models.LeaderSkill;

import java.util.List;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsViewHolder> {

    private List<LeaderSkill> leaderSkills;

    public SkillsAdapter(List<LeaderSkill> leaderSkills) {
        this.leaderSkills = leaderSkills;
    }

    @NonNull
    @Override
    public SkillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_skill, parent, false);
        return new SkillsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillsViewHolder holder, int position) {
        LeaderSkill leaderSkill = leaderSkills.get(position);
        holder.bind(leaderSkill);
    }

    @Override
    public int getItemCount() {
        return leaderSkills.size();
    }
}
