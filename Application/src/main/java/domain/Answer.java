package domain;

public class Answer {
    //region Fields
    private int answerId;
    private String answer;
    private boolean correct;
    //endregion

    //region Constructor
    public Answer(int answerId, String answer, boolean correct) {
        this.answerId = answerId;
        this.answer = answer;
        this.correct = correct;
    }
    //endregion

    //region Properties
    public int getAnswerId() {
        return answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return correct;
    }
    //endregion
}
