package core.loader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqlLoaderTest {

    @Test
    public void testSqlLoader() {
        SqlLoader loader = new SqlLoader("group/example.sql");
        loader.print();
    }
}