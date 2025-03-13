package pl.edu.pw.mini.base.manager;


import pl.edu.pw.mini.base.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Manager {
    protected static List<User> users;

    static {
        users = loadUsers();
    }

    @SuppressWarnings("unchecked")
    private static List<User> loadUsers(){
        String workingFilePath = FileManager.getWorkingFilePath();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(workingFilePath))) {
            return (List<User>) ois.readObject();
        } catch (EOFException | FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Błąd odczytu użytkowników: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    protected void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FileManager.getWorkingFilePath()))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.err.println("Błąd zapisu użytkowników: " + e.getMessage());
        }
    }
    protected User findUser(String username) {
        for (User user : users) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
