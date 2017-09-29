package com.zj.framework.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

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
     * @param data 数据集合，会将data以“data”注入模版
     * @param filePath 生成文件位置
     */
    public static void vmToFile(String vmPath,String vmName, List data,String filePath){
        VelocityEngine velocityEngine = new VelocityEngine();
        System.out.println(ClasspathResourceLoader.class.getName());
        velocityEngine.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, vmPath);
        velocityEngine.setProperty(INPUT_ENCODING, UTF8);
        velocityEngine.setProperty(OUTPUT_ENCODING, UTF8);
        velocityEngine.init();

        Template template = velocityEngine.getTemplate(vmName,UTF8);
        VelocityContext ctx = new VelocityContext();
        ctx.put("data",data);
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
