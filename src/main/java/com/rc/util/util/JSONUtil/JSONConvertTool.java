package com.rc.util.util.JSONUtil;

import com.rc.util.util.JSONUtil.Impl.JSONConvertFactory;

import java.util.List;
import java.util.Map;

public class JSONConvertTool {
    private static JSONConvertTool jsonConvertTool = new JSONConvertTool();

    private JSONConvertTool(){

    }

    public static JSONConvertTool getInstance(){
        return jsonConvertTool;
    }

    public String getString(String content, String... keys){
        JSONConvertUtil jsonConvertUtil = JSONConvertFactory.getStringFromJSONString();

        return jsonConvertUtil.getString(content,keys);
    }

    public Map getMap(String content){
        JSONConvertUtil jsonConvertUtil = JSONConvertFactory.getMapFromJSONString();
        return jsonConvertUtil.getMap(content);
    }

    public Map getMap(String content, String ...keys){
        JSONConvertUtil jsonConvertUtil = JSONConvertFactory.getMapFromJSONString();
        return jsonConvertUtil.getMap(content,keys);
    }

    public List getList(String content){
        JSONConvertUtil jsonConvertUtil = JSONConvertFactory.getListFromJSONString();
        return jsonConvertUtil.getList(content);
    }

    public List getList(String content, String ...keys){
        JSONConvertUtil jsonConvertUtil =JSONConvertFactory.getListFromJSONString();
        return jsonConvertUtil.getList(content, keys);
    }
}
