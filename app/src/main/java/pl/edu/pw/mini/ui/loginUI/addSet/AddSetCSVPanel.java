package pl.edu.pw.mini.ui.loginUI.addSet;

import pl.edu.pw.mini.loadingFiles.LoadFlashcardCSV;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class AddSetCSVPanel extends AddSetPanel {
    public AddSetCSVPanel() {
        super();
    }
    protected void handleFileUpload() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Wybierz plik CSV");
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String setName = nameField.getText().trim();
            if (setName.isEmpty()) {
                setName = file.getName();
            }
            if (LoadFlashcardCSV.loadFlashcardCSV(file, setName)) {
                statusLabel.setText("Plik załadowany pomyślnie!");
            } else {
                statusLabel.setText("Błąd podczas ładowania pliku.");
            }
        }
    }
}
