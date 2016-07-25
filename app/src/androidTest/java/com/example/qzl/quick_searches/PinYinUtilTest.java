package com.example.qzl.quick_searches;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by Qzl on 2016-07-25.
 */
public class PinYinUtilTest extends AndroidTestCase {
    public void pinyinUtilTest(){
        System.out.println("pinying = "+PinYinUtil.getPinYin("黑马"));
        Log.e("tag",PinYinUtil.getPinYin("黑马"));//HEIMA
        Log.e("tag",PinYinUtil.getPinYin("#黑**马"));//#HEI**MA
        Log.e("tag",PinYinUtil.getPinYin("O(∩_∩)O~黑马"));//HEIMA
    }
}
