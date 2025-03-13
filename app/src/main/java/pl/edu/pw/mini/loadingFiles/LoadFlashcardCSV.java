package pl.edu.pw.mini.loadingFiles;


import pl.edu.pw.mini.base.session.SetSession;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadFlashcardCSV {
    public static boolean loadFlashcardCSV(File file, String fileName) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(file.getPath()), java.nio.charset.StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(";");
                if (columns.length == 2) {
                    String word = columns[0].trim();
                    String definition = columns[1].trim();
                    SetSession.getInstance().addFlashcard(word, definition);
                }
            }
            return SetSession.getInstance().addSet(fileName);
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
