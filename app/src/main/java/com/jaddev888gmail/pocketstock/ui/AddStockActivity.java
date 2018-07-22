package com.jaddev888gmail.pocketstock.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.jaddev888gmail.pocketstock.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddStockActivity extends AppCompatActivity {
    @BindView(R.id.input_stock_symbol)
    EditText stockSymbolInput;
    @BindView(R.id.input_count_stock)
    EditText stockCountInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);
        ButterKnife.bind(this);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



    }
}
