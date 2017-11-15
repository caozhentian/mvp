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
package personal.ztcao.baseframe.net.base.interceptor;

import android.text.TextUtils;
import android.util.ArrayMap;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 工程名:mvp
 * 文 件 名: BaseActivity
 * 创 建 人: 曹振田
 * 描述:HTTP 公共头部拦截器
 * 创建日期: 2017/11/15 0012 21:30
 * 修改时间：
 * 修改备注：
 */
public class HeaderInterceptor implements Interceptor {
    private ArrayMap<String, String> headers;

    public HeaderInterceptor(ArrayMap<String, String> headers) {
        this.headers = headers;
    }

    @Override public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        //如果公共请求头不为空,则构建新的请求
        if (headers != null) {
            for (String key : headers.keySet()) {
                requestBuilder.addHeader(key, headers.get(key));
            }
        }
        Request request = requestBuilder.build();
        Response.Builder responseBuilder = chain.proceed(request).newBuilder();
        if (!TextUtils.isEmpty(request.cacheControl().toString())) {
            responseBuilder
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + request.cacheControl().maxAgeSeconds());
        }
        return responseBuilder.build();
    }
}
