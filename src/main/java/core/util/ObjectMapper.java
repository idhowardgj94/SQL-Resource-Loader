package core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

public class ObjectMapper implements IMapper {
    @Override
    public <T> T mapping(Class<T> object) throws InstantiationException, IllegalAccessException {
        T instance = object.newInstance();
        return instance;
    }

    @Override
    public <T> T mapping(Map<String, String> t, Class<T> object) throws
            InstantiationException,
            IllegalAccessException,
            NoSuchElementException,
            NoSuchMethodException,
            InvocationTargetException {
        T instance = object.newInstance();

        for (Map.Entry<String, String> x : t.entrySet()) {
            Class instanceClass = instance.getClass();
            Method method = instanceClass.getMethod("set" +
                    toCamelCase(x.getKey()), String.class);
            method.invoke(instance, x.getValue());
        }

        return instance;
    }

    private String toCamelCase(String s) {
        return s.transform(it -> {
            String first = it.substring(0, 1).toUpperCase(Locale.ROOT);
            return first.concat(it.substring(1));
        });
    }
}
