package com.jaddev888gmail.pocketstock.model.news;


import android.os.Parcel;
import android.os.Parcelable;

public class PortfolioItem implements Parcelable {
    private String ticker;
    private Integer stockCount;
    private Double stockPrice;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Double stockPrice) {
        this.stockPrice = stockPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ticker);
        dest.writeValue(this.stockCount);
        dest.writeValue(this.stockPrice);
    }

    public PortfolioItem() {
    }

    protected PortfolioItem(Parcel in) {
        this.ticker = in.readString();
        this.stockCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.stockPrice = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<PortfolioItem> CREATOR = new Parcelable.Creator<PortfolioItem>() {
        @Override
        public PortfolioItem createFromParcel(Parcel source) {
            return new PortfolioItem(source);
        }

        @Override
        public PortfolioItem[] newArray(int size) {
            return new PortfolioItem[size];
        }
    };
}
