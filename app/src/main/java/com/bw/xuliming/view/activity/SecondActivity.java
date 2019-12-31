package com.bw.xuliming.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.xuliming.R;
import com.bw.xuliming.base.BaseActivity;
import com.bw.xuliming.base.BasePresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class SecondActivity extends BaseActivity {

    @BindView(R.id.iv8)
    ImageView iv8;
    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.bt2)
    Button bt2;

    @Override
    protected void initView() {
        CodeUtils.init(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        Bitmap bitmap=CodeUtils.createImage("旺旺",350,350,null);
        iv8.setImageBitmap(bitmap);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }


    @Override
    protected int layoutid() {
        return (R.layout.activity_second);
    }

    @OnLongClick(R.id.iv8)
    public void ss(){
        CodeUtils.analyzeByImageView(iv8, new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Toast.makeText(SecondActivity.this, ""+result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnalyzeFailed() {
                Toast.makeText(SecondActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @OnClick({R.id.bt1, R.id.bt2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                EventBus.getDefault().post("微信");
                break;
            case R.id.bt2:
                EventBus.getDefault().post("QQ");
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void weixin(String s){
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
    }

}
