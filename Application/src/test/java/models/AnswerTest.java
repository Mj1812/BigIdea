package models;

import domain.Answer;
import enums.AnswerState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnswerTest {
    @Test
    public void testAnswer() {
        Answer answer = new Answer(1, 1, "yes", true );
        assertEquals(1, answer.getAnswerId());
        assertEquals(1, answer.getQuestionId());
        assertEquals("yes", answer.getAnswer());
        assertEquals(true, answer.isCorrect());
        answer.setQuestionId(2);
        assertEquals(2, answer.getQuestionId());
    }
}
