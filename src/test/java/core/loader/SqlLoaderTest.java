package core.loader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

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
    public void testMultiLine() {
        SqlLoader loader = new SqlLoader("group/multiline.sql");
        try {
            Map<String, String> res = loader.parse();
            assertEquals("SELECT * FROM hello WHERE a = :id AND b = :name", res.get("multiline"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}