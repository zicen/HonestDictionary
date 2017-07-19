package com.lizhenquan.honestdictionary.utils;

import android.content.Context;

import com.lizhenquan.honestdictionary.R;
import com.lizhenquan.honestdictionary.bean.Language;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ResourceHelper {


    public static ArrayList<Language> getLanguageArray(Context context, String key) {
        ArrayList<Language> array = new ArrayList<>();

        try {
            Class<R.array> res = R.array.class;
            Field field;
            int counter = 0;
            do {
                field = res.getField(key + "_" + counter);
                String[] resID = context.getResources().getStringArray(field.getInt(null));
                int imgRes = context.getResources().getIdentifier(resID[0], "drawable", context.getPackageName());
                Language lang = new Language(imgRes,resID[1],resID[2]);
                array.add(lang);
                counter++;
            } while (field != null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return array;
        }
    }


}

