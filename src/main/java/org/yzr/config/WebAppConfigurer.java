package org.yzr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class WebAppConfigurer extends WebMvcConfigurationSupport {

    @Resource
    private Environment environment;
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        try {
            String path = ResourceUtils.getURL("").getPath();
            String prefix = ResourceUtils.FILE_URL_PREFIX + path;
            String debug = environment.getProperty("config.debug");
            if (debug !=null && debug.equals("debug")) {
                prefix = "classpath:/";
            }
            //System.out.println(prefix); //这行别删 file:/Users/chuangcui/IdeaProjects/intranet_app_manager/
            //安装包及icon存放在项目里的绝对路径 file:/Users/chuangcui/IdeaProjects/intranet_app_manager/build/classes/java/main/static/upload/android/com.xzyd.app/1670496177004/android.apk
            registry.addResourceHandler("/android/**").addResourceLocations(prefix+ "static/upload/android/");//原始写法
            registry.addResourceHandler("/ios/**").addResourceLocations(prefix + "static/upload/ios/");//原始写法
//            registry.addResourceHandler("/android/**").addResourceLocations(prefix+ "build/classes/java/main/static/upload/android/");//崔 修改为本地路径
//            registry.addResourceHandler("/ios/**").addResourceLocations(prefix + "build/classes/java/main/static/upload/ios/");//崔 修改为本地路径
            registry.addResourceHandler("/crt/**").addResourceLocations(prefix + "static/crt/");
            registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
            registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
            registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
            super.addResourceHandlers(registry);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController( "/" ).setViewName( "forward:/apps" );
        super.addViewControllers(registry);
    }
}
