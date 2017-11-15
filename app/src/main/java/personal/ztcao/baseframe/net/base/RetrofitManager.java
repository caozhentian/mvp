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
package personal.ztcao.baseframe.net.base;

import android.os.Build;
import android.util.ArrayMap;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import personal.ztcao.baseframe.mvp.BuildConfig;
import personal.ztcao.baseframe.net.base.interceptor.HeaderInterceptor;
import personal.ztcao.baseframe.net.base.interceptor.ParamsInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 工程名:mvp
 * 文 件 名: RetrofitManager
 * 创 建 人: 曹振田
 * 描述:Retrofit管理类
 * 创建日期: 2017/11/15  21:30
 * 修改时间：
 * 修改备注：
 */
public class RetrofitManager {
    public static final int TIMEOUT = 10;
    //服务的根URL地址
    private static  String sBaseUrl = "" ;

    private static ArrayMap<String, String> SRequestHeader ;

    private static ArrayMap<String, String> sRequestParams ;
    /**
     * 配置网络请求的url
     */
    private static String getBaseUrl() {
        return  sBaseUrl;
    }

    public static void setBaseUrl(String baseUrl){
         sBaseUrl = baseUrl;
    }

    /**
     * 配置网络请求缓存
     */
//    private static Cache getCache() {
//        return new Cache(Utils.getContext().getCacheDir(), 1024 * 1024 * 50);
//    }

    /**
     * 配置网络请求头
     */
    private static ArrayMap<String, String> getRequestHeader() {
        return SRequestHeader;
    }

    public static void setRequestHeader(ArrayMap<String, String> requestHeader) {
        SRequestHeader = requestHeader;
    }
    /**
     * 配置网络请求体
     */
    private static ArrayMap<String, String> getRequestParams() {
        return sRequestParams;
    }

    public static void  setRequestParams(ArrayMap<String, String> requestParams) {
        sRequestParams = requestParams ;
    }
    /**
     * 获取Retrofit
     */
    public static Retrofit getInstance() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        //debug模式添加log信息拦截
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpBuilder.addInterceptor(interceptor);
        }
        okHttpBuilder.addNetworkInterceptor(new HeaderInterceptor(SRequestHeader));
        okHttpBuilder.addNetworkInterceptor(new ParamsInterceptor(sRequestParams));
        //okHttpBuilder.cache(getCache());
        //设置连接超时
        okHttpBuilder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        //设置写超时
        okHttpBuilder.writeTimeout(TIMEOUT, TimeUnit.SECONDS);
        //设置读超时
        okHttpBuilder.readTimeout(TIMEOUT, TimeUnit.SECONDS);


        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(getBaseUrl());
        retrofitBuilder.client(okHttpBuilder.build());
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        return retrofitBuilder.build();
    }
}
