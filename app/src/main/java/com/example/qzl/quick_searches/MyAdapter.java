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
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null){
            view = View.inflate(context,R.layout.adapter_friend,null);
        }
        ViewHolder holder = ViewHolder.getHolder(view);
        //设置数据
        Friend friend = list.get(position);
        holder.tv_mainactivity_name.setText(friend.getName());
        String currentWord = friend.getPinyin().charAt(0)+"";
        if (position > 0){
            //获取上一个item的首字母
            String lastWord = list.get(position-1).getPinyin().charAt(0)+"";
            //拿当前的首字母和上一个字母比较
            if (currentWord.equals(lastWord)){
                //说明首字母相同，需要隐藏当前item的first_word
                holder.tv_mainactivity_first_worid.setVisibility(View.GONE);
            }else {
                //不一样，需要显示当前的首字母
                //由于布局是复用的，所以在需要显示的时候，需再次将tv_mainactivity_first_worid设置为可见
                holder.tv_mainactivity_first_worid.setVisibility(View.VISIBLE);
                holder.tv_mainactivity_first_worid.setText(currentWord);
            }
        }else {
            holder.tv_mainactivity_first_worid.setVisibility(View.VISIBLE);
            holder.tv_mainactivity_first_worid.setText(currentWord);
        }
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
