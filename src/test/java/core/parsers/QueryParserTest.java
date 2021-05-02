package core.parsers;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class QueryParserTest {
    @Test
    public void parse() {
        try {
            IParse sut = new QueryParser();
            InputStream input = IOUtils
                    .toInputStream("SELECT * FROM user;", StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            Optional<String> s =  sut.parse(reader);
            assertEquals("SELECT * FROM user",s.get());

            input = IOUtils
                    .toInputStream("select * from user", StandardCharsets.UTF_8);
            reader = new BufferedReader(new InputStreamReader(input));
            s =  sut.parse(reader);
            assertFalse(s.isPresent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}