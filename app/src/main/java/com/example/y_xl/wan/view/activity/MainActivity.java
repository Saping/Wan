package com.example.y_xl.wan.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.y_xl.wan.R;
import com.example.y_xl.wan.model.goShopping_bean;
import com.example.y_xl.wan.presenter.goShopping_P;
import com.example.y_xl.wan.util.My_api;
import com.example.y_xl.wan.view.adapter.Myadapter;
import com.example.y_xl.wan.view.example.MyExpandableListView;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements goShoppingV_I{
    private MyExpandableListView my_expandableListview;
    private CheckBox allcheck;
    public static TextView tol;
    public static TextView gobuy;
    private Myadapter myadapter;
    private goShopping_P goShopping_p;

    private String priceString;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        //查看购物车展示数据
        goShopping_p = new goShopping_P(this);
        goShopping_p.getdata(My_api.goShopping_api);
    }
    @Override
    public void success(String S) {
        //判断获取的数据不为空
        if(!TextUtils.isEmpty(S)) {
            //获取导数据   解析展示
            goShopping_bean goShopping_bean = new Gson().fromJson(S, goShopping_bean.class);
            List<goShopping_bean.DataBean> data = goShopping_bean.getData();

            //设置二级列表的适配器    首先我得展示出来
            myadapter = new Myadapter(MainActivity.this, data, goShopping_p);
            my_expandableListview.setAdapter(myadapter);
            //去掉小箭头
            my_expandableListview.setGroupIndicator(null);
            //展开全部
            for (int i = 0; i < data.size(); i++) {
                my_expandableListview.expandGroup(i);
            }
            //l.根据某一个组中的二级所有的子条目是否选中,确定当前一级列表是否选中
            for (int i = 0; i < data.size(); i++) {
                //如果在当前一级列表中
                if (isChildInGroupChecked(i, data)) {
                    //选中一级列表
                    data.get(i).setGroup_check(true);
                }
            }
            //2.设置是否全选选中...根据所有的一级列表是否选中,确定全选是否选中
            allcheck.setChecked(isAllGroupChecked(data));
            //点击全选
            allcheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //判断适配器是否为空
                    if (myadapter != null) {
                        myadapter.setAllChildChecked(allcheck.isChecked());
                    }
                }
            });
            //3.计算  价格  和数目
            myadapter.sendPriceAndCount();
            //点击结算
            gobuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("TAG","");
                    Intent intent = new Intent(MainActivity.this, OrderActivity.class);
//                    intent.putExtra("price", priceString);
//                    intent.putExtra("count", count);
                    startActivity(intent);
                }
            });
        }
    }

    private boolean isAllGroupChecked(List<goShopping_bean.DataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            //如果有没选中的
            if (!data.get(i).isGroup_check()) {
                return false;
            }
        }
        return true;
    }

    private boolean isChildInGroupChecked(int i, List<goShopping_bean.DataBean> data) {
        for (int j = 0; j < data.get(i).getList().size(); j++) {
            if (data.get(i).getList().get(j).getSelected() == 0) {
                return false;
            }
        }
        return true;
    }

    private void initView() {
        my_expandableListview = (MyExpandableListView) findViewById(R.id.My_expandableListview);
        allcheck = (CheckBox) findViewById(R.id.allcheck);
        tol = (TextView) findViewById(R.id.tol);
        gobuy = (TextView) findViewById(R.id.gobuy);
    }
}

