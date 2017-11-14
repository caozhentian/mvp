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

import android.support.annotation.LayoutRes;

import personal.ztcao.baseframe.mvp.R;

/**
 * 工程名:mvp
 * 文 件 名: BaseView
 * 创 建 人: 曹振田
 * 描述:实现状态功能的MVP的基类Activity， 同时只包含一个Fragment
 * 创建日期: 2017/11/12 0012 19:33
 * 修改时间：
 * 修改备注：
 */
public class BaseStateMvpSingleFragmentActivity<T extends BasePresenter>  extends BaseStateMvpActivity<T>{

    @Override
    protected @LayoutRes  int getLayout() {
        return R.layout.act_base_state_mvp_single_fragment;
    }

    @Override
    protected void initInject() {

    }
}
