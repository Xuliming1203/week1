package com.bw.xuliming.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.xuliming.R;
import com.bw.xuliming.adapter.MyAdapter;
import com.bw.xuliming.base.BaseActivity;
import com.bw.xuliming.contracl.IContracl;
import com.bw.xuliming.model.entity.JsonEntity;
import com.bw.xuliming.presenter.Presenter;
import com.bw.xuliming.utils.OkHttpUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<Presenter> implements IContracl.IView {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void initView() {
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
            presenter.getJson("http://blog.zhaoliang5156.cn/api/news/ranking.json");
    }

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int layoutid() {
        return R.layout.activity_main;
    }

    @Override
    public void success(JsonEntity jsonEntity) {
        if (OkHttpUtil.getInstance().HasNet(this)){
            List<JsonEntity.RankingBean> ranking = jsonEntity.getRanking();
            MyAdapter myAdapter=new MyAdapter(MainActivity.this,ranking);
            myAdapter.setAdapterClick(new MyAdapter.AdapterClick() {
                @Override
                public void click(String s) {
                    Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                }
            });
            rv.setAdapter(myAdapter);
        }

    }

    @Override
    public void fshibai(Throwable error) {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv)
    public void onViewClicked() {
        Intent intent=new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
    }
}
