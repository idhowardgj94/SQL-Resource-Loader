package core.rules;

public class TrimColon implements IRule{
    @Override
    public String run(String s) {
        return s.equals("") ? s : s
                .charAt(s.length() - 1) == ';' ? s.substring(0, s.length() - 1) : s.trim();
    }
}
