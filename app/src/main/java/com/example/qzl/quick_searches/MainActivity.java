package com.example.qzl.quick_searches;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    private QuickIndexBar quickindexbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quickindexbar = (QuickIndexBar) findViewById(R.id.quickindexbar);
        quickindexbar.setOnTouchLetterListener(new QuickIndexBar.OnTouchLetterListener() {
            @Override
            public void onTouchLetter(String letter) {
                Log.d("tag","letter = "+letter);
            }
        });
    }
}
