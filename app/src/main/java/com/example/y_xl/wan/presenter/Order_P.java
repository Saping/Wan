package com.example.y_xl.wan.presenter;


import com.example.y_xl.wan.model.Order_M;
import com.example.y_xl.wan.view.fragment.OrderV_I;

/**
 * Created by Y_xl on 2018/4/5.
 */

public class Order_P implements OrderP_I{

    private final Order_M order_m;
    private final OrderV_I orderV_i;

    public Order_P(OrderV_I orderV_i) {
        this.orderV_i = orderV_i;
        order_m = new Order_M(this);
    }

    public void getdata(int page) {
        order_m.getdata(page);
    }

    @Override
    public void success(String s) {
        orderV_i.success(s);
    }
}
