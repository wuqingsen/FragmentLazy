package com.example.qd.fragmentdemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExamplePagerAdapter mExamplePagerAdapter;
    private List<String> mDataList;
    private List<Fragment> fragments;
    private ViewPager viewPager;
    private MagicIndicator magic_indicator;
    private CommonNavigator commonNavigator7;
    private SimplePagerTitleView simplePagerTitleView;
    private LinePagerIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        magic_indicator = findViewById(R.id.magic_indicator);

        //添加数据
        setDatas();
        mExamplePagerAdapter = new ExamplePagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(mExamplePagerAdapter);
        //设置头部标题的样式
        setTitle();
    }

    //添加数据
    private void setDatas() {
        mDataList = new ArrayList<>();
        fragments = new ArrayList<>();

        mDataList.add("第一页");
        mDataList.add("第二页");
        mDataList.add("第三页");
        mDataList.add("第四页");
        mDataList.add("第五页");

        fragments.add(new MainFragment("第一页"));
        fragments.add(new MainFragment("第二页"));
        fragments.add(new MainFragment("第三页"));
        fragments.add(new MainFragment("第四页"));
        fragments.add(new MainFragment("第五页"));
    }

    //设置头部标题的样式
    public void setTitle() {
        commonNavigator7 = new CommonNavigator(MainActivity.this);
        commonNavigator7.setAdjustMode(true);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(final Context context, final int index) {
                simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                //字体未选中时颜色
                simplePagerTitleView.setNormalColor(Color.parseColor("#FF999999"));
                //字体选中时颜色
                simplePagerTitleView.setSelectedColor(Color.parseColor("#000000"));
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {

                indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                //设置导航条的高度
                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                indicator.setLineWidth(UIUtil.dip2px(context, 25));
                //设置导航条的圆角
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                //导航条左右滑动的速度，越大越慢
                indicator.setEndInterpolator(new DecelerateInterpolator(3.0f));
                //设置导航条的颜色
                indicator.setColors(Color.parseColor("#000000"));
                return indicator;
            }
        });
        magic_indicator.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(magic_indicator, viewPager);
    }
}
