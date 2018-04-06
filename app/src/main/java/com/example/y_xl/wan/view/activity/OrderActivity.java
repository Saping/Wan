package com.example.y_xl.wan.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.y_xl.wan.R;
import com.example.y_xl.wan.view.fragment.Daizhifu_fragment;
import com.example.y_xl.wan.view.fragment.Quanbu_fragment;
import com.example.y_xl.wan.view.fragment.Yiquxiao_fragment;
import com.example.y_xl.wan.view.fragment.Yizhifu_fragment;


public class OrderActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private ImageView image;
    private TabLayout tablayout;
    private ViewPager view_pager;
    private Quanbu_fragment quanbu_fragment;
    private Daizhifu_fragment daizhifu_fragment;
    private Yizhifu_fragment yizhifu_fragment;
    private Yiquxiao_fragment yiquxiao_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();

        //tablayout中的内容
        final String[] titles = new String[]{"全部", "待支付", "已支付", "已取消"};

        view_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

            @Override
            public Fragment getItem(int position) {
                if (titles[position] == "全部") {
                    quanbu_fragment = new Quanbu_fragment();
                    return quanbu_fragment;
                } else if (titles[position] == "待支付") {
                    //得到子条目 因为子条目是Fragment,所以要new一个Fragment
                    daizhifu_fragment = new Daizhifu_fragment();
                    return daizhifu_fragment;
                } else if (titles[position] == "已支付") {
                    yizhifu_fragment = new Yizhifu_fragment();
                    return yizhifu_fragment;
                } else if (titles[position] == "已取消") {
                    yiquxiao_fragment = new Yiquxiao_fragment();
                    return yiquxiao_fragment;
                }
                return null;
            }

            @Override
            public int getCount() {
                return titles.length;
            }
        });
        //tablayout关联viewPager
        tablayout.setupWithViewPager(view_pager);
        //ViewPager限定预加载的页面个数
        view_pager.setOffscreenPageLimit(titles.length);
    }


    public void pop(View view) {
        PopupMenu popupMenu = new PopupMenu(this, image);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(this);
    }

    private void initView() {
        image = (ImageView) findViewById(R.id.image);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.quanbu) {
            view_pager.setCurrentItem(0);
            Toast.makeText(OrderActivity.this, "点击了全部!", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.daizhifu) {
            view_pager.setCurrentItem(1);
            Toast.makeText(OrderActivity.this, "点击了待支付!", Toast.LENGTH_SHORT).show();

        } else if (itemId == R.id.yizhifu) {
            Toast.makeText(OrderActivity.this, "点击了已支付!", Toast.LENGTH_SHORT).show();
            view_pager.setCurrentItem(2);
        } else if (itemId == R.id.yiquxiao) {
            view_pager.setCurrentItem(3);
            Toast.makeText(OrderActivity.this, "点击了已取消!", Toast.LENGTH_SHORT).show();
        }


        return true;
    }
}
