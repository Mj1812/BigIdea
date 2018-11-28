package domain;

import java.util.List;

public class Question {
    //region Fields
    private int questionId;
    private String question;
    private List<Answer> answers;
    //endregion

    //region Constructor
    public Question(int questionId, String question, List<Answer> answers) {
        this.questionId = questionId;
        this.question = question;
        this.answers = answers;
    }
    //endregion

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

}
