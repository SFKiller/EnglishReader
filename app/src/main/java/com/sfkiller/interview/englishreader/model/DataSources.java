package com.sfkiller.interview.englishreader.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据管理类
 *
 * Created by SFKiller on 2017/8/26.
 */
public class DataSources {

    private List<String> datas = new ArrayList<String>();

    private static class SingletonHolder {
        static DataSources sInstance = new DataSources();
    }

    private DataSources() {

    }

    public static DataSources getInstance() {
        return SingletonHolder.sInstance;
    }

    public List<String> getDatas() {
        return datas;
    }

    /**
     * Test数据
     */
    public void fetchDatas() {
        datas.add("XXXXXXXXXXXXXXXXXX1111111111111111");
        datas.add("XXXXXXXXXXXXXXXXXX2222222222222222222222222222222222222222");
        datas.add("XXXXXXXXXXXXXXXXXX33333333333333333333333333333333333");
        datas.add("XXXXXXXXXXXXXXXXXX44444444444");
        datas.add("XXXXXXXXXXXXXXXXXX5555555555555555555555555555555555555555");
        datas.add("XXXXXXXXXXXXXXXXXX66666666666666666666666666666666666666666666666");
        datas.add("XXXXXXXXXXXXXXXXXX77777777777777");
        datas.add("XXXXXXXXXXXXXXXXXX88888888888888888888888");
        datas.add("XXXXXXXXXXXXXXXXXX999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        datas.add("XXXXXXXXXXXXXXXXXX1000000000000000000000000000000000010000000000000");
        datas.add("XXXXXXXXXXXXXXXXXX110111111111111111111111111111111111111111");
        datas.add("XXXXXXXXXXXXXXXXXXqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
        datas.add("XXXXXXXXXXXXXXXXXXwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
        datas.add("XXXXXXXXXXXXXXXXXXeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        datas.add("XXXXXXXXXXXXXXXXXXrrrrrrrrr");
        datas.add("XXXXXXXXXXXXXXXXXXddddddddddddddddddddddddddddd");
        datas.add("XXXXXXXXXXXXXXXXXXsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        datas.add("XXXXXXXXXXXXXXXXXXffffffffffffff");
        datas.add("XXXXXXXXXXXXXXXXXggggggggggggggggggggggggggggggggggggggggggggX");
        datas.add("XXXXXXXXXXXXXXXXXXhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        datas.add("XXXXXXXXXXXXXXXXXXfffffffffffffffffffffffffffffffffffffffffffffff");
        datas.add("XXXXXXXXXXXXXXXXXXvvvvvvvvvvvvvvvvvvvvvvvvvv");
        datas.add("XXXXXXXXXXXXXXXXXXbbbbbbbbbbbbbbbbbbbbb");
        datas.add("XXXXXXXXXXXXXXXXXXnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
        datas.add("XXXXXXXXXXXXXXXXXXhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        datas.add("XXXXXXXXXXXXXXXXXXjjjjjjjjjjjjjjjjjjjjjjjj");
        datas.add("XXXXXXXXXXXXXXXXXX2222222222222222222222222222222");
        datas.add("XXXXXXXXXXXXXXXXXXddddddddddddddddddddddddddd");
        datas.add("XXXXXXXXXXXXXXXXXXgggggggggggggggggggggggggggggggggg");
        datas.add("XXXXXXXXXXXXXXXXXXrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        datas.add("XXXXXXXXXXXXXXXXXXssssssssssssssssssssss");
        datas.add("XXXXXXXXXXXXXXXXXqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqX");
        datas.add("XXXXXXXXXXXXXXXXXXddddddddddddddddddd");
        datas.add("XXXXXXXXXXXXXXXXXXzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        datas.add("XXXXXXXXXXXXXXXXXXffffffffffffffffffffffffff");
        datas.add("XXXXXXXXXXXXXXXXXtttttttttttttttttttttttttttttttX");
        datas.add("XXXXXXXXXXXXXXXXXXhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        datas.add("XXXXXXXXXXXXXXXXXXjkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        datas.add("XXXXXXXXXXXXXXXXXXllllllllllllllllllllllll");
        datas.add("XXXXXXXXXXXXXXXXXXssssssssssssssss");
        datas.add("XXXXXXXXXXXXXXXXXXssssssssssssssssss");
    }

    public void setDatas(List<String> datas) {
        if (null != datas) {
            this.datas.clear();
            this.datas.addAll(datas);
        }
    }

}
