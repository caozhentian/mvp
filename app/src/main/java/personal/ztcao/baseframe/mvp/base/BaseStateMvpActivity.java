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

import android.view.View;
import android.view.ViewGroup;

import personal.ztcao.baseframe.mvp.R;

/**
 * 工程名:mvp
 * 文 件 名: BaseStateMvpActivity
 * 创 建 人: 曹振田
 * 描述:实现状态功能的MVP的基类Activity
 * 创建日期: 2017/11/12 0012 19:35
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseStateMvpActivity<T extends BasePresenter> extends BaseMvpActivity<T>{

    private static final int STATE_MAIN = 0x00;
    private static final int STATE_LOADING = 0x01;
    private static final int STATE_ERROR = 0x02;

    private View viewStateError;
    private View viewLoading;
    private ViewGroup viewMain;
    private ViewGroup mParent;

    private int mErrorResource = R.layout.view_state_error;

    private int currentState = STATE_MAIN;
    private boolean isErrorViewAdded = false;

    @Override
    protected void initEventAndData() {
        viewMain = (ViewGroup) findViewById(R.id.view_state_main);
        if (viewMain == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named 'view_main'.");
        }
        if (!(viewMain.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "view_main's ParentView should be a ViewGroup.");
        }
        mParent = (ViewGroup) viewMain.getParent();
        View.inflate(mContext, R.layout.view_state_loading, mParent);
        viewLoading = mParent.findViewById(R.id.view_state_loading);
        viewLoading.setVisibility(View.GONE);
        viewMain.setVisibility(View.VISIBLE);
    }

    @Override
    public void stateError() {
        if (currentState == STATE_ERROR)
            return;
        if (!isErrorViewAdded) {
            isErrorViewAdded = true;
            View.inflate(mContext, mErrorResource, mParent);
            viewStateError = mParent.findViewById(R.id.view_state_error);
            if (viewStateError == null) {
                throw new IllegalStateException(
                        "A View should be named 'view_state_error' in ErrorLayoutResource.");
            }
        }
        hideCurrentView();
        currentState = STATE_ERROR;
        viewStateError.setVisibility(View.VISIBLE);
    }

    @Override
    public void stateLoading() {
        if (currentState == STATE_LOADING)
            return;
        hideCurrentView();
        currentState = STATE_LOADING;
        viewLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void stateMain() {
        if (currentState == STATE_MAIN)
            return;
        hideCurrentView();
        currentState = STATE_MAIN;
        viewMain.setVisibility(View.VISIBLE);
    }

    private void hideCurrentView() {
        switch (currentState) {
            case STATE_MAIN:
                viewMain.setVisibility(View.GONE);
                break;
            case STATE_LOADING:
                viewLoading.setVisibility(View.GONE);
                break;
            case STATE_ERROR:
                if (viewStateError != null) {
                    viewStateError.setVisibility(View.GONE);
                }
                break;
        }
    }

    public void setErrorResource(int errorLayoutResource) {
        this.mErrorResource = errorLayoutResource;
    }
}