package pl.edu.pw.mini.base.manager;

import java.io.*;
import java.nio.file.*;

public class FileManager {
    private static final String RESOURCE_FILE = "defaultUsers.dat"; // Plik w `resources`
    private static final String WORKING_FILE = System.getProperty("user.home") + File.separator + "users.dat";

    public static void copyFromResources() {
        try (InputStream inputStream = FileManager.class.getClassLoader().getResourceAsStream(RESOURCE_FILE)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Nie znaleziono pliku w resources: " + RESOURCE_FILE);
            }
            Files.copy(inputStream, Paths.get(WORKING_FILE), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Plik skopiowany do katalogu roboczego: " + WORKING_FILE);
        } catch (IOException e) {
            System.err.println("Błąd podczas kopiowania pliku: " + e.getMessage());
        }
    }

    public static boolean isWorkingFileExists() {
        return Files.exists(Paths.get(WORKING_FILE));
    }

    public static String getWorkingFilePath() {
        return WORKING_FILE;
    }
}
