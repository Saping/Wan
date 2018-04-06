package com.example.y_xl.wan.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.y_xl.wan.R;
import com.example.y_xl.wan.model.CountPriceBean;
import com.example.y_xl.wan.model.goShopping_bean;
import com.example.y_xl.wan.presenter.goShopping_P;
import com.example.y_xl.wan.util.BaseObserver;
import com.example.y_xl.wan.util.My_api;
import com.example.y_xl.wan.util.RetrofitAndOkHttpAndRxAndroid;
import com.example.y_xl.wan.view.activity.MainActivity;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Y_xl on 2018/4/5.
 * 二级列表适配器
 */

public class Myadapter extends BaseExpandableListAdapter {

    private final Context context;
    private final List<goShopping_bean.DataBean> list;
    private final goShopping_P goShopping_p;
    private int childIndex;
    private int allIndex;
    private CountPriceBean countPriceBean;
    private String priceString;

    public Myadapter(Context context, List<goShopping_bean.DataBean> list, goShopping_P goShopping_p) {
             this.context = context;
             this.list = list;

             this.goShopping_p = goShopping_p;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    //一级列表
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        final groupVH groupVH;
        if(convertView==null){
            groupVH = new groupVH();
            convertView = View.inflate(context, R.layout.group_layout,null);
            groupVH.group_check = convertView.findViewById(R.id.group_check);
            groupVH.group_name = convertView.findViewById(R.id.group_name);

            convertView.setTag(groupVH);
        }else{
            groupVH = (groupVH) convertView.getTag();
        }

        final goShopping_bean.DataBean dataBean = list.get(groupPosition);

        //赋值
        groupVH.group_check.setChecked(list.get(groupPosition).isGroup_check());
        groupVH.group_name.setText(list.get(groupPosition).getSellerName());
        //点击一届列表单选框
        groupVH.group_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                childIndex = 0;
                //更新      参数当前一级列表的选择状态    一级列表对象
                updateChildCheckedInGroup(groupVH.group_check.isChecked(),dataBean);
            }
        });

        return convertView;
    }

    private void updateChildCheckedInGroup(final boolean checked, final goShopping_bean.DataBean dataBean) {
        goShopping_bean.DataBean.ListBean listBean = dataBean.getList().get(childIndex);

        HashMap<String, String> map = new HashMap<>();
        map.put("uid","2775");
        map.put("sellerid",listBean.getSellerid()+"");
        map.put("pid",listBean.getPid()+"");
        map.put("num",listBean.getNum()+"");
        map.put("selected", String.valueOf(checked?1:0));


        RetrofitAndOkHttpAndRxAndroid.get(My_api.UpdataShoppingCart,map).subscribe(new BaseObserver() {
            @Override
            public void onSuccess(String result) {
                childIndex++;
                if(childIndex<dataBean.getList().size()){
                    updateChildCheckedInGroup(checked,dataBean);
                }else{
                    goShopping_p.getdata(My_api.goShopping_api);
                }
            }
        });

    }

    //二级列表
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        childVH childVH;
        if(convertView==null){
            convertView = View.inflate(context,R.layout.child_layout,null);
            childVH = new childVH();
            childVH.child_check = convertView.findViewById(R.id.child_check);
            childVH.image = convertView.findViewById(R.id.image);
            childVH.content = convertView .findViewById(R.id.content);
            childVH.beginPrice = convertView.findViewById(R.id.beginPrice);
            childVH.num = convertView.findViewById(R.id.num);
            childVH.jia = convertView.findViewById(R.id.jia);
            childVH.jian=convertView.findViewById(R.id.jian);
            childVH.delete=convertView.findViewById(R.id.delete);

            convertView.setTag(childVH);
        }else{
            childVH = (childVH) convertView.getTag();
        }
        //赋值
        final goShopping_bean.DataBean.ListBean listBean = list.get(groupPosition).getList().get(childPosition);
        childVH.child_check.setChecked(listBean.getSelected()==0?false:true);
        if(listBean.getImages()!=null) {
            String[] split = listBean.getImages().split("\\|");
            Glide.with(context).load(split[0]).into(childVH.image);
            childVH.content.setText(listBean.getTitle()+"");
            childVH.beginPrice.setText("￥:"+listBean.getBargainPrice());
            childVH.num.setText(listBean.getNum()+"");

             //加号
             childVH.jia.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     //更新接口
                     HashMap<String, String> map = new HashMap<>();
                     map.put("uid","2775");
                     map.put("sellerid",listBean.getSellerid()+"");
                     map.put("pid",listBean.getPid()+"");
                     map.put("num",listBean.getNum()+1+"");
                     map.put("selected", String.valueOf(listBean.getSelected()));

                     RetrofitAndOkHttpAndRxAndroid.get(My_api.UpdataShoppingCart,map).subscribe(new BaseObserver() {
                         @Override
                         public void onSuccess(String result) {

                                 goShopping_p.getdata(My_api.goShopping_api);

                         }
                     });
                 }
             });
             //减号
            childVH.jian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num = listBean.getNum();
                    if (num == 1){
                        return;
                    }

                    Map<String, String> params = new HashMap<>();
                    params.put("uid","2775");
                    params.put("sellerid", String.valueOf(listBean.getSellerid()));
                    params.put("pid", String.valueOf(listBean.getPid()));
                    params.put("selected", String.valueOf(listBean.getSelected()));//listBean.getSelected()...0--->1,,,1--->0
                    params.put("num", String.valueOf(num - 1));
                    RetrofitAndOkHttpAndRxAndroid.post(My_api.UpdataShoppingCart,params).subscribe(new BaseObserver() {
                        @Override
                        public void onSuccess(String result) {
                            goShopping_p.getdata(My_api.goShopping_api);
                        }
                    });
                }
            });
            //删除
            childVH.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("uid","2775");
                    map.put("pid",listBean.getPid()+"");
                    RetrofitAndOkHttpAndRxAndroid.post(My_api.DeleteShoppingCart,map).subscribe(new BaseObserver() {
                        @Override
                        public void onSuccess(String result) {
                            goShopping_p.getdata(My_api.goShopping_api);
                        }
                    });
                }
            });
            //点击二级列表选择框
            childVH.child_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //刷新接口,重新展示
                    HashMap<String, String> map = new HashMap<>();
                    map.put("uid","2775");
                    map.put("sellerid",listBean.getSellerid()+"");
                    map.put("pid",listBean.getPid()+"");
                    map.put("num",listBean.getNum()+"");
                    map.put("selected", String.valueOf(listBean.getSelected()==0?1:0));

                    RetrofitAndOkHttpAndRxAndroid.post(My_api.UpdataShoppingCart,map).subscribe(new BaseObserver() {
                        @Override
                        public void onSuccess(String result) {
                            goShopping_p.getdata(My_api.goShopping_api);
                        }
                    });
                }
            });
        }


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //当点击全选按钮的时候
    public void setAllChildChecked(boolean checked) {
        //通过遍历所有孩子添加到一个集合
        ArrayList<goShopping_bean.DataBean.ListBean> allList = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            for(int j=0;j<list.get(i).getList().size();j++){
                allList.add(list.get(i).getList().get(j));
            }
        }
        //更新每一个子孩子的状态...递归
        allIndex = 0;
        //修改所有二级列表的状态,  参数  当前全选的状态,所有的二级列表
        updateAllChild(checked,allList);
    }

    private void updateAllChild(final boolean checked, final ArrayList<goShopping_bean.DataBean.ListBean> allList) {
        //集合中当前二级列表对象
        final goShopping_bean.DataBean.ListBean listBean = allList.get(allIndex);
        //请求更新的接口
        //刷新接口,重新展示
        HashMap<String, String> map = new HashMap<>();
        map.put("uid","2775");
        map.put("sellerid",listBean.getSellerid()+"");
        map.put("pid",listBean.getPid()+"");
        map.put("num",listBean.getNum()+"");
        map.put("selected", String.valueOf(checked?1:0));

        RetrofitAndOkHttpAndRxAndroid.post(My_api.UpdataShoppingCart,map).subscribe(new BaseObserver() {
            @Override
            public void onSuccess(String result) {
                allIndex++;
                if(allIndex<allList.size()){
                    //继续更新
                    updateAllChild(checked,allList);
                }else{
                    //展示
                    goShopping_p.getdata(My_api.goShopping_api);
                }
            }
        });

    }

    //计算价格和数目的,算好后封装传回Activity进行展示
    public void sendPriceAndCount() {
        double price = 0;
        int count = 0;

        //循环
        for(int i=0;i<list.size();i++){
            List<goShopping_bean.DataBean.ListBean> list = this.list.get(i).getList();
            for(int j = 0; j< list.size(); j++){
                goShopping_bean.DataBean.ListBean listBean = list.get(j);
                //当选中状态下进行计算
                if(listBean.getSelected()==1){
                    price+=listBean.getBargainPrice()*listBean.getNum();
                    count+=listBean.getNum();
                }
            }
            //让价钱更精准一点
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String priceString = decimalFormat.format(price);
            //封装成对象,发挥主页面

            countPriceBean = new CountPriceBean(priceString, count);
        }
        priceString = countPriceBean.getPriceString();
        count = countPriceBean.getCount();

        MainActivity.tol.setText("合计:¥" + priceString);
        MainActivity.gobuy.setText("去结算(" + count + ")");
    }

    //两个内部类
    class groupVH{
        CheckBox group_check;
        TextView group_name;
    }
    class childVH{
        CheckBox child_check;
        ImageView image;
        TextView content;
        TextView beginPrice;
        TextView num;
        TextView jia;
        TextView jian;
        TextView delete;

    }
}
