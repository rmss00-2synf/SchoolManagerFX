package com.ensat.schoolmanagerfx.utils.ensatjpa.proxy;

import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Requete;
import com.ensat.schoolmanagerfx.utils.ensatjpa.dbmanip.ENSATJPA;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class DynamicInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Vérifie si la méthode a l'annotation CustomMethod
        if (method.isAnnotationPresent(Requete.class)) {
            Requete annotation = method.getAnnotation(Requete.class);
            // Comportement simple
//                System.out.println("Executing annotated method: " + annotation.value());
            return null;
        }

        // Si méthode par défaut, invoquez-la
        if (method.isDefault()) {
            return InvocationHandler.invokeDefault(proxy, method, args);
        } else {
            if (method.getName().contains("findBy")) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                StringBuilder request = new StringBuilder("SELECT * FROM "+args[1].getClass().getSimpleName().toUpperCase()+" WHERE ");
                request.append(method.getName().replace("findBy", "")).append(" = '").append(args[0].toString()).append("';");
                System.out.println("m2 called, now calling m1...");
                args[0]=request.toString();
                // Appelez la méthode m1 sur le proxy I1 (super méthode)
                Method m1Method = ENSATJPA.class.getMethod("findByAttribute", args[0].getClass(),Object.class);
                return m1Method.invoke(proxy, args);

            }
        }
        // Comportement générique pour les méthodes non annotées
        System.out.println("Method " + method.getName() + " is called.");
        return null;
    }
}
