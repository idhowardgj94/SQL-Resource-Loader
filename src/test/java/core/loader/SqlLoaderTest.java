package core.loader;

import core.constant.ReadMethod;
import core.loader.stub.TestStub;
import core.util.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SqlLoaderTest {

    @Test
    public void testSqlLoader() {
        SqlLoader loader = new SqlLoader("group/example.sql");
        loader.print();
    }

    @Test
    public void testParse() {
        SqlLoader loader = new SqlLoader("group/example.sql");
        try {
             Map<String, String> res= loader.parse();
             assertEquals("SELECT * FROM users WHERE id=:id", res.get("example"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParseByAnnotation() {
        SqlLoader loader = new SqlLoader("group/byAnnotation.sql", ReadMethod.BY_ANNOTATION);
        try {
            Map<String, String> res= loader.parse();
            assertEquals("SELECT * FROM user WHERE name=:name", res.get("queryByName"));
            assertEquals("SELECT * from user where id=:id", res.get("queryById"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMultiLine() {
        SqlLoader loader = new SqlLoader("group/multiline.sql");
        try {
            Map<String, String> res = loader.parse();
            assertEquals("SELECT * FROM hello WHERE a = :id AND b = :name", res.get("multiline"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWithObject() {
        SqlLoader loader = new SqlLoader("group/multiline.sql", ReadMethod.BY_NAME);
        try {
            TestStub t = new ObjectMapper().mapping(loader.parse(), TestStub.class);
            assertEquals(t.getMultiline(), "SELECT * FROM hello WHERE a = :id AND b = :name");
        } catch (InstantiationException
                | NoSuchMethodException
                | InvocationTargetException
                | IllegalAccessException
                | IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}