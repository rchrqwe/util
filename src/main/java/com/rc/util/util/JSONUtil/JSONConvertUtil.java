package com.rc.util.util.JSONUtil;

import java.util.List;
import java.util.Map;

public interface JSONConvertUtil {
    String listFirstString = "[";
    String mapFirstString = "{";

    List getList(String content);

    List getList(String content, String ...keys);

    String getString(String content, String... keys);

    Map getMap(String content);

    Map getMap(String content, String ...keys);
}
