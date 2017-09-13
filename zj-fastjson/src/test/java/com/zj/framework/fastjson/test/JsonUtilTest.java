package com.zj.framework.fastjson.test;

import com.zj.framework.fastjson.JsonUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
