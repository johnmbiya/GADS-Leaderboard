package com.johnmbiya.gads.api;

import com.johnmbiya.gads.models.LeaderHour;
import com.johnmbiya.gads.models.LeaderSkill;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("api/hours")
    Call<List<LeaderHour>> getLeaderHours();

    @GET("api/skilliq")
    Call<List<LeaderSkill>> getLeaderSkillIq();
}
