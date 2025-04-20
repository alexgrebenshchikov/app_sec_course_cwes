import com.mongodb.gridfs.GridFSDBFile;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        GridFSDBFile file = new GridFSDBFile();
        try {
            file.writeTo(new File("../injection.txt"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}