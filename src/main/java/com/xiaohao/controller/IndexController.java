package com.xiaohao.controller;

import com.xiaohao.util.District;
import com.xiaohao.util.Domestic;
import com.xiaohao.util.Nearby;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author 小浩
 * @Date 2020/3/13 14:16
 * @Version 1.0
 **/
@RestController
@RequestMapping("/index")
@CrossOrigin(origins = "*",maxAge = 3600)
public class IndexController {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 国内疫情
     * @return 国内疫情
     */
    @RequestMapping(value = "/domestic",method= RequestMethod.GET, produces= MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    public  String domestic(){
         return  Domestic.request();

    }

    /**
     * 地区疫情
     * @return 地区疫情
     */
    @RequestMapping(value = "/district",method= RequestMethod.GET, produces= MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    public  String district(){
        return  District.request();

    }

    /**
     * 累计访问次数
     * @return 累计访问次数
     * 利用CAS实现同步
     */
    @RequestMapping(value = "/visitor",method= RequestMethod.GET)
    public  String visitor(){
        int count;
        do{
            count = atomicInteger.get();
        }while(!atomicInteger.compareAndSet(count, count + 1));
        return String.valueOf(atomicInteger.get());

    }

    /**
     * 周边疫情
     * @return 周边疫情
     */
    @RequestMapping(value = "/nearby",method= RequestMethod.POST, produces= MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    public  String nearby(String province ,String city,String district){


       return Nearby.request(province, city, district);

    }




}
