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

/**
 * 工程名:mvp
 * 文 件 名: BasePresenter
 * 创 建 人: 曹振田
 * 描述:MVP模式中Presenter基类
 * 创建日期: 2017/11/12 0012 19:28
 * 修改时间：
 * 修改备注：
 */

public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
