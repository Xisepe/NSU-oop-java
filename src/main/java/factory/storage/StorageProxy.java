package factory.storage;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@RequiredArgsConstructor
public class StorageProxy<T> implements InvocationHandler {
    private final Storage<T> target;
    private final Object lock;

    public static <T> Storage<T> createProxy(Storage<T> target, Object lock) {
        Class<?>[] interfaces = {Storage.class};
        StorageProxy<T> proxy = new StorageProxy<>(target, lock);
        return (Storage<T>) Proxy.newProxyInstance(target.getClass().getClassLoader(), interfaces, proxy);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(target, args);
        if (method.getName().equals("take")) {
            lock.notify();
        }
        return result;
    }
}
