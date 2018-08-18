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

    private String ticker;
    private CompanyRs companyRs;


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
        if(bundle != null){
            ticker = bundle.getString("ticker");
        }
        restClient = new RestClient();

        if(savedInstanceState == null){
            setCompanyInfo(ticker);
        } else {
            ticker = savedInstanceState.getString("ticker");
            companyRs = (CompanyRs) savedInstanceState.getParcelable("companyInfo");
            setCompanyInfo(companyRs);
        }

    }


    private void setCompanyInfo(final String ticker){
        restClient.getCompanyInfo(ticker).enqueue(new Callback<CompanyRs>() {
            @Override
            public void onResponse(Call<CompanyRs> call, Response<CompanyRs> response) {
                Log.i("INFO", "Responce body: " + response.body().toString());
                companyRs =  response.body();
                setCompanyInfo(companyRs);
            }

            @Override
            public void onFailure(Call<CompanyRs> call, Throwable t) {
                Log.e("ERROR", "Error fetching response: \r\n" + t.getMessage());
            }
        });

    }

    private void setCompanyInfo(CompanyRs companyRs){
        companyName.setText(companyRs.getCompanyName());
        companyName.append(" | " + ticker);
        exchange.append(companyRs.getExchange());
        industry.append(companyRs.getIndustry());
        sector.append(companyRs.getSector());
        ceo.append(companyRs.getCEO());
        about.append(companyRs.getDescription());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("ticker", ticker);
        outState.putParcelable("companyInfo", companyRs);
        super.onSaveInstanceState(outState);
    }
}
