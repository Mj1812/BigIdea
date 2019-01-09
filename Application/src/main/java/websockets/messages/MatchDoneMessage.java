package websockets.messages;

import domain.Question;
import enums.AnswerState;
import enums.MessageType;

public class MatchDoneMessage extends Message {
    //region Fields
    private AnswerState answerState;
    private int playerId;
    //endregion

    //region Constructors
    public MatchDoneMessage(MessageType type, AnswerState answerState, int playerId) {
        super(type);
        this.answerState = answerState;
        this.playerId = playerId;
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
    //endregion
}
