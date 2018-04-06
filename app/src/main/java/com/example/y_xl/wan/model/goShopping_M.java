package com.example.y_xl.wan.model;

import android.util.Log;


import com.example.y_xl.wan.presenter.goShoppingP_I;
import com.example.y_xl.wan.util.BaseObserver;
import com.example.y_xl.wan.util.RetrofitAndOkHttpAndRxAndroid;

import java.util.HashMap;

/**
 * Created by Y_xl on 2018/4/5.
 */

public class goShopping_M {

    private final goShoppingP_I goShoppingP_i;

    public goShopping_M(goShoppingP_I goShoppingP_i) {
        this.goShoppingP_i = goShoppingP_i;
    }

    public void getdata(String goShopping_api) {
        HashMap<String, String> map = new HashMap<>();
        map.put("source","android");
        map.put("uid","2775");
        RetrofitAndOkHttpAndRxAndroid.get(goShopping_api,map).subscribe(new BaseObserver() {
            @Override
            public void onSuccess(String result) {
//                Log.d("TAG",result+"");
                goShoppingP_i.success(result);

            }
        });
    }
}
