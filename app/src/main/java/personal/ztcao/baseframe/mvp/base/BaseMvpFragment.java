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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * 工程名:mvp
 * 文 件 名: BaseMvpActivity
 * 创 建 人: 曹振田
 * 描述:MVP Fragment基类
 * 创建日期: 2017/11/12 0012 19:40
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    //@Inject
    protected T mPresenter;

//    protected FragmentComponent getFragmentComponent(){
//        return DaggerFragmentComponent.builder()
//                .appComponent(App.getAppComponent())
//                .fragmentModule(getFragmentModule())
//                .build();
//    }
//
//    protected FragmentModule getFragmentModule(){
//        return new FragmentModule(this);
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        //SnackbarUtil.show(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    protected abstract void initInject();
}