package com.lmml.graph;



import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import java.util.EnumSet;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final Logger logger = Logger.getLogger(WebAppInitializer.class);

    @Override
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("getRootConfigClasses");
        return new Class[]{CoreConfig.class, JPAConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        System.out.println("getServletConfigClasses");
        return new Class[]{MVCConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        System.out.println("getServletMappings");
        logger.info("init servlet mappings.");
        return new String[]{"/*"};
    }

    @Override
    protected Filter[] getServletFilters() {
        System.out.println("getServletFilters");
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        return new Filter[]{characterEncodingFilter};
    }

    @Override
    protected void registerDispatcherServlet(ServletContext servletContext) {
       /* System.out.println("registerDispatcherServlet");
        super.registerDispatcherServlet(servletContext);
        FilterRegistration.Dynamic resourceFilter = servletContext.addFilter("resourceFilter", new ResourceFilter());
        resourceFilter.setAsyncSupported(isAsyncSupported());
        resourceFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "*.js", "*.jsp", "*.png", "*.jpg", "*.css");
*/    }

    @Bean
    public MultipartResolver multipartResolver() {
        System.out.println("multipartResolver");
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(104857600);
        return multipartResolver;
    }

}