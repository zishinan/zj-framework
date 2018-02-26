package com.zj.framework.activiti;

import com.alibaba.fastjson.JSON;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;

/**
 * @author xi.yang(xi.yang@downjoy.com)
 * @create 2018-01-25 下午 4:11
 **/
public class Main {
    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(JSON.toJSONString(engine));
    }
}
