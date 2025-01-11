package com.ensat.schoolmanagerfx.utils.ensatjpa.proxy;

import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Requete;
import com.ensat.schoolmanagerfx.utils.ensatjpa.dbmanip.ENSATJPA;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class DynamicInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Requete.class)) {
            Requete annotation = method.getAnnotation(Requete.class);
                if (args.length > 2) {
                    Object[] objects = new Object[args.length - 2];
                    String query = annotation.value();;
                    for (int i = 2; i < args.length; i++) {
                        query = query.replaceFirst("\\?", "'" + args[i].toString() + "'" );
                        objects[i-2] = args[i-2];
                    }
                    return caller(proxy,method,objects,query);
                }
                String request = annotation.value();
            return caller(proxy,method,args,request);
        }
        if (method.isDefault()) {
            return InvocationHandler.invokeDefault(proxy, method, args);
        } else {
            if (method.getName().contains("findBy")) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                StringBuilder request = new StringBuilder("SELECT * FROM "+args[1].getClass().getSimpleName().toUpperCase()+" WHERE ");
                request.append(method.getName().replace("findBy", "")).append(" = '").append(args[0].toString()).append("';");
                return caller(proxy,method,args,request.toString());
            }
        }
        return null;
    }

    private Object caller(Object proxy, Method method, Object[] args, String request) throws Throwable {
        Method m1Method = ENSATJPA.class.getMethod("findByQuery", String.class,Class.class);
        args[0]=request;
        args[1]=args[1].getClass();
        m1Method.setAccessible(true);
        return m1Method.invoke(proxy, args);
    }
}
