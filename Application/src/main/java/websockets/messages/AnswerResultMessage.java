package websockets.messages;

import domain.Question;
import enums.AnswerState;
import enums.MessageType;

public class AnswerResultMessage extends Message{
    //region Fields
    private AnswerState answerState;
    private int playerId;
    private Question question;
    //endregion

    //region Constructors
    public AnswerResultMessage(MessageType type, AnswerState answerState, int playerId, Question question) {
        super(type);
        this.answerState = answerState;
        this.playerId = playerId;
        this.question = question;
    }
    //endregion

    //region Properties
    public AnswerState getAnswerState() {
        return answerState;
    }

    public void setAnswerState(AnswerState answerState) {
        this.answerState = answerState;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestionResult(Question coordinate) {
        this.question = coordinate;
    }
    //endregion
}
