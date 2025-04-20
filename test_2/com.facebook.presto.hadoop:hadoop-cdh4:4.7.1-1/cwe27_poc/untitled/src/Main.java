import com.facebook.presto.hadoop.shaded.org.jets3t.service.multithread.DownloadPackage;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        DownloadPackage pkg = new DownloadPackage(null, new File("../new_dir/injection.txt"));
        try {
            pkg.getOutputStream();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}