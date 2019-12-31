package com.bw.xuliming.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 时间：2019/12/31
 * 作者：徐黎明
 * 类的作用：
 */
public class OkHttpUtil {
    Handler handler=new Handler();
    private static OkHttpUtil instance;
    private final OkHttpClient okHttpClient;

    public OkHttpUtil() {
        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3,TimeUnit.SECONDS)
                .connectTimeout(3,TimeUnit.SECONDS)
                //日志拦截器
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    public static OkHttpUtil getInstance() {
        if (instance == null) {
            synchronized (OkHttpUtil.class){
                if (instance == null) {
                    instance=new OkHttpUtil();
                }
            }
        }
        return instance;
    }
    //网络判断
    public boolean HasNet(Context context){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null&& activeNetworkInfo.isAvailable()) {
            return true;
        }else {
            return false;
        }

    }
    public void doget(String url,OkHttpCallBack okHttpCallBack){
        Request request=new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (okHttpCallBack != null) {
                            okHttpCallBack.fshibai(e);
                        }
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (okHttpCallBack != null) {
                            try {
                                okHttpCallBack.success(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }
    public interface OkHttpCallBack{
        void success(String repose);
        void fshibai(Throwable throwable);
    }
}
