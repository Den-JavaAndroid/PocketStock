package com.jaddev888gmail.pocketstock.network;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IexApi {

    ///stock/aapl/price
    @GET("stock/{ticker}/price ")
    Call<Double> getPrice(@Path("ticker") String ticker);
}
