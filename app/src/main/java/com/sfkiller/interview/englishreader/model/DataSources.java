package com.sfkiller.interview.englishreader.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据管理类
 *
 * Created by SFKiller on 2017/8/26.
 */
public class DataSources {

    private List<String> datas = new ArrayList<String>();

    private List<WordAttr> wordAttrs = new ArrayList<WordAttr>();

    private int totalSize = 0;

    public List<WordAttr> getWordAttrs() {
        return wordAttrs;
    }

    private static class SingletonHolder {
        static DataSources sInstance = new DataSources();
    }

    private DataSources() {

    }

    public static DataSources getInstance() {
        return SingletonHolder.sInstance;
    }

    public String getWord(int index) {
        return null != datas ? datas.get(index) : null;
    }

    public List<String> getDatas() {
        return datas;
    }

    public String getStringData() {
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : datas) {
            stringBuffer.append(s)
                    .append(" ");
        }
        Log.e("QIPU", " stringBuffer : " + stringBuffer.toString());
        return stringBuffer.toString();
    }

    /**
     * Test数据
     */
    public void fetchDatas() {

        datas.add("English");
        datas.add("Chinese");
        datas.add("Japanese");
        datas.add("Russian");
        datas.add("Colombian");
        datas.add("Argentinian");
        datas.add("Mexican");
        datas.add("Brazilian");
        datas.add("Peruvian");
        datas.add("British");
        datas.add("Scottish");
        datas.add("Irish");
        datas.add("Spanish");
        datas.add("Polish");
        datas.add("Swedish");
        datas.add("Finnish");
        datas.add("Austrian");
        datas.add("Ukrainian");
        datas.add("Italian");
        datas.add("Norwegian");
        datas.add("Portuguese");
        datas.add("American1");
        datas.add("American2");
        datas.add("American3");
        datas.add("American4");
        datas.add("American5");
        datas.add("American6");
        datas.add("American7");
        datas.add("American8");
        datas.add("American9");
        datas.add("American10");
        datas.add("American11");
        datas.add("American12");
        datas.add("American13");
        datas.add("American14");
        datas.add("American15");
        datas.add("American16");
        datas.add("American17");
        datas.add("American18");
        datas.add("American19");
        datas.add("American20");
        datas.add("American21");
        datas.add("American22");
        datas.add("American23");
        datas.add("American24");
        datas.add("American25");
        datas.add("American26");
        datas.add("American27");
        datas.add("American28");
        datas.add("American29");
        datas.add("American30");


        updateWordAttrs();

    }

    public void insert(int from, int to) {
        Log.e("QIPU", "insert");
        String mergeString = datas.get(from);
        Log.e("QIPU", "mergeString : " + mergeString);
        datas.add(to, mergeString);
        if (from >= to) {
            datas.remove(from + 1);
        } else {
            datas.remove(from);
        }
        updateWordAttrs();
    }

    private void updateWordAttrs() {
        int index = 0;
        for (String s : datas) {
            WordAttr wordAttr = new WordAttr();
            wordAttr.begin = totalSize;
            wordAttr.end = totalSize + s.length();
            wordAttr.index = index;
            wordAttrs.add(wordAttr);
            totalSize = totalSize + s.length() + 1;
            index++;
        }
    }

    public void setDatas(List<String> datas) {
        if (null != datas) {
            this.datas.clear();
            this.datas.addAll(datas);
        }
    }

}
