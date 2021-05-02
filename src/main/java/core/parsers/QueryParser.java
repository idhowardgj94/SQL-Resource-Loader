package core.parsers;

import core.rules.IRule;
import core.rules.TrimColon;
import core.rules.TrimComment;
import core.rules.TrimSpace;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

public class QueryParser implements IParse {
    @Override
    public Optional<String> parse(BufferedReader reader) throws IOException {
        String l;
        StringBuilder res = new StringBuilder();
        IRule trimColon = new TrimColon();
        IRule trimComment = new TrimComment();
        IRule trimSpace = new TrimSpace();
        l = reader.readLine();
        while(l != null && l.charAt(l.length() - 1) != ';') {
            res.append(trimColon.run(
                    trimComment.run(
                            trimSpace.run(l)
                    )));
            l = reader.readLine();
        }
        if(l == null) {
            return Optional.empty();
        } else {
            return Optional.of(res.append(trimColon.run(
                    trimComment.run(
                            trimSpace.run(l)
                    ))).toString());
        }
    }
}
