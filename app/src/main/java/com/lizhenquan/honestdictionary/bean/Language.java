package com.lizhenquan.honestdictionary.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 개발팀 on 2017-04-21.
 */

public class Language implements Parcelable {

    private int img_flag;
    private String language_name;
    private String language_code;

    public Language(){

    }

    public Language(int img_flag, String language_name, String language_code)
    {
        this.img_flag=img_flag;
        this.language_name=language_name;
        this.language_code = language_code;
    }

    protected Language(Parcel in) {
        img_flag = in.readInt();
        language_name = in.readString();
        language_code = in.readString();
    }

    public static final Creator<Language> CREATOR = new Creator<Language>() {
        @Override
        public Language createFromParcel(Parcel in) {
            return new Language(in);
        }

        @Override
        public Language[] newArray(int size) {
            return new Language[size];
        }
    };

    public int getImg_flag() {
        return img_flag;
    }

    public void setImg_flag(int img_flag) {
        this.img_flag = img_flag;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(img_flag);
        dest.writeString(language_name);
        dest.writeString(language_code);
    }

    @Override
    public String toString() {
        return "Language{" +
                "img_flag=" + img_flag +
                ", language_name='" + language_name + '\'' +
                ", language_code='" + language_code + '\'' +
                '}';
    }
}
