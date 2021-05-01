package core.predicates;

public class IsComment implements IPredicate {
    @Override
    public Boolean predicate(String s) {
        return s.trim().startsWith("--");
    }
}
