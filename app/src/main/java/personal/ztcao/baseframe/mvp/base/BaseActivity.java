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

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lufficc.stateLayout.StateLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;
import personal.ztcao.baseframe.mvp.R;
import personal.ztcao.baseframe.mvp.base.app.App;

/**
 * 工程名:mvp
 * 文 件 名: BaseActivity
 * 创 建 人: 曹振田
 * 描述:无MVP的activity基类
 * 创建日期: 2017/11/12 0012 19:30
 * 修改时间：
 * 修改备注：
 */

public abstract class BaseActivity extends SupportActivity {

    protected Activity mContext;
    private Unbinder mUnBinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onBackPressedSupport();
            }
        });
    }

    protected void onViewCreated() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        App.getInstance().removeActivity(this);
    }

    protected abstract @LayoutRes  int getLayout();
    protected abstract void initEventAndData();
}
