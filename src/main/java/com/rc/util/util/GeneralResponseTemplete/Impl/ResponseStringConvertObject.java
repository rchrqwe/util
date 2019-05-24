package com.rc.util.util.GeneralResponseTemplete.Impl;

import com.alibaba.fastjson.JSON;
import com.rc.util.util.GeneralResponseTemplete.ResponseTemplete;

public class ResponseStringConvertObject implements ResponseTemplete {

    public Object StringConvertObject(String content){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(content);
        return JSON.parseObject(stringBuffer.toString());
    }
}
