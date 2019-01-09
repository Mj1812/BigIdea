package websockets.messages;

import enums.MessageType;

public class Message {
    //region Fields
    private MessageType type;
    //endregion

    //region Constructors
    public Message(MessageType type) {
        this.type = type;
    }
    //endregion

    //region Properties
    public MessageType getMessageType() {
        return this.type;
    }

    public void setMessageType(MessageType type) {
        this.type = type;
    }
    //endregion
}
