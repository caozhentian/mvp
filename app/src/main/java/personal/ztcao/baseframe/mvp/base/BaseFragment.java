/*
******************************* Copyright (c)*********************************\
**
**                 (c) Copyright 2017
**                          All Rights Reserved
**
**                           By(公司)
**
**-----------------------------------版本信息------------------------------------
** 版    本: V1.0
**
**------------------------------------------------------------------------------
********************************End of Head************************************\
*/
package personal.ztcao.baseframe.mvp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 工程名:mvp
 * 文 件 名: BaseFragment
 * 创 建 人: 曹振田
 * 描述:非MVP 支持的Fragment
 * 创建日期: 2017/11/12 0012 19:35
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseFragment extends Fragment {

    /**
     * attach 的activity
     */
    protected FragmentActivity mActivity;
    protected Context mContext;

    protected Unbinder mUnbinder ;
    /**
     *根 View
     */
    protected View mRootView ;
    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完onCreatView,View的初始化方法后方法后即为true
     */
    protected boolean mIsViewPrepare;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        mActivity = getActivity();
        mContext  = context ;
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        mRootView = inflater.inflate(getLayout(), container, false);
        mUnbinder = ButterKnife.bind(this ,mRootView) ;
        initData(getArguments());
        initView();
        mIsViewPrepare = true;
        onLazyLoad();
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView()  ;
        mUnbinder.unbind()    ;
    }

    /**
     * 初始化数据 主要包括拆包从activity传递过来的参数，适配器初始化，集合初始化等，不可进行view的操作
     * @param arguments 接收到的从其他地方传递过来的参数
     */
    protected void initData(Bundle arguments)
    {

    }

    /**
     * 初始化View
     */
    protected void initView()
    {

    }

    /**
     * 设置根布局资源id
     */
    protected abstract @LayoutRes  int getLayout();


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);

        this.mIsVisible = isVisibleToUser;

        if (isVisibleToUser)
        {
            onVisibleToUser();
        }
    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisibleToUser()
    {
        if (mIsViewPrepare && mIsVisible)
        {
            onLazyLoad();
        }
    }

    /**
     * 懒加载，仅当用户可见且view初始化结束后才会执行
     * 何为懒加载，就是view没有与用户交互的话并不会加载，但是加载顺序很快。
     * 该方法主要在viewpager嵌套fragment的场景。
     * viewpager可以提前加载左右指定数目（数目可以通过setOffscreenPageLimit(int limit)设置）的fragment，
     * 如果使用懒加载，就只会做些view的创建等操作，避免提前执行其他页面的网络请求。
     * 代码中，setUserVisibleHint(boolean isVisibleToUser)表示是否与用户可交互，在onCreatview方法前执行，当isVisibleToUser为true时表示对用户可见，
     * 在这里我们执行自定义的onVisibleToUser()方法
     * 在onVisibleToUser()中，我们进行判断，当mIsPrepare为true且与用户可交互时执行我们的onLazyLoad()方法进行懒加载。
     */
    protected void onLazyLoad()
    {

    }

    //跳转界面等方法封装
    /**
     * 打开一个Activity 默认 不关闭当前activity
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle ex) {
        Intent intent = new Intent(mActivity, clz);
        if (ex != null) intent.putExtras(ex);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            mActivity.finish();
        }
    }

}
