package com.example.y_xl.wan.view.adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.y_xl.wan.R;
import com.example.y_xl.wan.model.bean.Order_bean;
import com.example.y_xl.wan.presenter.Order_P;
import com.example.y_xl.wan.util.BaseObserver;
import com.example.y_xl.wan.util.My_api;
import com.example.y_xl.wan.util.RetrofitAndOkHttpAndRxAndroid;
import com.example.y_xl.wan.view.adapter.holder.quanbuVH;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Y_xl on 2018/4/5.
 */

public class QuanbuAdapter extends XRecyclerView.Adapter<quanbuVH>{

    private final Context context;
    private final List<Order_bean.DataBean> list;
    private final Order_P order_p;

    public QuanbuAdapter(Context context, List<Order_bean.DataBean> list, Order_P order_p) {
        this.context = context;
        this.list = list;
        this.order_p = order_p;
    }


    @Override
    public quanbuVH onCreateViewHolder(ViewGroup parent, int viewType) {

        //找到布局
        View view = View.inflate(context, R.layout.quanbuvh, null);
        quanbuVH quanbuVH = new quanbuVH(view);
        return quanbuVH;

    }

    @Override
    public void onBindViewHolder(quanbuVH holder, final int position) {
        //控件赋值
        final int status = list.get(position).getStatus();
        if (status == 0) {
            holder.state.setText("待支付");
            holder.state.setTextColor(Color.RED);
            holder.btn.setText("取消订单");
        } else if (status == 1) {
            holder.state.setText("已支付");
            holder.btn.setText("查看订单");
            holder.state.setTextColor(Color.BLACK);
        } else if (status == 2) {
            holder.state.setText("已取消");
            holder.btn.setText("查看订单");
            holder.state.setTextColor(Color.BLACK);
        }

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status == 0){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setTitle("提示").setMessage("确定取消订单吗?").setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "点击了否!", Toast.LENGTH_SHORT).show();
                        }
                    }).setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "点击了是!", Toast.LENGTH_SHORT).show();
                            //点击是的时候   取消 访问修改接口
                            HashMap<String, String> map = new HashMap<>();
                            map.put("uid", "2775");
                            map.put("status", "2");
                            map.put("orderId", list.get(position).getOrderid() + "");

                            RetrofitAndOkHttpAndRxAndroid.post(My_api.updateOrder,map).subscribe(new BaseObserver() {
                                @Override
                                public void onSuccess(String result) {
                                    //清空集合,重新获取数据 这个时候就改变了订单的状态,又重新展示了
                                    list.clear();
                                    int page = 1;
                                    order_p.getdata(page);

                                }
                            });

                            //展示
                            builder.show();
                        }

                    });


                }
                if (status == 1 || status == 2) {
                    Toast.makeText(context, "功能正在开发中!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.price.setText("价格:" + list.get(position).getPrice());
        holder.date.setText("创建日期:" + list.get(position).getCreatetime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
