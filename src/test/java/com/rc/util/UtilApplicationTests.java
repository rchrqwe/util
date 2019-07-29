package com.rc.util;

import com.alibaba.fastjson.JSON;
import com.rc.util.util.JSONUtil.Impl.JSONConvertFactory;
import com.rc.util.util.JSONUtil.JSONConvertTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class UtilApplicationTests {

 /*   @Test
    public void contextLoads() {

        List<Object> list = new ArrayList<>();
        List<Object> tempList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        list1.add("777");
        list1.add("888");
        list1.add("999");
        map.put("test1",list1);
        tempList.add(map);
        list.add(tempList);
        List list2 = JSONConvertTool.getInstance().getList(JSON.toJSONString(list),"test1");
        System.out.println(list2);
    }
*/
    public static void main(String[] args){
        List<Object> list = new ArrayList<>();
        List<Object> tempList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        list1.add("777");
        list1.add("888");
        list1.add("999");

        map.put("test1",list1);
       List temp11 = new ArrayList();
        temp11.add(map);
        tempList.add(temp11);
        list.add(tempList);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("data",temp11);
        System.out.println(JSON.toJSONString(map1));
         List list2 = JSONConvertTool.getInstance().getList(JSON.toJSONString(map1),"data","test1");
 //String str = JSONConvertTool.getInstance().getString(JSON.toJSONString(map1),"data","777","test1");

        System.out.println(list2);
    }

}
