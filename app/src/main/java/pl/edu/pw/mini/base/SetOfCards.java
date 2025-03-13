package pl.edu.pw.mini.base;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class SetOfCards implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public String name;
    public List<Flashcard> setOfFlashcards;
    public LocalDateTime creationDate;

    public SetOfCards(List<Flashcard> setOfCards, String name) {
        this.setOfFlashcards = setOfCards;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Zestaw o nazwie: " + name ;
    }
}
