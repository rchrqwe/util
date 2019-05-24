package com.rc.util.util.JSONUtil.Impl;

import com.rc.util.util.JSONUtil.JSONConvertUtil;

import java.util.List;
import java.util.Map;

class GetMapFromJSONString implements JSONConvertUtil {
    private static GetMapFromJSONString getMapFromJSONString = new GetMapFromJSONString();

    private GetMapFromJSONString() {

    }

    public static GetMapFromJSONString getInstance() {
        return getMapFromJSONString;
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
        return null;
    }


    @Override
    public Map getMap(String content) {
        return JSONStringConvertMap.getInstance().jsonStringConvertMap(content);
    }

    @Override
    public Map getMap(String content, String... keys) {
        String firstString = (String) content.subSequence(0, 1);
        Map<String, Object> map = null;
        if (firstString.equals(mapFirstString)) {
            map = doMapGetMap(content,keys);
        }
        if (firstString.equals(listFirstString)) {

        }

        return map;
    }

    public static Map doMapGetMap(String content, String... keys) {
        Map<String, Object> map = JSONStringConvertMap.getInstance().jsonStringConvertMap(content);
        Map resultMap = null;
        Object object = null;
        for (String key : keys) {
            if(keys[0].equals(key)){
                object = map.get(key);

            }

            if (object instanceof List) {
                if (((List) object).size() != 1) {
                    throw new RuntimeException("获取过程中，获取的list尺寸不为1");
                }
                object = GetListFromJSONString.doParseJSONArrayOrJSONObject((List) object);
            }
            if (object instanceof Map && !keys[keys.length-1].equals(key)) {
                Map<String, Object> temp = (Map<String, Object>) object;
                object = temp.get(key);
            }

            GetListFromJSONString.doParseJSONArrayOrJSONObject(object);
            if(keys[keys.length-1].equals(key)){
                resultMap = (Map) object;
            }
        }
        return resultMap;
    }

   /* public static Object dogetMap(){

    }
*/

}
