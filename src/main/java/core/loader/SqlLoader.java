package core.loader;

import core.predicates.IsComment;
import core.rules.IRule;
import core.rules.TrimColon;
import core.rules.TrimComment;
import core.rules.TrimSpace;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SqlLoader {
    private BufferedReader reader;
    private String filename;
    private String mode = "BY_NAME";
    private IRule trimColon = new TrimColon();
    private IRule trimComment = new TrimComment();
    private IRule trimSpace = new TrimSpace();

    public SqlLoader(String filename) {
        this.filename = filename;
        InputStream inputStream =  getClass().getClassLoader().getResourceAsStream(filename);
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public Map<String, String> parse() throws IOException {
        Map<String, String> p = new HashMap<>();
        reader.mark(0);
        if(mode.equals("BY_NAME")) {
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
