package io.github.xxpain.serviceapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Configuration
@Order
public class ServiceApiConfig {

    @Autowired
    public void initHandlerMapping(RequestMappingHandlerMapping mapping, ApplicationContext applicationContext) {
        Map<String, Object> serviceBeans = applicationContext.getBeansWithAnnotation(Service.class);
        Collection<Object> objects = serviceBeans.values();
        RequestMappingRegister register = new RequestMappingRegister(mapping);
        register.register(objects);
    }

    @Autowired
    public void initMappingHandlerAdapter(RequestMappingHandlerAdapter requestMappingHandlerAdapter, ApplicationContext applicationContext) {
        MethodArgumentReturnValueHandler resolverAndReturnValueHandler = new MethodArgumentReturnValueHandler(requestMappingHandlerAdapter, applicationContext);
        requestMappingHandlerAdapter.setReturnValueHandlers(Collections.singletonList(resolverAndReturnValueHandler));
        requestMappingHandlerAdapter.setArgumentResolvers(Collections.singletonList(resolverAndReturnValueHandler));
    }

}
