/* Import necessary libraries */
import java.util.*;
import java.io.File;

public class DirectoryResource {
    public Iterable<File> selectedFiles() {
        File[] files = FileSelector.selectedFiles();
        if(files[0] == null){
            return new ArrayList<File>();
        }
        else{
            return Arrays.asList(files);
        }

    }
}
