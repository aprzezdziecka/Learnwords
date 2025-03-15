package pl.edu.pw.mini.base;


import static org.junit.jupiter.api.Assertions.*;

class FlashcardTest {

    @org.junit.jupiter.api.Test
    void resetStatsEquals(){
        Flashcard flashcard1 = new Flashcard("rope", "lina");
        flashcard1.incrementCorrect();
        flashcard1.incrementIncorrect();

        flashcard1.resetStats();

        assertEquals(1, flashcard1.correctAnswers);
        assertEquals(1, flashcard1.incorrectAnswers);
        assertEquals(0, flashcard1.lastCorrectAnswers);
        assertEquals(0, flashcard1.lastIncorrectAnswers);
    }
    @org.junit.jupiter.api.Test
    void incrementCorrectEquals(){
        Flashcard flashcard2 = new Flashcard("tired", "zmęczony");
        flashcard2.incrementCorrect();
        flashcard2.incrementCorrect();

        assertEquals(2, flashcard2.correctAnswers);
        assertEquals(2, flashcard2.lastCorrectAnswers);
        assertEquals(0, flashcard2.incorrectAnswers);
        assertEquals(0, flashcard2.lastIncorrectAnswers);
    }

}