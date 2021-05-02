package core.parsers;

import core.predicates.IPredicate;
import core.predicates.IsComment;
import core.rules.IRule;
import core.rules.TrimComment;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

public class FieldNameParser implements IParse {
    private IPredicate isComment = new IsComment();
    private IRule trimComment = new TrimComment();
    @Override
    public Optional<String> parse(BufferedReader reader) throws IOException {
        String l = reader.readLine();
        while(l != null) {
            while(l.length() == 0) l = reader.readLine();

            if(isComment.predicate(l)) {
                if(l.trim().startsWith("--@") | l.trim().startsWith("-- @")) {
                    return Optional.of(l.substring(l.indexOf("@") + 1));
                }
            } else {
                throw new IOException("format error");
            }
            l = reader.readLine();
        }
        return Optional.empty();
    }
}
