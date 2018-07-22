package com.jaddev888gmail.pocketstock.database;


import android.provider.BaseColumns;

public class PortfolioContract {

    public static final class PortfolioEntry implements BaseColumns {
        public static final String TABLE_NAME = "portfolioDb";
        public static final String STOCK_SYMBOL = "ticker";
        public static final String STOCK_COUNT = "count";
    }
}
