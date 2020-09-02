package com.johnmbiya.gads.repository;

import androidx.lifecycle.MutableLiveData;

import com.johnmbiya.gads.api.Api;
import com.johnmbiya.gads.api.RetrofitService;
import com.johnmbiya.gads.models.LeaderHour;
import com.johnmbiya.gads.models.LeaderSkill;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearderRepository {

    public static LearderRepository learderRepository;

    private final Api restApi;

    public static LearderRepository getInstance() {
        if (learderRepository == null) {
            learderRepository = new LearderRepository();
        }
        return learderRepository;
    }

    public LearderRepository() { restApi = RetrofitService.createService(Api.class); }

    public MutableLiveData<List<LeaderHour>> getLeaderHours(){
        final MutableLiveData<List<LeaderHour>> hoursMutableLiveData = new MutableLiveData<>();
        restApi.getLeaderHours().enqueue(new Callback<List<LeaderHour>>() {
            @Override
            public void onResponse(@NotNull Call<List<LeaderHour>> call, @NotNull Response<List<LeaderHour>> response) {
                if(response.isSuccessful()){
                    hoursMutableLiveData.setValue(response.body());
                } else {
                    hoursMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<LeaderHour>> call, @NotNull Throwable t) {
                hoursMutableLiveData.setValue(null);
            }
        });

        return hoursMutableLiveData;
    }


    public MutableLiveData<List<LeaderSkill>> getLeaderSkills(){
        final MutableLiveData<List<LeaderSkill>> skillsMutableLiveData = new MutableLiveData<>();
        restApi.getLeaderSkillIq().enqueue(new Callback<List<LeaderSkill>>() {
            @Override
            public void onResponse(@NotNull Call<List<LeaderSkill>> call, @NotNull Response<List<LeaderSkill>> response) {
                if(response.isSuccessful()) {
                    skillsMutableLiveData.setValue(response.body());
                } else {
                    skillsMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<LeaderSkill>> call, @NotNull Throwable t) {
                skillsMutableLiveData.setValue(null);
            }
        });

        return skillsMutableLiveData;
    }
}
