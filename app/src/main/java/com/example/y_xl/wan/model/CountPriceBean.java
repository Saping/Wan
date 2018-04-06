package com.example.y_xl.wan.model;

/**
 * Created by Y_xl on 2018/4/5.
 */

public class CountPriceBean {
    private int count;
    private String priceString;

    public CountPriceBean(String priceString, int count) {
        this.priceString = priceString;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPriceString() {
        return priceString;
    }

    public void setPriceString(String priceString) {
        this.priceString = priceString;
    }
}
