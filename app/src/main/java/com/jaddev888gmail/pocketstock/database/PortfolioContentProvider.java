package com.jaddev888gmail.pocketstock.database;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class PortfolioContentProvider extends ContentProvider {
    private PortfolioDbHelper portfolioDbHelper;
    public static final String AUTHORITY = "com.jaddev888gmail.pocketstock.database";
    public static final Uri URI_PORTFOLIO = Uri.parse(
            "content://" + AUTHORITY + "/" + PortfolioContract.PortfolioEntry.TABLE_NAME);

    /**
     * The match code for some stocks in the Portfolio table.
     */
    private static final int CODE_PORTFOLIO_DIR = 1;

    /**
     * The match code for a stock in the Portfolio table.
     */
    private static final int CODE_PORTFOLIO_ITEM = 2;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, PortfolioContract.PortfolioEntry.TABLE_NAME, CODE_PORTFOLIO_DIR);
        matcher.addURI(AUTHORITY, PortfolioContract.PortfolioEntry.TABLE_NAME + "/*", CODE_PORTFOLIO_ITEM);
        return matcher;
    }


    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case CODE_PORTFOLIO_DIR: {
                cursor = portfolioDbHelper.getReadableDatabase().query(
                        PortfolioContract.PortfolioEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            }
            case CODE_PORTFOLIO_ITEM: {
                String ticker = uri.getLastPathSegment();
                String[] selectionArguments = new String[]{ticker};
                cursor = portfolioDbHelper.getReadableDatabase().query(
                        PortfolioContract.PortfolioEntry.TABLE_NAME,
                        projection,
                        PortfolioContract.PortfolioEntry.STOCK_SYMBOL + " = ? ",
                        selectionArguments,
                        null,
                        null,
                        sortOrder);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);

        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase database = portfolioDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch (match){
            case CODE_PORTFOLIO_DIR:
                long id = database.insert(PortfolioContract.PortfolioEntry.TABLE_NAME, null, values);
                if(id >0) returnUri = ContentUris.withAppendedId(URI_PORTFOLIO, id);
                else throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase database = portfolioDbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case CODE_PORTFOLIO_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case CODE_PORTFOLIO_ITEM:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                String ticker = uri.getLastPathSegment();
                int deletedCout = database.delete(PortfolioContract.PortfolioEntry.TABLE_NAME,
                        PortfolioContract.PortfolioEntry.STOCK_SYMBOL + " = ? ", new String[]{ticker});
                return deletedCout;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        final SQLiteDatabase database = portfolioDbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case CODE_PORTFOLIO_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case CODE_PORTFOLIO_ITEM:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                String ticker = uri.getLastPathSegment();
                int updateCOunt = database.update(PortfolioContract.PortfolioEntry.TABLE_NAME, values,
                        PortfolioContract.PortfolioEntry.STOCK_SYMBOL + " = ? ", new String[]{ticker});
                return updateCOunt;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

    }
}
