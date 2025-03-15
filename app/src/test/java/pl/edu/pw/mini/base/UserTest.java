package pl.edu.pw.mini.base;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class UserTest {

    @org.junit.jupiter.api.Test
    void addSetEquals() {
        User user1 = new User("Karolina", "haslo123");
        Flashcard flash1 = new Flashcard("cat", "kot");
        Flashcard flash2 = new Flashcard("dog", "pies");
        List<Flashcard> listofcards1 = new ArrayList<>();
        listofcards1.add(flash1);
        listofcards1.add(flash2);
        SetOfCards set1 = new SetOfCards(listofcards1, "Animals");

        user1.addSet(set1);

        assertEquals(1, user1.getCollection().size());
    }

    @org.junit.jupiter.api.Test
    void addSetifTrue(){
        User user2 = new User("Karol", "123haslo1");
        Flashcard flash1 = new Flashcard("apple", "jabłko");
        Flashcard flash2 = new Flashcard("grape", "winogrono");
        List<Flashcard> listofcards2 = new ArrayList<>();
        listofcards2.add(flash1);
        listofcards2.add(flash2);
        SetOfCards set2 = new SetOfCards(listofcards2, "Animals");

        user2.addSet(set2);

        assertTrue(user2.getCollection().contains(set2));
    }
}