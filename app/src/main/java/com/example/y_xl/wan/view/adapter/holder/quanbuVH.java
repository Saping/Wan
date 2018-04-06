package com.example.y_xl.wan.view.adapter.holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.y_xl.wan.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by Y_xl on 2018/4/5.
 */

public class quanbuVH extends XRecyclerView.ViewHolder {
    public TextView state;
    public TextView price;
    public TextView date;
    public Button btn;

    public quanbuVH(View itemView) {
        super(itemView);
        //找到控件
        state = itemView.findViewById(R.id.state);
        price = itemView.findViewById(R.id.price);
        date = itemView.findViewById(R.id.date);
        btn = itemView.findViewById(R.id.btn);
    }
}
