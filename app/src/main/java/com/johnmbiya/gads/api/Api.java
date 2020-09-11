package com.johnmbiya.gads.api;

import com.johnmbiya.gads.models.LeaderHour;
import com.johnmbiya.gads.models.LeaderSkill;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.johnmbiya.gads.api.CONSTANTS.HOURS_PATH;
import static com.johnmbiya.gads.api.CONSTANTS.SKILLS_PATH;

public interface Api {

    @GET(HOURS_PATH)
    Call<List<LeaderHour>> getLeaderHours();

    @GET(SKILLS_PATH)
    Call<List<LeaderSkill>> getLeaderSkillIq();
}
