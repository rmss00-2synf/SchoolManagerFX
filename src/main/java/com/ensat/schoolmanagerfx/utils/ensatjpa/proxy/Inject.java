package com.ensat.schoolmanagerfx.utils.ensatjpa.proxy;

import java.lang.reflect.Proxy;

public class Inject {
    @SuppressWarnings("unchecked")
    public static <T> T init(Class<T> interfaceType) {
        return (T) Proxy.newProxyInstance(
                interfaceType.getClassLoader(),
                new Class<?>[]{interfaceType},
                new DynamicInvocationHandler()
        );
    }
}
