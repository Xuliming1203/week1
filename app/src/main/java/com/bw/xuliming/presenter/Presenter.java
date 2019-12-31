package com.bw.xuliming.presenter;

import com.bw.xuliming.base.BasePresenter;
import com.bw.xuliming.contracl.IContracl;
import com.bw.xuliming.model.Model;
import com.bw.xuliming.model.entity.JsonEntity;

/**
 * 时间：2019/12/31
 * 作者：徐黎明
 * 类的作用：
 */
public class Presenter extends BasePresenter<Model, IContracl.IView> implements IContracl.IPresenter {
    @Override
    protected Model initModel() {
        return new Model();
    }

    @Override
    public void getJson(String url) {
        model.getJson(url, new IContracl.IModel.ModelCallBack() {
            @Override
            public void success(JsonEntity jsonEntity) {
                getView().success(jsonEntity);
            }

            @Override
            public void fshibai(Throwable error) {
            getView().fshibai(error);
            }
        });
    }
}
