package com.zucc.zxf31501315.currency;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    //声明ViewPager
    private ViewPager mViewPager;
    //适配器
    private FragmentPagerAdapter mAdapter;
    //装载Fragment的集合
    private List<Fragment> mFragments;
    private Fragment fragment;
    private Fragment c_fragment;
    private Fragment t_fragment;
    //Tab对应的布局
    private LinearLayout mTab_currency;
    private LinearLayout mTab_trend;
    //Tab对应的Button
    private ImageButton imgbtn_currency;
    private ImageButton imgbtn_trend;
    private TextView tvcurrency,tvtrend;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();//初始化控件
        initEvents();//初始化事件
        initDatas();//初始化数据
//        initLeftMenu();//菜单
    }
    private void initDatas() {
        mFragments = new ArrayList<>();
        //将Fragment加入集合中
        mFragments.add(new CurrencyFragment());
        mFragments.add(new TrendFragment());

        //初始化适配器
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                //从集合中获取对应位置的Fragment
                c_fragment = mFragments.get(0);
                t_fragment = mFragments.get(1);
                fragment = mFragments.get(position);
                return fragment;
            }

            @Override
            public int getCount() {
                //获取集合中Fragment的总数
                return mFragments.size();
            }

        };
        //设置ViewPager的适配器
        mViewPager.setAdapter(mAdapter);
        //设置ViewPager的切换监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            //页面滚动事件
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //页面选中事件
            @Override
            public void onPageSelected(int position) {
                //设置position对应的集合中的Fragment
                mViewPager.setCurrentItem(position);
                resetImgs();
                selectTab(position);
            }

            @Override
            //页面滚动状态改变事件
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void initEvents() {
        //设置Tab的点击事件
        mTab_currency.setOnClickListener(this);
        mTab_trend.setOnClickListener(this);
    }

    //初始化控件
    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mTab_currency = (LinearLayout) findViewById(R.id.id_tab_currency);
        mTab_trend = (LinearLayout) findViewById(R.id.id_tab_trend);

        imgbtn_currency = (ImageButton) findViewById(R.id.id_tab_currency_imgbtn);
        imgbtn_trend = (ImageButton) findViewById(R.id.id_tab_trend_imgbtn);

        tvcurrency = (TextView) findViewById(R.id.id_tab_currency_tv);
        tvtrend = (TextView) findViewById(R.id.id_tab_trend_tv);
    }

    @Override
    public void onClick(View v) {
        //先将ImageButton置为灰色
        resetImgs();

        //根据点击的Tab切换不同的页面及设置对应的ImageButton为绿色
        switch (v.getId()) {
            case R.id.id_tab_currency:
                selectTab(0);
                break;
            case R.id.id_tab_trend:
                selectTab(1);
                break;
        }
    }

    private void selectTab(int i) {
        //根据点击的Tab设置对应的ImageButton为绿色
        switch (i) {
            case 0:
                imgbtn_currency.setImageResource(R.drawable.tab_currency_pressed);
                tvcurrency.setTextColor(Color.parseColor("#1296db"));
                break;
            case 1:
                imgbtn_trend.setImageResource(R.drawable.tab_trend_pressed);
                tvtrend.setTextColor(Color.parseColor("#1296db"));
                break;
        }
        //设置当前点击的Tab所对应的页面
        mViewPager.setCurrentItem(i);
    }

    //将ImageButton设置为灰色
    private void resetImgs() {
        imgbtn_currency.setImageResource(R.drawable.tab_currency);
        imgbtn_trend.setImageResource(R.drawable.tab_trend);
        tvcurrency.setTextColor(Color.parseColor("#ffffff"));
        tvtrend.setTextColor(Color.parseColor("#ffffff"));
    }

    //按下返回键退向后台运行
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void initLeftMenu()
    {

    }
}
