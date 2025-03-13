package pl.edu.pw.mini.loadingFiles;

import pl.edu.pw.mini.base.session.SetSession;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoadFlashcardTXT {
    public static boolean loadFlashcardTXT(File file, String setName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int addedCount = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    String word = parts[0].trim();
                    String definition = parts[1].trim();
                    if (SetSession.getInstance().addFlashcard(word, definition)) {
                        addedCount++;
                    }
                }
            }
            if (SetSession.getInstance().addSet(setName)){
                return addedCount > 0;
            }
            else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
