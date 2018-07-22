package com.jaddev888gmail.pocketstock.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jaddev888gmail.pocketstock.R;
import com.jaddev888gmail.pocketstock.adapters.NewsAdapter;
import com.jaddev888gmail.pocketstock.model.news.NewsRs;
import com.jaddev888gmail.pocketstock.network.RestClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsFragment extends Fragment implements NewsAdapter.ItemClickListener{

    @BindView(R.id.news)
    RecyclerView newsRecyclerView;
    @BindView(R.id.progress_load_news)
    ProgressBar progressBar;

    private RestClient restClient;
    private ArrayList<NewsRs> newsList = new ArrayList<>();

    public NewsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        ButterKnife.bind(this, view);

        restClient = new RestClient();
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadNews("aapl");
        return view;
    }


    private void loadNews(String ticker) {
        progressBar.setVisibility(View.VISIBLE);
        newsRecyclerView.setVisibility(View.INVISIBLE);
        restClient.getStockNews(ticker).enqueue(new Callback<List<NewsRs>>() {
            @Override
            public void onResponse(Call<List<NewsRs>> call, Response<List<NewsRs>> response) {
                Log.i("INFO", "Responce body: " + response.body().toString());
                newsList = (ArrayList<NewsRs>) response.body();
                NewsAdapter reciepsAdapter = new NewsAdapter(getContext(), newsList, NewsFragment.this);
                progressBar.setVisibility(View.INVISIBLE);
                newsRecyclerView.setVisibility(View.VISIBLE);
                newsRecyclerView.setAdapter(reciepsAdapter);
            }

            @Override
            public void onFailure(Call<List<NewsRs>> call, Throwable t) {
                Log.e("ERROR", "Error fetching response: \r\n" + t.getMessage());
            }
        });

    }

    @Override
    public void onItemClick(NewsRs newsRs) {
    }
}
