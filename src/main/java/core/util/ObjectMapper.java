package core.util;

public class ObjectMapper implements IMapper {
    @Override
    public <T> T mapping(Class<T> object) throws InstantiationException, IllegalAccessException {
        T instance = object.newInstance();
        return instance;
    }
}
