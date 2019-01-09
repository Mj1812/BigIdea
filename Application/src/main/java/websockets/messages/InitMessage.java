package websockets.messages;

import enums.MessageType;
import websockets.messages.Message;

public class InitMessage extends Message {
    //region Fields
    private int userId;
    private String username;
    //endregion

    //region Constructors
    public InitMessage(int userId, String username, MessageType type) {
        super(type);
        this.userId = userId;
        this.username = username;
    }
    //endregion

    //region Properties
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //endregion
}
