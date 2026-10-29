package secure.app;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.*;


public class Setup {
    

    // search system to see if the file folder is downloaded
    public static String getFileFolder() {

        // initalize bool to see if file is found
        Boolean folder = false;
        
        while (!folder) {
            File startingDir = new File(System.getProperty("user.home"));
            File[] files;
            System.out.println(startingDir);

            
            files = startingDir.listFiles();
            System.out.println(files);

            for (File f : files) {
                if (f.isDirectory()) {
                    if (f.getName().equals("files_processed")) {
                        folder = true;
			System.out.println(f.getAbsolutePath());
                        return f.getAbsolutePath();
                    }
                } 
            }
            

            File processed = new File(startingDir + "/files_processed");

	    try {
		Files.createDirectory(processed.toPath());
		System.out.println("worked, directory created");
	}  catch (IOException e) { e.printStackTrace(); }	

            
        }
        System.out.println("error getting file folder");
        return null;
    }


    // return the output path for the encryption
}
