package com.jaddev888gmail.pocketstock.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaddev888gmail.pocketstock.R;
import com.jaddev888gmail.pocketstock.network.RestClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PortfolioFragment extends Fragment {

    @BindView(R.id.summ_money)
    TextView totalMoney;
    private RestClient restClient;


    public PortfolioFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.portfolio_fragment, container, false);
        ButterKnife.bind(this, view);
        restClient = new RestClient();
        getPrice("aapl");
        return view;
    }


    private void getPrice(String ticker) {
        final Double[] price = new Double[1];
        restClient.getPrice(ticker).enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                price[0] = response.body();
                String prices = String.valueOf(price[0]);
                totalMoney.setText(prices);
                totalMoney.append("$");

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                price[0] = 0.0;
            }
        });
    }

}
