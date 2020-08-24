package com.example.listviewandrecycleview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {//如果不重写ViewHolder，该泛型是RecyclerView.ViewHolder
    private List<String>list;
    private List<Integer>heights;
    private Context context;
    private MyRecycleViewAdapter.MyViewHolder viewHolder;
    private onItemClickListener itemClickListener;

    public MyRecycleViewAdapter(Context context, List<String>list){
        this.context = context;
        this.list = list;
        heights = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            heights.add((int)(200+Math.random()*50));
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //		MyViewHolder holder = new MyViewHolder(View.inflate(viewGroup.getContext(), R.layout.listitem, null));
//		MyViewHolder holder = new MyViewHolder(View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, null));
//		MyViewHolder holder = new MyViewHolder(View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, viewGroup));//最好用LayoutInflater.from(context)来设置填充
        viewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false));//由于RecyclerView/ListView会自动将child添加到它里面去,所有需要将最后一个参数设为false
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //绑定数据
        ViewGroup.LayoutParams params = holder.tv.getLayoutParams();
        params.height = heights.get(position);
		holder.tv.setBackgroundColor(Color.rgb(100, (int)(Math.random()*255), (int)(Math.random()*255)));
        holder.tv.setLayoutParams(params);
        holder.tv.setText(list.get(position));

        if(itemClickListener!=null){
            holder.tv.setOnClickListener(new MyonClickListener(position));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView)view.findViewById(android.R.id.text1);

        }
    }

    public interface onItemClickListener{
        public void onItemClickListener(Context context, int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.itemClickListener = listener;
    }

    public void addData(int position){
        list.add(position,"additem"+position);
        //提示刷新--会影响效率
//		notifyDataSetChanged();
        notifyItemInserted(position);
    }

    public void removeData(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }

    public class MyonClickListener implements View.OnClickListener{
        int position;
        public MyonClickListener(int position){
            this.position = position;
        }


        @Override
        public void onClick(View v) {
            itemClickListener.onItemClickListener(context,position);
        }
    }
}
