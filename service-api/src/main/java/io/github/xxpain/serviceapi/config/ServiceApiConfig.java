package io.github.xxpain.serviceapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@Order
public class ServiceApiConfig {

    @Autowired
    public void initHandlerMapping(RequestMappingHandlerMapping mapping, ApplicationContext applicationContext) {
    }

    @Autowired
    public void initMappingHandlerAdapter(RequestMappingHandlerAdapter requestMappingHandlerAdapter, ApplicationContext applicationContext) {
    }

}
