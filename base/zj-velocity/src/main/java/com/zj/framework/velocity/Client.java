package com.zj.framework.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.texen.util.PropertiesUtil;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: velocity使用 <br/>
 * @date 2017-09-27 上午 9:33 <br/>
 */
public class Client {
    private static final String CLASSPATH = "classpath";
    private static final String CLASSPATH_CLASS = "classpath.resource.loader.class";
    public static void main(String[] args) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER,CLASSPATH);
        velocityEngine.setProperty(CLASSPATH_CLASS, ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        Template actionTpt = velocityEngine.getTemplate("ActionTemplate.vm");
        VelocityContext ctx = new VelocityContext();

        ctx.put("classNameLowCase", "teacher");
        ctx.put("classNameUpCase", "Teacher");
        String[][] attrs = {
                {"Integer","id"},
                {"String","name"},
                {"String","serializeNo"},
                {"String","titile"},
                {"String","subject"}
        };
        ctx.put("attrs", attrs);
        String rootPath = PropertiesUtil.class.getName();
        merge(actionTpt,ctx,rootPath+"/java/com/liuxiang/velocity/action/TeacherAction.java");
        System.out.println("success...");
    }

    private static void merge(Template template, VelocityContext ctx, String path) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path);
            template.merge(ctx, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
