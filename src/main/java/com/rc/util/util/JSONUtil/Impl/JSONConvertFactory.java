package com.rc.util.util.JSONUtil.Impl;

import com.rc.util.util.JSONUtil.JSONConvertUtil;

public class JSONConvertFactory {
    private static GetListFromJSONString getListFromJSONString;
    private static GetMapFromJSONString getMapFromJSONString;
    private static GetStringFromJSONString getStringFromJSONString;

    static {
        getListFromJSONString = GetListFromJSONString.getInstance();
        getMapFromJSONString = GetMapFromJSONString.getInstance();
        getStringFromJSONString = GetStringFromJSONString.getInstance();
    }

    private JSONConvertFactory() {


    }

    public static JSONConvertUtil getListFromJSONString() {
        return getListFromJSONString;
    }

    public static JSONConvertUtil getMapFromJSONString() {
        return getMapFromJSONString;
    }

    public static JSONConvertUtil getStringFromJSONString() {
        return getStringFromJSONString;
    }
}
