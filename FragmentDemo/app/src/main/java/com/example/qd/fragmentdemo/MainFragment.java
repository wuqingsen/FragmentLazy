package com.example.qd.fragmentdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * author: wu
 * date: on 2018/11/26.
 * describe:Fragment懒加载和ViewPager取消预加载
 */

@SuppressLint("ValidFragment")
public class MainFragment extends Fragment {
    private View view;
    private TextView tv_text;
    private String text;
    //是否可见
    public boolean isVisible = false;
    //是否初始化完成
    public boolean isInit = false;
    //是否已经加载过
    public boolean isLoadOver = false;

    public MainFragment() {
    }

    public MainFragment(String text) {
        this.text = text;
    }

    //界面可见时再加载数据(该方法在onCreate()方法之前执行。)
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isVisible = isVisibleToUser;
        setParam();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = View.inflate(getActivity(), R.layout.fragment_main, null);
            isInit = true;
            setParam();
        }
        return view;
    }

    /**
     * 初始化一些参数，完成懒加载和数据只加载一次的效果
     * isInit = true：此Fragment初始化完成
     * isLoadOver = false：此Fragment没有加载过
     * isVisible = true：此Fragment可见
     */
    private void setParam() {
        if (isInit && !isLoadOver && isVisible) {
            isLoadOver = true;
            setDates();
        }
    }

    /**
     * 在这里写请求网络等逻辑代码
     */
    private void setDates() {
        Log.e("=====", "加载" + text + "数据");
        tv_text = view.findViewById(R.id.tv_text);
        tv_text.setText(text);
    }
}
