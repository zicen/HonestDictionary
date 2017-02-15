package com.lizhenquan.honestdictionary.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by lizhenquan on 2017/2/14.
 */

public class Word extends DataSupport {
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                '}';
    }
}
