package core.util;

import core.util.stub.TestFieldStub;
import core.util.stub.TestStub;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

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
            fail();
        }
    }

    @Test
    public void testMappingMapper() {
        IMapper mapper = new ObjectMapper();
        try {
            Map<String, String> map = new HashMap<>();
            map.put("name", "howard");
            map.put("password", "123");
            map.put("trytry", "ok");
            TestFieldStub res =  mapper.mapping(map, TestFieldStub.class);
            assertEquals("howard", res.getName());
            assertEquals("123", res.getPassword());
            assertEquals("ok", res.getTrytry());
        } catch(Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}