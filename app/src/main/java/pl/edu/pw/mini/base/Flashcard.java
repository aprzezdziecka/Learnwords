package pl.edu.pw.mini.base;

import java.io.Serial;
import java.io.Serializable;

public class Flashcard implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public String word;
    public String definition;
    public int correctAnswers;
    public int incorrectAnswers;
    public int lastCorrectAnswers;
    public int lastIncorrectAnswers;

    public Flashcard(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    @Override
    public String toString() {
        return  "word='" + word + '\'' +
                ", definition='" + definition + '\'';
    }

    public void incrementCorrect() {
        correctAnswers++;
        lastCorrectAnswers++;
    }
    public void incrementIncorrect() {
        incorrectAnswers++;
        lastIncorrectAnswers++;
    }
    public void resetStats(){
        lastCorrectAnswers = 0;
        lastIncorrectAnswers = 0;
    }
}
