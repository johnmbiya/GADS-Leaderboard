package com.johnmbiya.gads.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    public static final String BASE_URL = "https://gadsapi.herokuapp.com/";
    public static final String FORM_URL = " https://docs.google.com/forms/d/e/";

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
