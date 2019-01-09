package websockets.messages;

import enums.MessageType;
import websockets.messages.Message;

public class StartGameMessage extends Message {
    //region Fields
    private int startingPlayer;
    //endregion

    //region Constructors
    public StartGameMessage(MessageType type, int startingPlayer) {
        super(type);
        this.startingPlayer = startingPlayer;
    }
    //endregion

    //region Properties
    public int getStartingPlayer() {
        return startingPlayer;
    }
    //endregion
}
