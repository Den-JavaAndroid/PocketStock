package com.jaddev888gmail.pocketstock.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaddev888gmail.pocketstock.R;
import com.jaddev888gmail.pocketstock.model.news.PortfolioItem;

import java.util.ArrayList;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.ViewHolder>{
    private final LayoutInflater mInflater;
    private final ArrayList<PortfolioItem> portfolioItems;
    private final Context context;

    public PortfolioAdapter(Context context, ArrayList<PortfolioItem> portfolioItems) {
        this.mInflater = LayoutInflater.from(context);
        this.portfolioItems = portfolioItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.portfolio_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.stockName.setText(portfolioItems.get(position).getTicker());
        holder.count.setText(portfolioItems.get(position).getStockCount()+"");
        holder.price.setText(portfolioItems.get(position).getStockPrice()+"");
    }

    @Override
    public int getItemCount() {
        return portfolioItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView stockName;
        final TextView count;
        final TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            stockName = itemView.findViewById(R.id.stockName);
            count = itemView.findViewById(R.id.count);
            price = itemView.findViewById(R.id.price);

        }
    }
}
