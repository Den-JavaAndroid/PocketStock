package com.jaddev888gmail.pocketstock.adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jaddev888gmail.pocketstock.R;
import com.jaddev888gmail.pocketstock.model.news.NewsRs;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final LayoutInflater mInflater;
    private final ArrayList<NewsRs> news;
    private final Context context;
    private ItemClickListener mClickListener;

    public NewsAdapter(Context context, ArrayList<NewsRs> news, ItemClickListener itemClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.news = news;
        this.context = context;
        mClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.newsSummary.setText(news.get(position).getSummary());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView newsSummary;

        public ViewHolder(View itemView) {
            super(itemView);
            newsSummary = itemView.findViewById(R.id.news_summary);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(news.get(getAdapterPosition()));
                Toast.makeText(context,context.getResources().getString(R.string.open_news) + news.get(getAdapterPosition()).getHeadline(), Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.get(getAdapterPosition()).getUrl()));
                context.startActivity(myIntent);

            }

        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(NewsRs newsRs);
    }
}
