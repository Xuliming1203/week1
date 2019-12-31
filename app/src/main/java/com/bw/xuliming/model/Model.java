package com.bw.xuliming.model;

import com.bw.xuliming.contracl.IContracl;
import com.bw.xuliming.model.entity.JsonEntity;
import com.bw.xuliming.utils.OkHttpUtil;
import com.google.gson.Gson;

/**
 * 时间：2019/12/31
 * 作者：徐黎明
 * 类的作用：
 */
public class Model implements IContracl.IModel {

    @Override
    public void getJson(String url, ModelCallBack modelCallBack) {
        OkHttpUtil.getInstance().doget(url, new OkHttpUtil.OkHttpCallBack() {
            @Override
            public void success(String repose) {
                JsonEntity jsonEntity = new Gson().fromJson(repose, JsonEntity.class);
                if (modelCallBack != null) {
                    modelCallBack.success(jsonEntity);
                }

            }

            @Override
            public void fshibai(Throwable throwable) {
                if (modelCallBack != null) {
                    modelCallBack.fshibai(throwable);
                }
            }
        });
    }
}
