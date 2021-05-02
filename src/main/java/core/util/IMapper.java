package core.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface IMapper {
    <T> T mapping(Class<T> object) throws InstantiationException, IllegalAccessException;
    <T> T mapping(Map<String, String> t, Class<T> object) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
