package com.jaddev888gmail.pocketstock.network;


import com.jaddev888gmail.pocketstock.model.news.NewsRs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IexApi {

    ///stock/aapl/price
    @GET("stock/{ticker}/price ")
    Call<Double> getPrice(@Path("ticker") String ticker);

    //    /stock/aapl/news
    @GET("stock/{ticker}/news ")
    Call<List<NewsRs>> getStockNews(@Path("ticker") String ticker);
}
