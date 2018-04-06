package com.example.y_xl.wan.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.y_xl.wan.R;
import com.example.y_xl.wan.model.bean.Order_bean;
import com.example.y_xl.wan.presenter.Order_P;
import com.example.y_xl.wan.view.adapter.QuanbuAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Y_xl on 2018/4/5.
 */

public class Quanbu_fragment extends Fragment implements OrderV_I {

    public static int page = 1;
    public static List<Order_bean.DataBean> list = new ArrayList<>();
    private XRecyclerView refreshLayout;
    QuanbuAdapter quanbuAdapter;
    private Order_P order_p;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.quanbu, null);
        initView(view);
        //获取数据
        order_p = new Order_P(this);
        order_p.getdata(page);
        return view;
    }



    @Override
    public void success(String S) {
        //获取到数据

        Order_bean order_bean = new Gson().fromJson(S, Order_bean.class);
        List<Order_bean.DataBean> data = order_bean.getData();
        list.addAll(data);
        //设置适配器
        setAdapter();

    }

    private void setAdapter() {
        if (quanbuAdapter == null) {
            refreshLayout.setLayoutManager(new LinearLayoutManager(getActivity()));
            quanbuAdapter = new QuanbuAdapter(getActivity(), list,order_p);
            refreshLayout.setAdapter(quanbuAdapter);
        } else {
            //刷新适配器
            quanbuAdapter.notifyDataSetChanged();
        }
    }

    private void initView(View view) {
        refreshLayout = (XRecyclerView) view.findViewById(R.id.recycler);
    }
}
