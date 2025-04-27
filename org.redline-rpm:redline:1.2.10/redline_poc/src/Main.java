import org.redline_rpm.IntString;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        MyBuilder builder = new MyBuilder();
        try {
            builder.addTrigger(new File("../secret_file.txt"), null, new HashMap<>() {
            }, 0);
            for (String script: builder.getScripts()) {
                System.out.println(script);
            }
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}