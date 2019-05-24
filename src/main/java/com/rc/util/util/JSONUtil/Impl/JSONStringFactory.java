package com.rc.util.util.JSONUtil.Impl;

import com.rc.util.util.JSONUtil.JSONStringUtil;

 class JSONStringFactory {
    private static JSONStringConvertMap jsonStringConvertMap;
    private static JSONStringConvertList jsonStringConvertList;

    static {
        jsonStringConvertMap = JSONStringConvertMap.getInstance();
        jsonStringConvertList = JSONStringConvertList.getInstance();
    }

     static JSONStringUtil getJSONStringConvertMap(){
        return jsonStringConvertMap;
    }

     static JSONStringUtil getJSONStringConvertList(){
        return jsonStringConvertList;
    }
}
