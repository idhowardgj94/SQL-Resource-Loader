package core.rules;

import java.util.Arrays;

public class TrimSpace implements IRule{
    @Override
    public String run(String s) {
        return Arrays
                .stream(s.split("\\t+|\\s+|\\n|\\r\\n"))
                .reduce("", (prev, cur) -> prev.concat(" " + cur) );
    }
}
