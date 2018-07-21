package com.jaddev888gmail.pocketstock.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static final String BASE_URL = "https://api.iextrading.com/1.0";
    private final IexApi iexApi;

    public RestClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        iexApi = retrofit.create(IexApi.class);
    }

    public Call<Double> getPrice(String ticker){
        return iexApi.getPrice(ticker);
    }

}
