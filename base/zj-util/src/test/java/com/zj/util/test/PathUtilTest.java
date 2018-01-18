package com.zj.util.test;

import com.zj.util.file.PathUtil;
import com.zj.util.game.ExtractGameData;
import org.junit.Test;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 测试path <br/>
 * @date 2017-05-24 下午 2:15 <br/>
 */
public class PathUtilTest {
    @Test
    public void name() throws Exception {
        System.out.println(PathUtil.getClassPath());
    }

    @Test
    public void testExtraGameData() throws Exception {
        String ex = ExtractGameData.uncompress("465465464654646");
        System.out.println(ex);
    }
}
