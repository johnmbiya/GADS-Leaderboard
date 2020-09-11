package com.johnmbiya.gads.api;

import com.johnmbiya.gads.models.LeaderHour;
import com.johnmbiya.gads.models.LeaderSkill;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static com.johnmbiya.gads.api.CONSTANTS.FORM_EMAIL;
import static com.johnmbiya.gads.api.CONSTANTS.FORM_FNAME;
import static com.johnmbiya.gads.api.CONSTANTS.FORM_LNAME;
import static com.johnmbiya.gads.api.CONSTANTS.FORM_PATH;
import static com.johnmbiya.gads.api.CONSTANTS.FORM_PROJECT;

public interface ApiForm {

    @POST(FORM_PATH)
    @FormUrlEncoded
    Call<Void> submitProject(
            @Field(FORM_EMAIL) String emailAdress,
            @Field(FORM_FNAME) String firstName,
            @Field(FORM_LNAME) String lastName,
            @Field(FORM_PROJECT) String projectLink
    );
}
