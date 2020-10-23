package com.company.mybatis.commons.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author bin.li
 * @date 2020/10/21
 */
public class GsonUtils {

    public static Gson getGsonWithOutConfig(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.disableHtmlEscaping();
        return gsonBuilder.create();
    }
}
