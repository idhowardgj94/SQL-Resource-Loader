package core.util;

public interface IMapper {
    <T> T mapping(Class<T> object) throws InstantiationException, IllegalAccessException;
}
