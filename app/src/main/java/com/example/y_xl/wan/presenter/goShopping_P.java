package com.example.y_xl.wan.presenter;


import com.example.y_xl.wan.model.goShopping_M;
import com.example.y_xl.wan.view.activity.goShoppingV_I;

/**
 * Created by Y_xl on 2018/4/5.
 */

public class goShopping_P implements goShoppingP_I{

    private final goShopping_M goShopping_m;
    private final goShoppingV_I goShoppingV_i;

    public goShopping_P(goShoppingV_I goShoppingV_i) {
        this.goShoppingV_i = goShoppingV_i;
        goShopping_m = new goShopping_M(this);
    }

    public void getdata(String goShopping_api) {
        goShopping_m.getdata(goShopping_api);
    }

    @Override
    public void success(String s) {
        goShoppingV_i.success(s);
    }
}
