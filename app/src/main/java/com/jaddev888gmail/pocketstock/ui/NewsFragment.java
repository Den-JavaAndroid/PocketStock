package com.jaddev888gmail.pocketstock.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.jaddev888gmail.pocketstock.R;
import com.jaddev888gmail.pocketstock.adapters.NewsAdapter;
import com.jaddev888gmail.pocketstock.model.news.NewsRs;
import com.jaddev888gmail.pocketstock.network.RestClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;


public class NewsFragment extends Fragment implements NewsAdapter.ItemClickListener {

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

        if (savedInstanceState == null) {
            loadNews("25"); //load news for last 25 days
        } else {
            newsList = savedInstanceState.getParcelableArrayList("newsList");
            setNews();
        }

        return view;
    }

    private void loadNews(String countDaysForNews) {
        new LoadNews().execute(countDaysForNews);
    }


    @Override
    public void onItemClick(NewsRs newsRs) {
    }


    private class LoadNews extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            newsRecyclerView.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Response<List<NewsRs>> news = restClient.getLastNews(strings[0]).execute();
                newsList = (ArrayList<NewsRs>) news.body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newsList.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            setNews();
        }
    }

    private void setNews() {
        NewsAdapter reciepsAdapter = new NewsAdapter(getContext(), newsList, NewsFragment.this);
        progressBar.setVisibility(View.INVISIBLE);
        newsRecyclerView.setVisibility(View.VISIBLE);
        newsRecyclerView.setAdapter(reciepsAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("newsList", newsList);
        super.onSaveInstanceState(outState);
    }
}
