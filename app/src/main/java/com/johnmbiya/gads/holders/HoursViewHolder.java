package com.johnmbiya.gads.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.johnmbiya.gads.R;
import com.johnmbiya.gads.models.LeaderHour;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class HoursViewHolder extends RecyclerView.ViewHolder {

    private ImageView badge;
    private TextView name;
    private TextView details;

    public HoursViewHolder(@NonNull View itemView) {
        super(itemView);

        badge = itemView.findViewById(R.id.badge);
        name = itemView.findViewById(R.id.name);
        details = itemView.findViewById(R.id.details);
    }

    public void bind(LeaderHour leaderHour){
        Picasso.get()
                .load(leaderHour.getBadgeUrl())
                .placeholder(R.drawable.top_learner)
                .into(badge);
        name.setText(leaderHour.getName());

        String summary = String.format(Locale.ENGLISH, "%d learning hours, %s", leaderHour.getHours(), leaderHour.getCountry());
        details.setText(summary);
    }
}
