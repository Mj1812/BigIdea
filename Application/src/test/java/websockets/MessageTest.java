package websockets;

import enums.MessageType;
import org.junit.Assert;
import org.junit.Test;
import websockets.messages.Message;

public class MessageTest {
    @Test
    public void testMessage() {
        Message message = new Message(MessageType.STARTGAME);

        Assert.assertNotNull(message);
        Assert.assertEquals(MessageType.STARTGAME, message.getMessageType());

        message.setMessageType(MessageType.ANSWER);
        Assert.assertEquals(MessageType.ANSWER, message.getMessageType());
    }
}
