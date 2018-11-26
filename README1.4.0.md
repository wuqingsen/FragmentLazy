# FragmentLazy
ViewPager不设置预加载和Fragment只加载一次。

先上效果图：
![1](https://github.com/wuqingsen/FragmentLazy/blob/master/%E6%95%88%E6%9E%9C%E5%9B%BE/1.png)  ![2](https://github.com/wuqingsen/FragmentLazy/blob/master/%E6%95%88%E6%9E%9C%E5%9B%BE/2.png)  

**1、定义参数**
主要参数为下面三个参数，要充分理解意思才能更好的运用：

```
    //是否可见
    public boolean isVisible = false;
    //是否初始化完成
    public boolean isInit = false;
    //是否已经加载过
　　public boolean isLoadOver = false;
```
**2、重写setUserVisibleHint()方法**

```

    //界面可见时再加载数据(该方法在onCreate()方法之前执行。)
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isVisible = isVisibleToUser;
        setParam();
    }
```
**3、onCreateView()判断view是否为空**

```       
 if (view == null) {
            view = View.inflate(getActivity(), R.layout.fragment_main, null);
            isInit = true;
            setParam();
        }
        return view;
```
**4、setParam()设置参数**

```      
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
```
**5、设置完成，写入自己的逻辑即可**

```    
    /**
     * 在这里写请求网络等逻辑代码
     */
    private void setDates() {
        Log.e("=====", "加载" + text + "数据");
        tv_text = view.findViewById(R.id.tv_text);
        tv_text.setText(text);
　　}
```
最主要的始要明白上面三个参数的使用，要知道只有达到下面的条件时才可以进行加载数据：
此 Fragment 初始化完成、此 Fragment 没有加载过并且此 Fragment 处于可见状态下去加载数据。　　
