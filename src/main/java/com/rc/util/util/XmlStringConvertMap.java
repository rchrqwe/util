package com.rc.util.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class XmlStringConvertMap {
    private XmlStringConvertMap xmlStringConvertMap;

    @PostConstruct
    public void init() {
        xmlStringConvertMap = this;
        System.out.println("成功装配XmlStringConvertMap");
    }

    public Map<String,  Object> XmlStringConvertMap(String xmlString){
        DomConvertMap domConvertMap = new DomConvertMap();
        Document document = null;
        try {
            document = DocumentHelper.parseText(xmlString);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = domConvertMap.domToMap(document);

        return map;
    }

    public Map<String, Object> getMapFromMap(Map<String, Object> map, String ...key){
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.putAll(map);
        System.out.println(map);
        for(int i = 0; i<key.length; i++){
            returnMap = (Map<String, Object>) returnMap.get(key[i]);
        }
        System.out.println(returnMap);
        return returnMap;
    }

}
