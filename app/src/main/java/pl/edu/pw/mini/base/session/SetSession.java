package pl.edu.pw.mini.base.session;


import pl.edu.pw.mini.base.Flashcard;
import pl.edu.pw.mini.base.SetOfCards;
import pl.edu.pw.mini.base.manager.SetManager;

import java.util.ArrayList;
import java.util.List;

public class SetSession extends Session{
    private static SetSession instance;

    private List<Flashcard> currentFlashcardList;
    private SetOfCards currentSet;

    private SetManager setManager;

    private SetSession(){
        super();
        this.setManager = new SetManager();
        this.currentFlashcardList = new ArrayList<>();
    }

    public static SetSession getInstance(){
        if(instance == null){
            instance = new SetSession();
        }
        return instance;
    }
    /*public void setCurrentSet(){
        this.currentFlashcardList.clear();
    }*/

    public List<Flashcard> getCurrentFlashcardList(){
        return currentFlashcardList;
    }
    public SetOfCards getCurrentSet(){
        return currentSet;
    }

    public boolean addSet(String name) {
        if (setManager.addSetToUser(name, currentFlashcardList, UserSession.getInstance().username)) {
            currentFlashcardList = new ArrayList<>();
            return true;
        }
        return false;
    }
    public boolean addFlashcard(String word, String definition){
        Flashcard flashcard = setManager.checkFlashcard(word, definition);
        if(flashcard == null){
            return false;
        }
        currentFlashcardList.add(flashcard);
        return true;
    }

    public List<SetOfCards> setsOfcurrentUser(){
        return setManager.findAllSets(username);
    }

    public boolean removeSet(String name){
        if(setManager.removeSetFromUser(name, UserSession.getInstance().username)){
            return true;
        }
        return false;
    }

    public boolean setCurrentSet(String name){
        SetOfCards set = setManager.findSet(name, UserSession.getInstance().username);
        if(set != null){
            currentSet = set;
            currentFlashcardList = set.setOfFlashcards;
            return true;
        }
        return false;
    }
    public boolean removeFlashcardFromSet(String setName, int number){
        return setManager.removeFlashcardFromSet(setName, number, UserSession.getInstance().username);
    }
}
