package com.lizhenquan.honestdictionary.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhenquan on 2017/2/11.
 */

public class StringUtils {
    /**
     * 获取
     * @param text
     * @return
     */
    public static List<String> splitText(String text) {
        List<String> data = new ArrayList<>();
        if (text.contains(",") || text.contains(".") || text.contains("...")
                || text.contains("?") || text.contains("!")) {
            text.replaceAll(","," ");
            text.replaceAll("."," ");
            text.replaceAll("..."," ");
            text.replaceAll("!"," ");
        }
        String[] split = text.split(" ");
        for (int i = 0; i < split.length; i++) {
           data.add(split[i]);
        }
        return data;
    }
    public static String toUtf8(String str) {
          String result = null;
          try {
                  result = new String(str.getBytes("UTF-8"), "UTF-8");
              } catch (UnsupportedEncodingException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              }
          return result;
       }
    //用换行符（在多数编程语言中为转义符号 \n ）来分隔要翻译的多个单词
   public static  String splitDexWord(String text){
       String s = text.replaceAll(" ", " \n");
       return s;
   }

}
