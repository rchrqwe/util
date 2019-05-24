package com.rc.util.util.JSONUtil.Impl;

import com.alibaba.fastjson.JSON;
import com.rc.util.util.JSONUtil.JSONStringUtil;

import java.util.List;
import java.util.Map;

class JSONStringConvertMap implements JSONStringUtil {
    private static JSONStringConvertMap jsonStringConvertMap;

    static {
        jsonStringConvertMap = new JSONStringConvertMap();
    }

    private JSONStringConvertMap() {

    }

    public static JSONStringConvertMap getInstance() {
        return jsonStringConvertMap;
    }

    @Override
    public Map jsonStringConvertMap(String content) {
        Map<String, Object> map = JSON.parseObject(content, Map.class);

        return map;
    }

    @Override
    public List jsonStringConvertList(String content) {
        return null;
    }
}
