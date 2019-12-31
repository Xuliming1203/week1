package com.bw.xuliming.contracl;

import com.bw.xuliming.base.IBaseModel;
import com.bw.xuliming.base.IBaseView;
import com.bw.xuliming.model.entity.JsonEntity;

/**
 * 时间：2019/12/31
 * 作者：徐黎明
 * 类的作用：
 */
public interface IContracl {
    interface IModel extends IBaseModel{
        void getJson(String url,ModelCallBack modelCallBack);
        interface ModelCallBack{
            void success(JsonEntity jsonEntity);
            void fshibai(Throwable error);
        }
    }
    interface IView extends IBaseView{
        void success(JsonEntity jsonEntity);
        void fshibai(Throwable error);
    }
    interface IPresenter{
        void getJson(String url);
    }
}
