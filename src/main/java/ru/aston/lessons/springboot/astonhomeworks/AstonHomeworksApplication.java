package ru.aston.lessons.springboot.astonhomeworks;


import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.aston.lessons.springboot.astonhomeworks.config.WebConfig;
import ru.aston.lessons.springboot.astonhomeworks.config.WebMvcConfig;

import java.io.File;
import java.nio.file.Files;

public class AstonHomeworksApplication {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();

        final Connector connector = new Connector();
        connector.setPort(8080);
        connector.setScheme("http");
        connector.setSecure(false);
        tomcat.setConnector(connector);


        File baseDir = Files.createTempDirectory("embedded-tomcat").toFile();

        Context context= tomcat.addWebapp("",baseDir.getAbsolutePath());

        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(WebConfig.class);
        appContext.register(WebMvcConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);

        Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet).setLoadOnStartup(1);
        context.addServletMappingDecoded("/*", "dispatcherServlet");


        tomcat.start();
        tomcat.getServer().await();

    }
}
