package com.rc.util.util.GeneralResponseTemplete;

import com.rc.util.util.GeneralResponseTemplete.Impl.ResponseStringConvertObject;

public class ResponseTempleteFactory {
    private static ResponseTempleteFactory responseTempleteFactory = new ResponseTempleteFactory();

    private ResponseTempleteFactory(){

    }
    public static ResponseTempleteFactory getInstance(){
        return responseTempleteFactory;
    }

    public static Object getResponseObject(String content){
        ResponseTemplete responseTemplete = new ResponseStringConvertObject();
        return responseTemplete.StringConvertObject(content);
    }
}
