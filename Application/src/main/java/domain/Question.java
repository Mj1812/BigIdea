package domain;

import enums.AnswerState;

import java.util.ArrayList;

public class Question {
    //region Fields
    private int questionId;
    private String question;
    private ArrayList<Answer> answers;
    private AnswerState state;
    //endregion

    //region Constructor
    public Question() {

    }

    public Question(int questionId, String question, ArrayList<Answer> answers, AnswerState state) {
        this.questionId = questionId;
        this.question = question;
        this.answers = answers;
        this.state = state;
    }
    //endregion

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public AnswerState getState() {
        return state;
    }

    public void setState(AnswerState state) {
        this.state = state;
    }
}
