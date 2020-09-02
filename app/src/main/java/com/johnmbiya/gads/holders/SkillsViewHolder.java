package com.johnmbiya.gads.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.johnmbiya.gads.R;
import com.johnmbiya.gads.models.LeaderHour;
import com.johnmbiya.gads.models.LeaderSkill;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class SkillsViewHolder extends RecyclerView.ViewHolder {

    private ImageView badge;
    private TextView name;
    private TextView details;

    public SkillsViewHolder(@NonNull View itemView) {
        super(itemView);

        badge = itemView.findViewById(R.id.badge);
        name = itemView.findViewById(R.id.name);
        details = itemView.findViewById(R.id.details);
    }

    public void bind(LeaderSkill leaderSkill){
        Picasso.get()
                .load(leaderSkill.getBadgeUrl())
                .placeholder(R.drawable.top_learner)
                .into(badge);
        name.setText(leaderSkill.getName());

        String summary = String.format(Locale.ENGLISH, "%d Skill IQ Score, %s", leaderSkill.getScore(), leaderSkill.getCountry());
        details.setText(summary);
    }
}
