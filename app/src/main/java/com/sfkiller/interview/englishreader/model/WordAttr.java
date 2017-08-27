package com.sfkiller.interview.englishreader.model;

/**
 * 单词的属性，主要是开始位置和结束位置
 *
 * Created by SFKiller on 2017/8/26.
 */
public class WordAttr {

    public int begin;
    public int end;
    public int index;

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isIn(final int index) {
        if (index >= getBegin() && index <= getEnd()) {
            return true;
        }
        return false;
    }

}
