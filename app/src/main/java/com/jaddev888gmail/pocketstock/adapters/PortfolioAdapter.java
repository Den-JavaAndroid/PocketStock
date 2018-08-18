package com.jaddev888gmail.pocketstock.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaddev888gmail.pocketstock.R;
import com.jaddev888gmail.pocketstock.model.news.PortfolioItem;
import com.jaddev888gmail.pocketstock.ui.StockInfoActivity;

import java.util.ArrayList;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.ViewHolder> {
    private final LayoutInflater mInflater;
    private final ArrayList<PortfolioItem> portfolioItems;
    private final Context context;
    private ItemClickListener mClickListener;


    public PortfolioAdapter(Context context, ArrayList<PortfolioItem> portfolioItems, ItemClickListener itemClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.portfolioItems = portfolioItems;
        this.context = context;
        this.mClickListener = itemClickListener;
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
        holder.count.setText(context.getResources().getString(R.string.shares, portfolioItems.get(position).getStockCount()));
        holder.price.setText(context.getResources().getString(R.string.buy_price, portfolioItems.get(position).getStockPrice()));
    }

    @Override
    public int getItemCount() {
        return portfolioItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView stockName;
        final TextView count;
        final TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            stockName = itemView.findViewById(R.id.stockName);
            count = itemView.findViewById(R.id.count);
            price = itemView.findViewById(R.id.price);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                Intent stockInfoActivity = new Intent(view.getContext(), StockInfoActivity.class);
                stockInfoActivity.putExtra("ticker", portfolioItems.get(getAdapterPosition()).getTicker());
                view.getContext().startActivity(stockInfoActivity);
            }

        }
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(PortfolioItem portfolioItem);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}
