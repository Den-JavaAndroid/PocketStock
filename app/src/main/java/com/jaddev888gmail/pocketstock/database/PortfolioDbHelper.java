package com.jaddev888gmail.pocketstock.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PortfolioDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "portfolio.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "
            + PortfolioContract.PortfolioEntry.TABLE_NAME + "( " +
            PortfolioContract.PortfolioEntry.STOCK_SYMBOL + " text primary key, " +
            PortfolioContract.PortfolioEntry.STOCK_COUNT + " integer not null, " +
            PortfolioContract.PortfolioEntry.STOCK_PRICE + " real not null);";

    public PortfolioDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(PortfolioDbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + PortfolioContract.PortfolioEntry.TABLE_NAME);
        onCreate(db);
    }
}
