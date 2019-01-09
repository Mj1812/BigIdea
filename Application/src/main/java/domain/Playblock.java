package domain;

import enums.AnswerState;

import java.util.ArrayList;
import java.util.List;

public class Playblock {
    private ArrayList<Question> questions = new ArrayList<Question>();
    private int count = 0;

    public Playblock(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public AnswerState answerQuestion(Answer answer, Question answeredQuestion) {
        int index = -1;
        for (Question question:this.questions) {
            if (question.getQuestion().equals(answeredQuestion.getQuestion())) {
                index = this.questions.indexOf(question);
            }
        }
        Question question = this.questions.get(index);
        AnswerState state = AnswerState.UNANSWERED;
        if(answeredQuestion.getState() == AnswerState.UNANSWERED) {
            if (answer.isCorrect()) {
                if (question.getQuestion().equals(answeredQuestion.getQuestion())) {
                    answeredQuestion.setState(AnswerState.CORRECT);
                    count++;
                }
            } else if (!answer.isCorrect()) {
                if (question.getQuestion().equals(answeredQuestion.getQuestion()))
                    answeredQuestion.setState(AnswerState.INCORRECT);
                count++;
            }
            System.out.println(count);
            if (count == 5) {
                state = AnswerState.ALLANSWERED;
            } else if (answer.isCorrect()) {
                state = AnswerState.CORRECT;
            } else if (!answer.isCorrect()) {
                state = AnswerState.INCORRECT;
            } else {
                state = state.UNANSWERED;
            }
        }
        return state;
    }
}
