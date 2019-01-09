package websockets.messages;

import domain.Question;
import enums.MessageType;

import java.util.ArrayList;
import java.util.List;

public class PlayerReadyMessage extends Message {
    //region Fields
    private ArrayList<Question> questions;
    //endregion

    //region Constructors
    public PlayerReadyMessage(MessageType type, ArrayList<Question> questions) {
        super(type);
        this.questions = questions;
    }
    //endregion

    //region Properties
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setShips(ArrayList<Question> questions) {
        this.questions = questions;
    }
    //endregion
}
