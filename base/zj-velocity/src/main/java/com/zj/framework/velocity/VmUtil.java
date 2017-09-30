package com.zj.framework.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 生成模板工具 <br/>
 * @date 2017-09-29 下午 5:14 <br/>
 */
public class VmUtil {
    private static final String INPUT_ENCODING = "input.encoding";
    private static final String OUTPUT_ENCODING = "output.encoding";
    private static final String UTF8 = "UTF-8";

    /**
     * @param vmPath 模板位置
     * @param dataMap 数据
     * @param filePath 生成文件位置
     */
    public static void vmToFile(String vmPath, String vmName, Map<String,Object> dataMap, String filePath){
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, vmPath);
        velocityEngine.setProperty(INPUT_ENCODING, UTF8);
        velocityEngine.setProperty(OUTPUT_ENCODING, UTF8);
        velocityEngine.init();

        Template template = velocityEngine.getTemplate(vmName,UTF8);
        VelocityContext ctx = new VelocityContext();
        for (String key : dataMap.keySet()) {
            ctx.put(key,dataMap.get(key));
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filePath);
            template.merge(ctx, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
