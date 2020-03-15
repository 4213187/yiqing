package com.xiaohao.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author 小浩
 * @Date 2020/3/13 17:06
 * @Version 1.0
 * 国内疫情
 **/
public class Domestic {
   private static String httpUrl = "http://api.tianapi.com/txapi/ncov/index?key=xxxxx";
//   private static String httpArg ="";


    public static String request() {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
//        httpUrl = httpUrl + "?" + httpArg;

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

//    public static void main(String[] args) {
//        Domestic.request();
//    }
}
