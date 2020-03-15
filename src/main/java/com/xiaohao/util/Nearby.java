package com.xiaohao.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @Author 小浩
 * @Date 2020/3/13 23:32
 * @Version 1.0
 **/
public class Nearby {
    private  static  String  api = "http://api.tianapi.com/txapi/ncovnearby/index?key=xxxxxx";



    public static String request(String province,String city,String district) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        String httpUrl = api+"&province="+province+"&city="+city+"&district="+district;

        System.out.println(httpUrl);


        try {

            URL url = new URL(httpUrl);

            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");

            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void main(String[] args) {
        Nearby.request("广东省","深圳市","龙岗区");
    }
}
