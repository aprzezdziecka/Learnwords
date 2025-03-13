package pl.edu.pw.mini.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private final String name;
    private final String password;
    private List<SetOfCards> collection;
/*

    private Credential userCredential;
*/

    public User(String name, String password) {
        this.password = password;
        this.name = name;
        this.collection = new ArrayList<>();

    }
/*
    public User(String name, String password, List<SetOfCards> collection) {
        this.name = name;
        this.password = password;
        this.collection = collection;
    }*/

    public String getName() {
        return name;
    }

    public List<SetOfCards> getCollection() {
        return collection;
    }

   /* public void setCollection(List<SetOfCards> collection) {
        this.collection = collection;
    }*/

    public String getPassword() {
        return password;
    }
    public void addSet(SetOfCards set){
        collection.add(set);
    }

    /*public void authenticateGoogle() {
        try {
            this.userCredential = GoogleAuth.authorize();
        } catch (IOException e) {
            System.err.println("Błąd autoryzacji: " + e.getMessage());
        }
    }*/
}
