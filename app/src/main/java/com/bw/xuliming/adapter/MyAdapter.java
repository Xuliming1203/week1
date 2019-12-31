package com.bw.xuliming.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.xuliming.R;
import com.bw.xuliming.model.entity.JsonEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时间：2019/12/31
 * 作者：徐黎明
 * 类的作用：
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final Context context;
    private final List<JsonEntity.RankingBean> ranking;


    public MyAdapter(Context context, List<JsonEntity.RankingBean> ranking) {

        this.context = context;
        this.ranking = ranking;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.head, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(ranking.get(position).getAvatar())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .circleCrop()
                .into(holder.iv);
        holder.tv1.setText(ranking.get(position).getRank());
        holder.tv2.setText(ranking.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterClick.click(ranking.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return ranking.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv1)
        TextView tv1;

        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv2)
        TextView tv2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.findViewById(R.id.iv);
            itemView.findViewById(R.id.tv1);
            itemView.findViewById(R.id.tv2);
        }
    }
    private AdapterClick adapterClick;

    public void setAdapterClick(AdapterClick adapterClick) {
        this.adapterClick = adapterClick;
    }

    public interface AdapterClick{
        void click(String s);
    }
}
