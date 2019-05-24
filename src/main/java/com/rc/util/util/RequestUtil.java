package com.rc.util.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestUtil {

    private final static String CONTENT_TYPE_TEXT_JSON = "application/json";


    /**
     * httpClient post请求工具类方法
     * @param url post请求的url
     * @param content 发送报文的具体内容，自行组织，一般为json
     * @return json数据
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String postRequestContent(String url, String content,Map<String, Object> headerMap, String usageSituation)
            throws ClientProtocolException, IOException {

        // 新建httpClient连接
        CloseableHttpClient client = HttpClients.createDefault();

        // httpPost方法发送
        HttpPost httpPost = new HttpPost(url);

        // 设置http请求头，将编码格式设成utf-8
        // httpPost.setHeader("Content-Type", "application/json");

        StringEntity se = new StringEntity(content, "utf-8");

        // 设置格式
        se.setContentType(CONTENT_TYPE_TEXT_JSON);
        // 迭代HashMap里面对象获取key和value并加入header
        for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
            httpPost.setHeader(entry.getKey(), entry.getValue().toString());
        }
        long startTime = System.currentTimeMillis();

        // 发送数据
        httpPost.setEntity(se);

        // 获取响应报文
        CloseableHttpResponse response = client.execute(httpPost);
        long endTime = System.currentTimeMillis();
        // System.out.println(response.getStatusLine().getStatusCode());

        // 将其转换回utf8
        HttpEntity entity = response.getEntity();
        Map<String, Object> map = new HashMap<>();
        map.put("httpCode", response.getStatusLine().getStatusCode());
        String result = EntityUtils.toString(entity, "UTF-8");
        map.put("result", result);
        map.put("responseTime", endTime-startTime);


        // 返回json数据
        return JSON.toJSONString(map);
    }

    /** httpClient get请求工具类方法
     * @param url get请求的url地址
     * @param params List<NameValuePair> params 用法为 new BasicNameValuePair();  然后add
     * @return 原始报文字符串
     */
    public String getRequest(String url, List<NameValuePair> params) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "403");
        map.put("desc", "服务器内部错误");
        // 创建参数队列
        String content ="";
        try {
            // 参数转换为字符串
            String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(params, "UTF-8"));
            String url1 = url + "?" + paramsStr;
            // 创建httpget.
            HttpGet httpGet = new HttpGet(url1);
            System.out.println("executing request " + httpGet.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    // 打印响应内容长度
                    System.out.println("Response content length: " + entity.getContentLength());
                    // 打印响应内容
                    content = EntityUtils.toString(entity);
                    System.out.println("Response content: " + content);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            content = JSON.toJSONString(map);
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /** httpClient post请求工具类方法
     * @param url post请求的url地址
     * @param params List<NameValuePair> params 用法为 new BasicNameValuePair();  然后add
     * @return 原始报文字符串
     */
    public String getPostRequest(String url, List<NameValuePair> params, String usageSituation) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HashMap<String, String> map = new HashMap<>();

        // 创建参数队列
        String content ="";
        try {
            // 参数转换为字符串

            // 创建httpget.
            HttpPost httpPost = new HttpPost();
            httpPost.setURI(new URI(url));
            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            long startTime = System.currentTimeMillis();
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                // System.out.println(response.getStatusLine());
                if (entity != null) {
                    // 打印响应内容
                    content = EntityUtils.toString(entity);
                }
                long endTime = System.currentTimeMillis();
                map.put("httpCode", response.getStatusLine().getStatusCode()+"");
                // String result = EntityUtils.toString(entity, "UTF-8");
                map.put("result", content);
                map.put("responseTime", endTime-startTime+"");
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("httpCode", "500");
            // String result = EntityUtils.toString(entity, "UTF-8");
            map.put("result", content);
            map.put("responseTime", "");

        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return JSON.toJSONString(map);
    }
}
