package com.jaddev888gmail.pocketstock.ui;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jaddev888gmail.pocketstock.R;
import com.jaddev888gmail.pocketstock.database.PortfolioContentProvider;
import com.jaddev888gmail.pocketstock.database.PortfolioContract;
import com.jaddev888gmail.pocketstock.network.RestClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddStockActivity extends AppCompatActivity {
    @BindView(R.id.input_stock_symbol)
    EditText stockSymbolInput;
    @BindView(R.id.input_count_stock)
    EditText stockCountInput;
    @BindView(R.id.add_stock_button)
    Button addStockButton;

    private RestClient restClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);
        ButterKnife.bind(this);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        restClient = new RestClient();
    }

    //check that portfolio contains same stock.
    private boolean hasStockInPortfolio(String stockSymbol) {
        return getContentResolver().query(PortfolioContentProvider.URI_PORTFOLIO, null,
                PortfolioContract.PortfolioEntry.STOCK_SYMBOL + "=?", new String[]{String.valueOf(stockSymbol)},
                null).getCount() != 0;
    }

    private Integer getCountStockInPortfolio(String stockSymbol) {
        Cursor cursor = getContentResolver().query(PortfolioContentProvider.URI_PORTFOLIO, null,
                PortfolioContract.PortfolioEntry.STOCK_SYMBOL + "=?", new String[]{String.valueOf(stockSymbol)},
                null);
        if (cursor != null) {
            cursor.moveToNext();
            Integer countStock = cursor.getInt(1);
            cursor.close();
            return countStock;
        }
        return 0;
    }


    @OnClick(R.id.add_stock_button)
    void saveStockInDb() {

        final String stockSymbol = stockSymbolInput.getText().toString();
        final String stockCount = stockCountInput.getText().toString();

        if (!TextUtils.isEmpty(stockSymbol)) {
            if (!TextUtils.isEmpty(stockCount)) {
                final Double[] price = new Double[1];
                restClient.getPrice(stockSymbol).enqueue(new Callback<Double>() {
                    @Override
                    public void onResponse(Call<Double> call, Response<Double> response) {
                        price[0] = response.body();
                        ContentValues portfolioItemValues = new ContentValues();

                        if (price[0] != null) {
                            portfolioItemValues.put(PortfolioContract.PortfolioEntry.STOCK_PRICE, price[0]);

                            if (hasStockInPortfolio(stockSymbol)) {
                                Integer newStockCount = Integer.valueOf(stockCount) + getCountStockInPortfolio(stockSymbol);

                                portfolioItemValues.put(PortfolioContract.PortfolioEntry.STOCK_SYMBOL, stockSymbol);
                                portfolioItemValues.put(PortfolioContract.PortfolioEntry.STOCK_COUNT, newStockCount);
                                getContentResolver().update(Uri.parse(PortfolioContentProvider.URI_PORTFOLIO + "/" + stockSymbol), portfolioItemValues, null, null);

                            } else {
                                portfolioItemValues.put(PortfolioContract.PortfolioEntry.STOCK_SYMBOL, stockSymbol);
                                portfolioItemValues.put(PortfolioContract.PortfolioEntry.STOCK_COUNT, Integer.parseInt(stockCount));
                                getContentResolver().insert(PortfolioContentProvider.URI_PORTFOLIO, portfolioItemValues);
                            }


                            //automatically update widget info
                            Intent intent = new Intent(AddStockActivity.this, MyWidgetProvider.class);
                            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                            int[] ids = AppWidgetManager.getInstance(getApplication())
                                    .getAppWidgetIds(new ComponentName(getApplication(), MyWidgetProvider.class));
                            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
                            sendBroadcast(intent);

                            Toast.makeText(AddStockActivity.this, getResources().getString(R.string.message_for_add_stock, stockCount, stockSymbol), Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(AddStockActivity.this, getResources().getString(R.string.not_price_for_add_stock, stockSymbol), Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        price[0] = 0.0;
                    }
                });
            } else {
                Toast.makeText(AddStockActivity.this, getResources().getString(R.string.not_count_message), Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(AddStockActivity.this, getResources().getString(R.string.not_ticker_message), Toast.LENGTH_LONG).show();
        }

    }
}
