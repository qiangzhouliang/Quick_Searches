package com.example.qzl.quick_searches;

/**
 * Created by Qzl on 2016-07-25.
 */
public class Friend implements Comparable<Friend> {
    private String name;
    private String pinyin;

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Friend(String name) {
        this.name = name;
        //一开始就转化好拼音
        setPinyin(PinYinUtil.getPinYin(name));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Friend another) {
        return getPinyin().compareTo(another.getPinyin());
    }
}
