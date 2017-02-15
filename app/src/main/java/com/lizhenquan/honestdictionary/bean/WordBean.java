package com.lizhenquan.honestdictionary.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by lizhenquan on 2017/2/11.
 */

public class WordBean extends DataSupport{
        private String word;
        private String explanation;
    private String example;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public String toString() {
        return "WordBean{" +
                "word='" + word + '\'' +
                ", explanation='" + explanation + '\'' +
                ", example='" + example + '\'' +
                '}';
    }
}
