package websockets.messages;

import enums.MessageType;
import websockets.messages.Message;

public class MatchMadeMessage extends  Message {
    //region Fields
    private int opponentId;
    private String opponentName;
    //endregion

    //region Constructors
    public MatchMadeMessage(int opponentId, String opponentName, MessageType type) {
        super(type);
        this.opponentId = opponentId;
        this.opponentName = opponentName;
    }
    //endregion

    //region Properties
    public int getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(int opponentId) {
        this.opponentId = opponentId;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }
    //endregion
}
