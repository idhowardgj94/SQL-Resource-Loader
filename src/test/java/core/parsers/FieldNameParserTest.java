package core.parsers;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FieldNameParserTest {

    @Test
    void parse() {
        try {
            FieldNameParser sut = new FieldNameParser();
            InputStream input = IOUtils
                    .toInputStream("-- @queryByName\n -- dudududud", StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            Optional<String> s =  sut.parse(reader);
            assertEquals("queryByName",s.get());

            input = IOUtils
                    .toInputStream("-- rcohrcoehu\n -- @queryByName", StandardCharsets.UTF_8);
            reader = new BufferedReader(new InputStreamReader(input));
            s =  sut.parse(reader);
            assertEquals("queryByName",s.get());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}