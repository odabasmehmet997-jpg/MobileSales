package retrofit2;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

abstract class ServiceMethod<T> {
    abstract T invoke(Object[] objArr);
    ServiceMethod() {
    } 
    static <T> ServiceMethod<T> parseAnnotations(Retrofit retrofit, Method method) {
        RequestFactory annotations = RequestFactory.parseAnnotations(retrofit, method);
        Type genericReturnType = method.getGenericReturnType();
        if (Utils.hasUnresolvableType(genericReturnType)) {
            throw Utils.methodError(method, "Method return type must not include a type variable or wildcard: %s", genericReturnType);
        }
        if (genericReturnType == Void.TYPE) {
            throw Utils.methodError(method, "Service methods cannot return void.");
        }
        return HttpServiceMethod.parseAnnotations(retrofit, method, annotations);
    }
}
