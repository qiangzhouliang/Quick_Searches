package com.example.qzl.quick_searches;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity {

    private QuickIndexBar quickindexbar;
    private ListView lv_mainactivity_data;
    private ArrayList<Friend> friends = new ArrayList<Friend>();
    private TextView tv_mainactivity_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quickindexbar = (QuickIndexBar) findViewById(R.id.quickindexbar);
        lv_mainactivity_data = (ListView) findViewById(R.id.lv_mainactivity_data);
        tv_mainactivity_flag = (TextView) findViewById(R.id.tv_mainactivity_flag);
        //初始化数据
        initData();

    }

    private void initData() {
        //1 准备数据
        fillList();
        //2 对数据进行排序
        Collections.sort(friends);
        //3 设置adapter
        lv_mainactivity_data.setAdapter(new MyAdapter(getApplicationContext(),friends));
        quickindexbar.setOnTouchLetterListener(new QuickIndexBar.OnTouchLetterListener() {
            @Override
            public void onTouchLetter(String letter) {
//                Log.d("tag","letter = "+letter);
                //根据当前触摸的首字母，去集合中找那个item的首字母和letter的首字母一样，然后将对应的item放到屏幕顶端
                for (int i = 0; i < friends.size(); i++) {
                    //获取当前首字母
                    String firstWord = friends.get(i).getPinyin().charAt(0)+"";
                    if (letter.equals(firstWord)){
                        //说明找到了，那么应该蒋当前的item放到屏幕顶端
                        lv_mainactivity_data.setSelection(i);
                        break;//只需要找到第一个就成
                    }
                }
                //显示当前触摸的字母
                showCurrentWord(letter);
            }
        });

    }

    private Handler handler = new Handler();
    /**
     * 显示当前触摸字母
     * @param letter
     */
    private void showCurrentWord(String letter) {
        tv_mainactivity_flag.setVisibility(View.VISIBLE);
        tv_mainactivity_flag.setText(letter);
        //先移出之前的任务
        handler.removeCallbacksAndMessages(null);
        //延时隐藏CurrentWord
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_mainactivity_flag.setVisibility(View.GONE);
            }
        },1500);
    }

    private void fillList() {
        // 虚拟数据
        friends.add(new Friend("李伟"));
        friends.add(new Friend("张三"));
        friends.add(new Friend("阿三"));
        friends.add(new Friend("阿四"));
        friends.add(new Friend("段誉"));
        friends.add(new Friend("段正淳"));
        friends.add(new Friend("张三丰"));
        friends.add(new Friend("陈坤"));
        friends.add(new Friend("林俊杰1"));
        friends.add(new Friend("陈坤2"));
        friends.add(new Friend("王二a"));
        friends.add(new Friend("林俊杰a"));
        friends.add(new Friend("张四"));
        friends.add(new Friend("林俊杰"));
        friends.add(new Friend("王二"));
        friends.add(new Friend("王二b"));
        friends.add(new Friend("赵四"));
        friends.add(new Friend("杨坤"));
        friends.add(new Friend("赵子龙"));
        friends.add(new Friend("杨坤1"));
        friends.add(new Friend("李伟1"));
        friends.add(new Friend("宋江"));
        friends.add(new Friend("宋江1"));
        friends.add(new Friend("李伟3"));
    }
}
