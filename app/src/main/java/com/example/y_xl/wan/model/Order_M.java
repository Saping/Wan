package com.example.y_xl.wan.model;

import android.util.Log;


import com.example.y_xl.wan.presenter.OrderP_I;
import com.example.y_xl.wan.util.BaseObserver;
import com.example.y_xl.wan.util.My_api;
import com.example.y_xl.wan.util.RetrofitAndOkHttpAndRxAndroid;

import java.util.HashMap;

/**
 * Created by Y_xl on 2018/4/5.
 */

public class Order_M {

    private final OrderP_I orderP_i;

    public Order_M(OrderP_I orderP_i) {
        this.orderP_i = orderP_i;
    }

    public void getdata(int page) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", "2775");
        map.put("page", page + "");
        RetrofitAndOkHttpAndRxAndroid.post(My_api.Order_api,map).subscribe(new BaseObserver() {
            @Override
            public void onSuccess(String result) {
//                Log.d("TAG",""+result);
                orderP_i.success(result);
            }
        });
    }
}
