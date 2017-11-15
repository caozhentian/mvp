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

import android.util.ArrayMap;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/**
 * 工程名:mvp
 * 文 件 名: BaseActivity
 * 创 建 人: 曹振田
 * 描述:公共请求参数拦截器
 * 创建日期: 2017/11/15 0012 19:30
 * 修改时间：
 * 修改备注：
 */
public class ParamsInterceptor implements Interceptor {
    private ArrayMap<String, String> params;

    public ParamsInterceptor(ArrayMap<String, String> params) {
        this.params = params;
    }

    @Override public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        //如果公共请求参数不为空,则构建新的请求
        if (params != null) {
            Request.Builder newRequestBuilder = oldRequest.newBuilder();
            //GET请求则使用HttpUrl.Builder来构建
            if ("GET".equalsIgnoreCase(oldRequest.method())) {
                HttpUrl.Builder httpUrlBuilder = oldRequest.url().newBuilder();
                for (String key : params.keySet()) {
                    httpUrlBuilder.addQueryParameter(key, params.get(key));
                }
                newRequestBuilder.url(httpUrlBuilder.build());
            } else {
                //如果原请求是表单请求
                if (oldRequest.body() instanceof FormBody) {
                    FormBody.Builder formBodyBuilder = new FormBody.Builder();
                    for (String key : params.keySet()) {
                        formBodyBuilder.add(key, params.get(key));
                    }
                    FormBody oldFormBody = (FormBody) oldRequest.body();
                    int size = oldFormBody.size();
                    for (int i = 0; i < size; i++) {
                        formBodyBuilder.add(oldFormBody.name(i), oldFormBody.value(i));
                    }
                    newRequestBuilder.post(formBodyBuilder.build());
                }
                // TODO: 2017/3/24 处理其它类型的request.body
            }
            return chain.proceed(newRequestBuilder.build());
        }
        return chain.proceed(oldRequest);
    }
}
