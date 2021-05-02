package core.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Optional;

public interface IParse {
    Optional<String> parse(BufferedReader reader) throws IOException;
}
