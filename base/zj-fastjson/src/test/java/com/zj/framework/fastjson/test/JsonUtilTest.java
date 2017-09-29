package com.zj.framework.fastjson.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zj.framework.fastjson.JsonUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 测试类 <br/>
 * @date 2017-09-13 下午 1:31 <br/>
 */
public class JsonUtilTest {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtilTest.class);

    @Test
    public void testToJsonString() throws Exception {
        User user = new User();
        user.setAge(13);
        user.setBirthday(new Date());
        user.setName("test yang");
        logger.info(JsonUtil.toJsonString(user));
    }

    @Test
    public void test() throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\error_vip_log.log")),
                    "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                if(lineTxt.contains("添加vip错误：")){
                    String error = lineTxt.substring(lineTxt.indexOf("[2017-09-21"),lineTxt.indexOf("错误：")+3);
                    lineTxt = lineTxt.replace(error,"");
                }
                JSONObject member = JSON.parseObject(lineTxt);
                System.out.println(member.getInteger("mid")+","+member.getFloat("totalCharge"));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("read errors :" + e);
        }

    }


    class User{
        private int age;
        private String name;
        private Date birthday;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }
    }
}
