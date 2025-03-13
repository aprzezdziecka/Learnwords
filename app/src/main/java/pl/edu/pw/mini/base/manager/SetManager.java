package pl.edu.pw.mini.base.manager;

import pl.edu.pw.mini.base.SetOfCards;
import pl.edu.pw.mini.base.User;
import pl.edu.pw.mini.base.Flashcard;
import java.util.List;

public class SetManager extends Manager {

    public SetOfCards findSet(String name, String username) {
        User user = findUser(username);
        if (user == null) {
            return null;
        }
        return user.getCollection().stream()
                .filter(set -> set.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<SetOfCards> findAllSets(String username) {
        User user = findUser(username);
        return user != null ? user.getCollection() : null;
    }

    public boolean removeSetFromUser(String name, String username) {
        User user = findUser(username);
        if (user == null) {
            return false;
        }
        List<SetOfCards> setsOfCards = user.getCollection();
        boolean removed = setsOfCards.removeIf(set -> set.getName().equals(name));
        if (removed) {
            saveUsers();
        }
        return removed;
    }

    public boolean removeFlashcardFromSet(String setName, int index, String username) {
        User user = findUser(username);
        if (user == null) {
            return false;
        }
        SetOfCards set = findSet(setName, username);
        if (set != null && index >= 0 && index < set.setOfFlashcards.size()) {
            set.setOfFlashcards.remove(index);
            saveUsers();
            return true;
        }
        return false;
    }

    public boolean addSetToUser(String name, List<Flashcard> flashcardSet, String username) {
        User user = findUser(username);
        if (user == null || flashcardSet == null || flashcardSet.isEmpty()) {
            return false;
        }
        if (findSet(name, username) != null) {
            name = "Zestaw nr: " + user.getCollection().size();
        }
        if (findSet(name, username) != null) {
            return false;
        }
        SetOfCards set = new SetOfCards(flashcardSet, name);
        set.creationDate = java.time.LocalDateTime.now();
        user.addSet(set);
        saveUsers();
        return true;
    }

    public Flashcard checkFlashcard(String word, String definition) {
        return (word.isEmpty() || definition.isEmpty()) ? null : new Flashcard(word, definition);
    }
}
