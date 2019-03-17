package Proxy.cacheproxy;


import Proxy.serialization.SerializationUtils;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.ClassLoader.getSystemClassLoader;

public class CacheProxy implements InvocationHandler {
    private final Map<String, Object> resultByArg = new HashMap<>();
    private final Object delegate;

    private final String rootCacheDirectory;

    /**
     * @param delegate           Объект который оборачиваем в кеширующий проксис
     * @param rootCacheDirectory Директория в которой будет храниться кеш
     * @param <T>
     * @return
     */
    public static <T> T cacheFactory(Object delegate, String rootCacheDirectory) {
        return (T) Proxy.newProxyInstance(getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CacheProxy(delegate, rootCacheDirectory)
        );
    }

    /**
     * Протсой конструктор
     *
     * @param delegate           объект который оборачиваем в кеширующий прокси
     * @param rootCacheDirectory директория в которой будет храниться кеш
     */
    public CacheProxy(Object delegate, String rootCacheDirectory) {
        this.delegate = delegate;
        this.rootCacheDirectory = rootCacheDirectory;
    }

    /**
     * Переопределение функции исполнения.
     * Добавление логики:
     * Если метод имеет анотацию Cash, ищем информацию в кеше, нашли возвращаем кеш значение
     * иначе вызываем стандартрный метод, добавляем в кеш и возвращаем значение.
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cache.class)) {
            return method.invoke(delegate, args);
        }

        String key = key(method, args);
        Cache cacheSettings = method.getDeclaredAnnotation(Cache.class);
        if (cacheSettings.cacheType() == CacheType.IN_MEMORY) {
            if (!resultByArg.containsKey(key)) {
                Object result = method.invoke(delegate, args);
                resultByArg.put(key, result);
            }

            return resultByArg.get(key);
        } else {
            File cachedResultFile = Paths.get(rootCacheDirectory, key + ".ser").toFile();
            if (!cachedResultFile.exists()) {
                Object result = method.invoke(delegate, args);
                SerializationUtils.serialize((Serializable) result, cachedResultFile, cacheSettings.zip());
            }

            return SerializationUtils.deserialize(cachedResultFile, cacheSettings.zip());
        }
    }


    /**
     * Метод получения уникального ключа основанного  на входны параметрах
     *
     *
     * @param method
     * @param args
     * @return
     */
    private String key(Method method, Object[] args) {
        Cache annotationSettings = method.getDeclaredAnnotation(Cache.class);
        Class<?>[] parameterTypes = method.getParameterTypes();

        byte[] identityBy = annotationSettings.identityBy();

        StringBuilder builder = new StringBuilder();
        if (annotationSettings.fileNamePrefix().equals("")) {
            builder.append(method.getName());
        } else {
            builder.append(annotationSettings.fileNamePrefix());
        }
        if (identityBy.length == 0) {
            for (int i = 0; i < parameterTypes.length; i++) {
                builder.append("-" + parameterTypes[i].getName() + args[i].hashCode());
            }
        } else {
            for (int i = 0; i < identityBy.length; i++) {
                builder.append("-" + parameterTypes[identityBy[i]] + args[identityBy[i]].hashCode());


            }
        }
        System.out.println(builder.toString());
        return builder.toString();
    }
}
