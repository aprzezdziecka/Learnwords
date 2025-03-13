package pl.edu.pw.mini.ui.loginUI.addSet;

import pl.edu.pw.mini.loadingFiles.LoadFlashcardTXT;

import javax.swing.*;
import java.io.File;

public class AddSetTXTPanel extends AddSetPanel {
    public AddSetTXTPanel() {
        super();
    }

    @Override
    protected void handleFileUpload() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Wybierz plik TXT do importu");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Pliki TXT", "txt"));

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String setName = nameField.getText().trim();
            if (setName.isEmpty()) {
                setName = selectedFile.getName();
            }
            if(LoadFlashcardTXT.loadFlashcardTXT(selectedFile, setName)){
                statusLabel.setText("Plik załadowany pomyślnie!");
            }else {
                statusLabel.setText("Błąd podczas ładowania pliku.");
            }
        }
    }
}
