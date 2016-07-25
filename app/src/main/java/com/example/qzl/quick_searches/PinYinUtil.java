package com.example.qzl.quick_searches;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by Qzl on 2016-07-25.
 */
public class PinYinUtil {
    /**
     *获取汉字的拼音,会消耗一定资源，不应该被频繁调运
     * @param chinese
     * @return
     */
    public static String getPinYin(String chinese){
        if (TextUtils.isEmpty(chinese)){
            return null;
        }
        //用来设置转化的拼音的大小写，或声调
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);//设置转换的拼音是大写字母
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//设置转化的拼音不带声调
        //1 由于只能对单个汉字转化，所以需要将字符串转化为字符数据，然后对买个字符转化，然后拼接
        char[] charArray = chinese.toCharArray();
        String pinyin = "";
        for (int i = 0; i < charArray.length; i++) {
           // 2  过滤空格
            if (Character.isWhitespace(charArray[i]))continue;
            //3 需要判断是否是汉字
            //汉字占两个字节，一个字节范围是-128-127，那么汉子肯定大于127
            if (charArray[i] > 127){
                //可能是汉字
                try {
                    //由于多音字的存在，所以返回的是数组
                    String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(charArray[i],format);
                    if (pinyinArr != null){
                        pinyin += pinyinArr[0];//此处即使有多音字，那么也只能取第一个拼音，要兼容单音字的存在
                    }else {
                        //说明没有找到对应的拼音，汉字有问题，或者可能不是汉字，则忽略
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                    //说明转化失败，不是汉字，比如O(∩_∩)O~，那么则忽略
                }
            }else {
                //肯定不是汉字，应该是键盘上能够直接输入的字符，这些字符能够排序，但不能获取拼音，最后直接拼接
                pinyin += charArray[i];
            }
        }
        return pinyin;
    }
}
