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
 * 文 件 名: BaseView
 * 创 建 人: 曹振田
 * 描述:MVP模式中View的基类
 * 创建日期: 2017/11/12 0012 19:23
 * 修改时间：
 * 修改备注：
 */
public interface BaseView {

    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    //=======  State对应的不同的View视图  =======
    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
