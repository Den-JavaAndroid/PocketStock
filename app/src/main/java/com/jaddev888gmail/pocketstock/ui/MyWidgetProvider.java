package com.jaddev888gmail.pocketstock.ui;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;

import com.jaddev888gmail.pocketstock.R;
import com.jaddev888gmail.pocketstock.database.PortfolioContentProvider;


public class MyWidgetProvider extends AppWidgetProvider {
    private static final String ACTION_CLICK = "ACTION_CLICK";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        // Get all ids
        ComponentName thisWidget = new ComponentName(context,
                MyWidgetProvider.class);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.widget_layout);

        int summStocks = 0;
        double summMoney = 0.0;
        Cursor data = context.getContentResolver().query(PortfolioContentProvider.URI_PORTFOLIO, null, null, null, null);

        if (data.getCount() != 0) {
            while (data.moveToNext()) {
                int countStocks = data.getInt(1);
                summStocks = summStocks + countStocks;
                summMoney = summMoney + data.getDouble(2) * countStocks;
            }
            data.close();

            String formattedSummMoney = String.format("%.2f", summMoney);


            remoteViews.setTextViewText(R.id.total_money,
                    context.getResources().getString(R.string.total_money)
                            + ": " + formattedSummMoney + "$");
            remoteViews.setTextViewText(R.id.total_stocks,
                    context.getResources().getString(R.string.total_stocks) + ": " + summStocks);

            // Register an onClickListener
            Intent intent = new Intent(context, MyWidgetProvider.class);

            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.total_money, pendingIntent);
            remoteViews.setOnClickPendingIntent(R.id.total_stocks, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetIds[0], remoteViews);

        }
    }
}

