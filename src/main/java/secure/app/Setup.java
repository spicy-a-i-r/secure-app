package secure.app;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.File;
import java.io.IOException;



public class Setup {
    

    // search system to see if the file folder is downloaded
    public static String getFileFolder() {

        // initalize bool to see if file is found
        Boolean folder = false;
        
        while (!folder) {
            String startingDir = System.getProperty("user.home");
            File[] files;
            System.out.println(startingDir);

            File start = new File(startingDir);
            files = start.listFiles();
            System.out.println(files);

            for (File f : files) {
                if (f.isDirectory()) {
                    if (f.getName().equals("processed")) {
                        folder = true;
                        return f.getAbsolutePath();
                    }
                } 
            }
            

            File processed = new File(startingDir + "\\processed");

            if (processed.mkdir()){
                System.out.println("successfully made processed directory");
                folder = true;
                return processed.getAbsolutePath();
            } else { System.out.println("Error creating folder"); }

            
        }
        System.out.println("error getting file folder");
        return null;
    }


    // return the output path for the encryption
}