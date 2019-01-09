package websockets.messages;

import domain.Answer;
import domain.Question;
import enums.MessageType;
import websockets.messages.Message;

public class AnsweredMessage extends Message {
    //region Fields
    private Question question;
    private Answer answer;
    //endregion

    //region Constructors
    public AnsweredMessage(MessageType type, Question question, Answer answer) {
        super(type);
        this.question = question;
        this.answer = answer;
    }
    //endregion

    //region Properties
    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
    //endregion
}
