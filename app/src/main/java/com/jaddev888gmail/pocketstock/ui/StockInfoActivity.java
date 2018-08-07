package com.jaddev888gmail.pocketstock.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jaddev888gmail.pocketstock.R;
import com.jaddev888gmail.pocketstock.adapters.NewsAdapter;
import com.jaddev888gmail.pocketstock.model.company.CompanyRs;
import com.jaddev888gmail.pocketstock.model.news.NewsRs;
import com.jaddev888gmail.pocketstock.network.RestClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StockInfoActivity extends AppCompatActivity {

    @BindView(R.id.company_name)
    TextView companyName;
    @BindView(R.id.exchange)
    TextView exchange;
    @BindView(R.id.industry)
    TextView industry;
    @BindView(R.id.sector)
    TextView sector;
    @BindView(R.id.ceo)
    TextView ceo;
    @BindView(R.id.about)
    TextView about;

    private RestClient restClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_info);
        ButterKnife.bind(this);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();
        String ticker="empty";
        if(bundle != null){
            ticker = bundle.getString("ticker");
        }
        restClient = new RestClient();
        setCompanyInfo(ticker);
    }


    private void setCompanyInfo(final String ticker){
        restClient.getCompanyInfo(ticker).enqueue(new Callback<CompanyRs>() {
            @Override
            public void onResponse(Call<CompanyRs> call, Response<CompanyRs> response) {
                Log.i("INFO", "Responce body: " + response.body().toString());
                CompanyRs companyRs =  response.body();
                companyName.setText(companyRs.getCompanyName());
                companyName.append(" | " + ticker);
                exchange.append(companyRs.getExchange());
                industry.append(companyRs.getIndustry());
                sector.append(companyRs.getSector());
                ceo.append(companyRs.getCEO());
                about.append(companyRs.getDescription());

            }

            @Override
            public void onFailure(Call<CompanyRs> call, Throwable t) {
                Log.e("ERROR", "Error fetching response: \r\n" + t.getMessage());
            }
        });


    }
}
