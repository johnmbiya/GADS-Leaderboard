package com.johnmbiya.gads.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.johnmbiya.gads.api.CONSTANTS.BASE_URL;
import static com.johnmbiya.gads.api.CONSTANTS.FORM_URL;

public class RetrofitService {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }


    private static Retrofit retrofitForm = new Retrofit.Builder()
            .baseUrl(FORM_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createFormService(Class<S> serviceClass) {
        return retrofitForm.create(serviceClass);
    }

}
