package tech.kushan;/* Author: Kushan Mehta
 * Date: 20-07-2018
 */

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class ValidateUtils {

    // Compress the instance creation as it is a utility class
    private ValidateUtils() {
    }


    private static String folderPath;


    // check if the folder structure and the files within it are properly organized
    public static boolean hasRequiredStructure(String folderPathVar) {

        folderPath = folderPathVar;

        if (hasCorrectFolders()) {

        }

        return true;
    }

    // Check if the main folder has all the sub-folders like code, output, conclusion
    private static boolean hasCorrectFolders() {

        File file = new File("D:\\Java\\Projects\\CHARUSAT\\DocFileAutomationSystem");
        //  File file = new File(folderPath);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        System.out.println(Arrays.toString(directories));

        return true;
    }


}
