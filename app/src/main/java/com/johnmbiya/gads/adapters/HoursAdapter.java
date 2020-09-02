package com.johnmbiya.gads.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.johnmbiya.gads.R;
import com.johnmbiya.gads.holders.HoursViewHolder;
import com.johnmbiya.gads.models.LeaderHour;

import java.util.List;

public class HoursAdapter extends RecyclerView.Adapter<HoursViewHolder> {

    private List<LeaderHour> leaderHours;

    public HoursAdapter(List<LeaderHour> leaderHours) {
        this.leaderHours = leaderHours;
    }

    @NonNull
    @Override
    public HoursViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new HoursViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HoursViewHolder holder, int position) {
        LeaderHour leaderHour = leaderHours.get(position);
        holder.bind(leaderHour);
    }

    @Override
    public int getItemCount() {
        return leaderHours.size();
    }
}
