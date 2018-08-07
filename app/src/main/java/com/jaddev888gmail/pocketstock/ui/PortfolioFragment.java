package com.jaddev888gmail.pocketstock.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jaddev888gmail.pocketstock.R;
import com.jaddev888gmail.pocketstock.adapters.PortfolioAdapter;
import com.jaddev888gmail.pocketstock.database.PortfolioContentProvider;
import com.jaddev888gmail.pocketstock.database.PortfolioContract;
import com.jaddev888gmail.pocketstock.model.news.PortfolioItem;
import com.jaddev888gmail.pocketstock.network.RestClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PortfolioFragment extends Fragment implements PortfolioAdapter.ItemClickListener {

    @BindView(R.id.summ_money)
    TextView totalMoney;

    @BindView(R.id.summ_stocks)
    TextView totalStocks;

    private RestClient restClient;
    @BindView(R.id.stock_list)
    RecyclerView stockListRecyclerView;

    private ArrayList<PortfolioItem> portfolioItemList;


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
        stockListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadPortfolioFromDb();


        return view;
    }


    private void loadPortfolioFromDb() {
        Cursor data = getContext().getContentResolver().query(PortfolioContentProvider.URI_PORTFOLIO, null, null, null, null);
        int summStocks=0;
        double summMoney=0.0;

        if (data.getCount() != 0) {
            portfolioItemList = new ArrayList<>();
            while (data.moveToNext()) {
                int countStocks = data.getInt(1);
                summStocks = summStocks+countStocks;
                summMoney = summMoney + data.getDouble(2)*countStocks;
                PortfolioItem portfolioItem = new PortfolioItem();
                portfolioItem.setTicker(data.getString(0));
                portfolioItem.setStockCount(countStocks);
                portfolioItem.setStockPrice(data.getDouble(2));
                portfolioItemList.add(portfolioItem);
            }
            totalStocks.setText("Bought shares\n" +summStocks);
            String formattedSummMoney = String.format("%.2f", summMoney);
            totalMoney.setText("Invested money \n" +formattedSummMoney+"$");
            PortfolioAdapter portfolioAdapter = new PortfolioAdapter(getContext(), portfolioItemList, PortfolioFragment.this);
            stockListRecyclerView.setAdapter(portfolioAdapter);
        } else {
            Toast.makeText(getContext(), "PORTFOLIO IS EMPTY. ADD STOCKS.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPortfolioFromDb();
    }

    @Override
    public void onItemClick(PortfolioItem portfolioItem) {

    }
}
