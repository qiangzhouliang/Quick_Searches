package com.example.qzl.quick_searches;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Qzl on 2016-07-25.
 */
public class MyAdapter extends BaseAdapter {
    private ArrayList<Friend> list;
    private Context context;
    public MyAdapter(Context context,ArrayList<Friend> list) {
        super();
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = View.inflate(context,R.layout.adapter_friend,null);
        }
        ViewHolder holder = ViewHolder.getHolder(view);
        //设置数据
        Friend friend = list.get(i);
        holder.tv_mainactivity_name.setText(friend.getName());
        holder.tv_mainactivity_first_worid.setText(PinYinUtil.getPinYin(friend.getName()));
        return view;
    }
    static class ViewHolder{
        TextView tv_mainactivity_first_worid,tv_mainactivity_name;
        public ViewHolder(View view) {
            tv_mainactivity_first_worid = (TextView) view.findViewById(R.id.tv_mainactivity_first_worid);
            tv_mainactivity_name = (TextView) view.findViewById(R.id.tv_mainactivity_name);
        }
        public static ViewHolder getHolder(View view){
            ViewHolder holder = (ViewHolder) view.getTag();
            if (holder == null){
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            return holder;
        }
    }
}
