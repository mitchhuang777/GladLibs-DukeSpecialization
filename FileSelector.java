import java.io.File;
import javax.swing.JFileChooser;

public class FileSelector {
    /* SelectedFiles */
    public static File[] selectedFiles() {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFiles();
        }
        else{
            return new File[]{null};
        }
    }
}
