package core.util;

import core.loader.stub.TestStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectMapperTest {
    @Test
    public void testMapper() {
        IMapper mapper = new ObjectMapper();
        try {
            TestStub res = mapper.mapping(TestStub.class);
            assertTrue(res.successCreate());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}