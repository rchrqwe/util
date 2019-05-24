package com.rc.util.util.JSONUtil.Impl;

import com.alibaba.fastjson.JSON;
import com.rc.util.util.JSONUtil.JSONConvertUtil;

import java.util.List;
import java.util.Map;

class GetStringFromJSONString implements JSONConvertUtil {
    private static GetStringFromJSONString getStringFromJSONString = new GetStringFromJSONString();

    public static GetStringFromJSONString getInstance() {
        return getStringFromJSONString;
    }

    private GetStringFromJSONString() {

    }

    @Override
    public List getList(String content) {
        return null;
    }

    @Override
    public List getList(String content, String... key) {
        return null;
    }

    @Override
    public String getString(String content, String... key) {
        return doGetString(content, key);
    }

    public static String doGetString(String content, String... keys) {
        JSONStringConvertMap jsonStringConvertMap = JSONStringConvertMap.getInstance();
        Map map = jsonStringConvertMap.jsonStringConvertMap(content);
        String result = "";

        if (1 != keys.length) {
            if (map != null && map.get(keys[0]) != null) {
                String[] newKeyArrays = new String[keys.length - 1];
                System.arraycopy(keys, 1, newKeyArrays, 0, newKeyArrays.length);
                result = doGetString(JSON.toJSONString(map.get(keys[0])), newKeyArrays);
            } else {
                JSONStringConvertList jsonStringConvertList = JSONStringConvertList.getInstance();
                List list = jsonStringConvertList.jsonStringConvertList(content);
                if (list != null && list.size() > 1) {
                    result = JSON.toJSONString(list);
                } else if (list != null & list.size() == 1) {
                    Map temp = (Map) list.get(0);
                    result = doGetString(JSON.toJSONString(temp), keys);
                }
            }
        } else {
            if (map != null && map.get(keys[0]) != null) {
                result = map.get(keys[0]).toString();
            } else {
                JSONStringConvertList jsonStringConvertList = JSONStringConvertList.getInstance();
                List list = jsonStringConvertList.jsonStringConvertList(content);
                if (list != null && list.size() > 1) {
                    result = JSON.toJSONString(list);
                } else if (list != null & list.size() == 1) {
                    Map temp = (Map) list.get(0);
                    result = doGetString(JSON.toJSONString(temp), keys);
                }
            }
        }

        return result;
    }

    @Override
    public Map getMap(String content) {
        return null;
    }

    @Override
    public Map getMap(String content, String... key) {
        return null;
    }
}
