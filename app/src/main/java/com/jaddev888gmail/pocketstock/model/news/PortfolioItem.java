package com.jaddev888gmail.pocketstock.model.news;


public class PortfolioItem {
    private String ticker;
    private Integer stockCount;

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
}
