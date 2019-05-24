package com.rc.util.util.JSONUtil.Impl;

import com.alibaba.fastjson.JSON;
import com.rc.util.util.JSONUtil.JSONStringUtil;

import java.util.List;
import java.util.Map;


class JSONStringConvertList implements JSONStringUtil {
    private static JSONStringConvertList jsonStringConvertList;

    static {
        jsonStringConvertList = new JSONStringConvertList();
    }

    private JSONStringConvertList() {

    }

    public static JSONStringConvertList getInstance() {
        return jsonStringConvertList;
    }

    @Override
    public Map jsonStringConvertMap(String content) {
        return null;
    }

    @Override
    public List jsonStringConvertList(String content) {
        List<Object> list = JSON.parseObject(content, List.class);

        return list;
    }
}
