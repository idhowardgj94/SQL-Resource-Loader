package core.loader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SqlLoader {
    private BufferedReader reader;
    private Map<String, String> p;
    public SqlLoader(String filename) {
        try {
            InputStream inputStream =  getClass().getClassLoader().getResourceAsStream(filename);
            reader = new BufferedReader(new InputStreamReader(inputStream));
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    public Map<String, String> parse() {
        p = new HashMap<>();

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
