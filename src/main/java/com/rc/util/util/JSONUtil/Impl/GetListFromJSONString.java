package com.rc.util.util.JSONUtil.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rc.util.util.JSONUtil.JSONConvertUtil;
import com.rc.util.util.JSONUtil.JSONStringUtil;

import java.util.List;
import java.util.Map;

class GetListFromJSONString implements JSONConvertUtil {
    private static GetListFromJSONString getListFromJSONString = new GetListFromJSONString();

    private GetListFromJSONString() {

    }

    public static GetListFromJSONString getInstance() {
        return getListFromJSONString;
    }

    public List getList(String content) {
        JSONStringUtil jsonStringUtil = JSONStringFactory.getJSONStringConvertList();
        return jsonStringUtil.jsonStringConvertList(content);
    }

    @Override
    public List getList(String content, String... keys) {
        String firstString = (String) content.subSequence(0, 1);
        List<Object> list = null;
        if (firstString.equals(mapFirstString)) {
            list = doMapGetList(content, keys);
        }
        System.out.println(firstString.equals(listFirstString));
        if (firstString.equals(listFirstString)) {
            list = doListGetList(content, keys);
        }
        return list;
    }

    public static List doListGetList(String content, String... keys) {
        List<Object> list = null;
        for (String key : keys) {
            Object tempList = null;
            // 将list 初始化解析listJSON字符串
            if (keys[0].equals(key)) {
                tempList = JSONStringConvertList.getInstance().jsonStringConvertList(content);
                if (tempList instanceof List) {
                    if (((List) tempList).size() != 1) {
                        throw new RuntimeException("获取过程中，获取的list尺寸不为1");
                    }
                    tempList = doParseJSONArrayOrJSONObject((List) tempList);
                }
                if (tempList instanceof Map) {
                    Map<String, Object> temp = (Map<String, Object>) tempList;
                    list = (List<Object>) temp.get(key);
                }
               doParseJSONArrayOrJSONObject(tempList);
            }
            if (tempList instanceof List) {
                if (((List) tempList).size() != 1) {
                    throw new RuntimeException("获取过程中，获取的list尺寸不为1");
                }
                tempList = doParseJSONArrayOrJSONObject((List) tempList);
            }
            if (tempList instanceof Map) {
                Map<String, Object> temp = (Map<String, Object>) tempList;
                tempList = temp.get(key);
                continue;
            }
            doParseJSONArrayOrJSONObject(tempList);
            if (keys[keys.length - 1].equals(key)) {
                list = (List) tempList;
            }
        }
        return list;
    }

    public static List doMapGetList(String content, String... keys) {
        List<Object> list = null;
        Object tempList = null;
        for (String key : keys) {
            // 将list 初始化解析listJSON字符串
            if (keys[0].equals(key)) {
                tempList = JSONStringConvertMap.getInstance().jsonStringConvertMap(content);
            }

            if (tempList instanceof List) {
                if (((List) tempList).size() != 1) {
                    throw new RuntimeException("获取过程中，获取的list尺寸不为1");
                }
                tempList = doParseJSONArrayOrJSONObject((List) tempList);
            }
            if (tempList instanceof Map) {
                Map<String, Object> temp = (Map<String, Object>) tempList;
                tempList = temp.get(key);
            }
            doParseJSONArrayOrJSONObject(tempList);
            if (keys[keys.length - 1].equals(key)) {
                list = (List) tempList;
            }
        }
        return list;
    }

    public static Object doParseJSONArrayOrJSONObject(List<Object> list) {
        Object temp = list.get(0);
        if (temp instanceof JSONArray) {
            temp = JSONObject.parseArray(((JSONArray) temp).toJSONString(), Object.class);
        }
        if (temp instanceof List && ((List) temp).size() == 1) {
            temp = doParseJSONArrayOrJSONObject((List) temp);
        }
        if (temp instanceof JSONObject) {
            temp = JSONObject.parseObject(((JSONObject) temp).toJSONString(), Map.class);
        }

        return temp;
    }

    public static void doParseJSONArrayOrJSONObject(Object object){
        if (object instanceof String) {
            throw new RuntimeException("检查json");
        }
        if (object instanceof JSONArray) {
            object = JSONObject.parseArray(((JSONArray) object).toJSONString(), Object.class);
        }
        if (object instanceof JSONObject) {
            object = JSONObject.parseObject(((JSONObject) object).toJSONString(), Map.class);
        }
    }

    @Override
    public String getString(String content, String... key) {
        return null;
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
