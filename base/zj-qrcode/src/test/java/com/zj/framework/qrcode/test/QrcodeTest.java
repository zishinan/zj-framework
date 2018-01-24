package com.zj.framework.qrcode.test;

import com.zj.framework.qrcode.ZxingCode;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 测试类 <br/>
 * @date 2017-09-13 下午 1:31 <br/>
 */
public class QrcodeTest {
    private static final Logger logger = LoggerFactory.getLogger(QrcodeTest.class);

    @Test
    public void testToJsonString() throws Exception {
        String str = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=%E5%BC%82%E5%BD%A2%E4%BA%8C%E7%BB%B4%E7%A0%81&rsv_pq=ae6ccf920000f81b&rsv_t=cb4eNGOXwQLzxsj45SLn%2BPDf2WszIXQ8HGW5Ti48FI3YEPgc9DgNugaHxps&rqlang=cn&rsv_enter=1&rsv_sug3=14&rsv_sug1=8&rsv_sug7=100";
        ZxingCode.saveQRCode(str,"C:\\Users\\Administrator\\Desktop","test");
    }
}
