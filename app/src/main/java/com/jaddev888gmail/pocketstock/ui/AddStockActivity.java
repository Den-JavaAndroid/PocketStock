package com.jaddev888gmail.pocketstock.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jaddev888gmail.pocketstock.R;
import com.jaddev888gmail.pocketstock.database.PortfolioContentProvider;
import com.jaddev888gmail.pocketstock.database.PortfolioContract;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddStockActivity extends AppCompatActivity {
    @BindView(R.id.input_stock_symbol)
    EditText stockSymbolInput;
    @BindView(R.id.input_count_stock)
    EditText stockCountInput;
    @BindView(R.id.add_stock_button)
    Button addStockButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);
        ButterKnife.bind(this);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
/*
            addFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isFavoriteMovie()) {
                        getContentResolver().delete(Uri.parse(MovieInfoContentProvider.URI_MOVIE + "/" + movieInfo.getId()), null, null);
                    } else {

                        ContentValues movieInfoValues = new ContentValues();
                        movieInfoValues.put(MovieContract.MovieEntry.MOVIE_ID, movieInfo.getId());
                        movieInfoValues.put(MovieContract.MovieEntry.TITLE, movieInfo.getTitle());
                        movieInfoValues.put(MovieContract.MovieEntry.RELEASE_DATE, movieInfo.getReleaseDate());
                        movieInfoValues.put(MovieContract.MovieEntry.POSTER, movieInfo.getPosterPath());
                        movieInfoValues.put(MovieContract.MovieEntry.RATING, movieInfo.getVoteAverage());
                        movieInfoValues.put(MovieContract.MovieEntry.BACKDROP_POSTER, movieInfo.getBackdropPath());
                        movieInfoValues.put(MovieContract.MovieEntry.OVERVIEW, movieInfo.getOverview());
                        getContentResolver().insert(MovieInfoContentProvider.URI_MOVIE, movieInfoValues);
                    }
                }
            });
 */
        addStockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stockSymbol = stockSymbolInput.getText().toString();
                String stockCount = stockCountInput.getText().toString();
                ContentValues portfolioItemValues = new ContentValues();
                portfolioItemValues.put(PortfolioContract.PortfolioEntry.STOCK_SYMBOL, stockSymbol);
                portfolioItemValues.put(PortfolioContract.PortfolioEntry.STOCK_COUNT, Integer.parseInt(stockCount));

                getContentResolver().insert(PortfolioContentProvider.URI_PORTFOLIO, portfolioItemValues);
                Toast.makeText(AddStockActivity.this, stockCount+" shares of "+ stockSymbol +
                        " added to the portfolio", Toast.LENGTH_LONG).show();
                finish();
            }
        });


    }
}
