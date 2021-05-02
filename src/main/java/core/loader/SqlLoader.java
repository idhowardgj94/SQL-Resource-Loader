package core.loader;

import core.constant.ReadMethod;
import core.parsers.IParse;
import core.parsers.FieldNameParser;
import core.parsers.QueryParser;
import core.predicates.IsComment;
import core.rules.IRule;
import core.rules.TrimColon;
import core.rules.TrimComment;
import core.rules.TrimSpace;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SqlLoader {
    private BufferedReader reader;
    private String filename;
    private String mode = "BY_NAME";
    private IRule trimColon = new TrimColon();
    private IRule trimComment = new TrimComment();
    private IRule trimSpace = new TrimSpace();
    private IParse fieldNameParser = new FieldNameParser();
    private IParse queryParser = new QueryParser();

    public SqlLoader(String filename) {
        this.filename = filename;
        InputStream inputStream =  getClass().getClassLoader().getResourceAsStream(filename);
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public SqlLoader(String filename, String mode) {
        this(filename);
        this.mode = mode;
    }

    public Map<String, String> parse() throws IOException {
        Map<String, String> p = new HashMap<>();
        reader.mark(0);
        if(mode.equals(ReadMethod.BY_NAME)) {
            String res = reader.lines()
                    .filter(x -> !(new IsComment().predicate(x)))
                    .reduce("", (prev, c) -> prev.concat(" " + trimColon.run(
                            trimComment.run(
                                    trimSpace.run(c)
                            ))))
                    .trim();

            p.put(filename.substring(
                        filename.lastIndexOf("/") + 1,
                        filename.length() - 4),
                    res);
        } else if(mode.equals(ReadMethod.BY_ANNOTATION)) {
            while(true) {
                Optional<String> fieldName = fieldNameParser.parse(reader);
                Optional<String> query = queryParser.parse(reader);
                if(fieldName.isPresent() && query.isPresent())  {
                    p.put(fieldName.get(), query.get());
                } else {
                    break;
                }
            }
        }

        return p;
    }

    public void print() {
        try {
            System.out.println(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
