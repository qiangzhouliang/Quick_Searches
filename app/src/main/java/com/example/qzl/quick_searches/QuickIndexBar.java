package com.example.qzl.quick_searches;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Qzl on 2016-07-25.
 */
public class QuickIndexBar extends View {
    private String[] indexArr = { "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z" };
    private Paint paint;
    private int width;
    private float cellHeight;
    private int lastIndex = -1;//记录上次的触摸字母的索引
    private OnTouchLetterListener listener;//触摸字母监听

    public QuickIndexBar(Context context) {
        super(context);
        init();
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);//设置抗锯齿
        paint.setColor(Color.WHITE);
        paint.setTextSize(18);
        paint.setTextAlign(Paint.Align.CENTER);//设置文本的起点是底边的中心
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        //得到一个格子的高度
        cellHeight = getMeasuredHeight()*1f/26;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String text = "黑";
        for (int i = 0; i < indexArr.length; i++) {
            float x = width/2;
            float y = cellHeight/2 + getTextHeight(indexArr[i])/2 + i * cellHeight;
            //如果被选中的时候改变颜色
            paint.setColor(lastIndex == i?Color.BLACK:Color.WHITE);
            //文字绘制起点默认在文字的左下脚
            canvas.drawText(indexArr[i],x,y,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://按下
            case MotionEvent.ACTION_MOVE://移动
                float y = event.getY();
                int index = (int) (y/cellHeight);//得到对应字母的索引
                if (lastIndex != index) {
                    //说明当前触摸字母和上一个不是同一个字母
//                    Log.d("tag", "index = " + indexArr[index]);
                    //对index做安全性的检查
                    if (index >= 0 && index < indexArr.length){
                        if (listener != null){
                            listener.onTouchLetter(indexArr[index]);
                        }
                    }
                }
                lastIndex = index;
                break;
            case MotionEvent.ACTION_UP://抬起
                //重置lastIndex
                lastIndex = -1;
                break;
        }
        //引起重绘
        invalidate();
        return true;
    }

    /**
     * 获取文本高度
     * @param text
     */
    private int getTextHeight(String text) {
        Rect bounds = new Rect();
        paint.getTextBounds(text,0,1,bounds);
        return bounds.height();
    }

    public void setOnTouchLetterListener(OnTouchLetterListener listener){
        this.listener = listener;
    }
    /**
     * 触摸字母的一个监听器
     */
    public interface OnTouchLetterListener{
        void onTouchLetter(String letter);
    }
}
