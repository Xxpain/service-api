package io.github.xxpain.serviceapi.config;

import org.springframework.aop.support.AopUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Collection;


public class RequestMappingRegister {

    private final RequestMappingHandlerMapping mapping;

    public RequestMappingRegister(RequestMappingHandlerMapping mapping) {
        this.mapping = mapping;
    }

    public void register(Collection<?> mappingBeans) {
        mappingBeans.forEach(this::addMapping);
    }

    private void addMapping(Object bean) {
        Class<?> beanClass = AopUtils.getTargetClass(bean);
        String simpleName = beanClass.getSimpleName();
        Class<?>[] interfaces = ClassUtils.getAllInterfacesForClass(beanClass);
        for (Class<?> itf : interfaces) {
            Method[] methods = itf.getDeclaredMethods();
            for (Method method : methods) {
                addMapping(itf.getSimpleName(), bean, simpleName, method);
            }
        }
    }

    private void addMapping(String itfName, Object bean, String className, Method method) {
        String name = method.getName();
        String path = "/" + itfName + "/" + name;
        RequestMappingInfo info =
                RequestMappingInfo
                        .paths(path)
                        .methods(RequestMethod.GET, RequestMethod.POST)
                        .build();
        mapping.registerMapping(info, bean, method);
        System.err.printf("RequestMappingRegister register request mapping, bean class : %s, method: %s, path: %s, http method: %s\n", itfName, name, path, "GET,POST");
    }


}
