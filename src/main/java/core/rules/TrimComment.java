package core.rules;

public class TrimComment implements IRule{
    @Override
    public String run(String s) {
        String[] ss = s.split("\\t+|\\s+|\\n|\\r\\n");
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while(i < ss.length) {
            String it = ss[i];
            if(it.contains("--")) {
                break;
            } else {
                sb.append(" " + it);
            }
            i = i + 1;
        }
        return sb.toString().trim();
    }
}
