package com.jaddev888gmail.pocketstock.network;


import com.jaddev888gmail.pocketstock.model.company.CompanyRs;
import com.jaddev888gmail.pocketstock.model.news.NewsRs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IexApi {

    ///stock/aapl/price
    @GET("stock/{ticker}/price ")
    Call<Double> getPrice(@Path("ticker") String ticker);

    //    /stock/market/news/last/5
    @GET("stock/market/news/last/{days}")
    Call<List<NewsRs>> getLastNews(@Path("days") String days);

    @GET("stock/{ticker}/company ")
    Call<CompanyRs> getCompanyInfo(@Path("ticker") String ticker);
}
