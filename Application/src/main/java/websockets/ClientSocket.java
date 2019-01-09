package websockets;

import com.google.gson.Gson;
import enums.MessageType;
import websockets.messages.*;

import javax.websocket.*;
import java.util.Observable;

@ClientEndpoint
public class ClientSocket extends Observable {
    @OnOpen
    public void onConnect() {

    }

    @OnClose
    public void onDisconnect(CloseReason reason) {

    }

    @OnMessage
    public void onMessage(String message) {
        Gson gson = new Gson();
        Message aMessage = gson.fromJson(message, Message.class);
        if(aMessage.getMessageType().equals(MessageType.MATCHMADE)) {
            aMessage = gson.fromJson(message, MatchMadeMessage.class);
        } else if(aMessage.getMessageType().equals(MessageType.STARTGAME)) {
            aMessage = gson.fromJson(message, StartGameMessage.class);
        } else if(aMessage.getMessageType().equals(MessageType.ANSWERRESULT)) {
            aMessage = gson.fromJson(message, AnswerResultMessage.class);
        }else if(aMessage.getMessageType().equals(MessageType.DONE)) {
            aMessage = gson.fromJson(message, MatchDoneMessage.class);
        }
        this.setChanged();
        this.notifyObservers(aMessage);
    }

    @OnError
    public void onError(Throwable cause) {

    }
}
