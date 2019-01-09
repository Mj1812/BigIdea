package quizgame;

import GUI.IQuizGameApplication;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import data.mssqlcontexts.QuizQuestionMSSQLContext;
import domain.*;
import enums.AnswerState;
import enums.MessageType;
import logic.repositories.QuestionRepository;
import logic.repositories.helperclasses.PasswordHasher;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.util.component.LifeCycle;
import restapi.RestContext;
import restapi.responses.AuthenticationResponse;
import restapi.responses.QuestionResponse;
import restapi.viewmodels.Login;
import restapi.viewmodels.Register;
import websockets.ClientSocket;
import websockets.messages.*;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class QuizGame implements IQuizGame, Observer {
    private Account account;
    private Playblock playBlock;
    protected Session session;
    protected WebSocketContainer container;
    protected ClientSocket clientSocket;
    private Player player;
    private int currentButtonNr = 0;
    private int count = 0;
    private RestContext restContext = new RestContext();
    private PasswordHasher hasher = new PasswordHasher();

    public QuizGame() {
        this.container = ContainerProvider.getWebSocketContainer();
        try {
            URI uri = URI.create("ws://localhost:8095/gameserver/");
            this.clientSocket = new ClientSocket();
            this.clientSocket.addObserver(this);
            this.session = this.container.connectToServer(this.clientSocket, uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Account login(String name, String password, IQuizGameApplication application) {
        if (name == null || name.length() == 0 || password == null || password.length() == 0 || application == null) {
            throw new IllegalArgumentException();
        }
        else {
            try {
                int id = -1;
                Login login = new Login(name, password);
                AuthenticationResponse authenticationResponse = restContext.loginUserRest(login);
                if (authenticationResponse.getUserId() == -1) {
                    application.showErrorMessage(0, authenticationResponse.getMessage());
                } else {
                    id = authenticationResponse.getUserId();
                }
                if (id > 0) {
                    this.account = new Account(id, name, application);
                    application.showErrorMessage(this.account.getId(), "Searching for opponent!");
                    Gson gson = new Gson();
                    InitMessage initMessage = new InitMessage(this.account.getId(), this.account.getName(), MessageType.INITIALIZE);
                    String message = gson.toJson(initMessage);
                    this.session.getBasicRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.account;
    }

    public Account register(String name, String password, String confirmPassword, IQuizGameApplication application) {
        if (name == null || name.length() == 0 || password == null || password.length() == 0 || application == null) {
            throw new IllegalArgumentException();
        }
        else {
            try {
                    int id = -1;
                    Register register = new Register(name, password, confirmPassword);
                    AuthenticationResponse authenticationResponse = restContext.registerUserRest(register);
                    if (authenticationResponse.getUserId() == -1) {
                        application.showErrorMessage(0, authenticationResponse.getMessage());
                    } else {
                        id = authenticationResponse.getUserId();
                    }
                    if (id > 0) {
                        this.account = new Account(id, name, application);
                        application.showErrorMessage(this.account.getId(), "Searching for opponent!");
                        Gson gson = new Gson();
                        InitMessage initMessage = new InitMessage(this.account.getId(), this.account.getName(), MessageType.INITIALIZE);
                        String message = gson.toJson(initMessage);
                        this.session.getBasicRemote().sendText(message);
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.account;
    }

    public boolean answerQuestion(int playerNr, int buttonNr, Answer answer, Question question) {
        boolean result = false;
            this.currentButtonNr = buttonNr;
            if(question.getState().equals(AnswerState.UNANSWERED)) {
                AnsweredMessage answeredMessage = new AnsweredMessage(MessageType.ANSWER, question, answer);
                Gson gson = new Gson();
                String message = gson.toJson(answeredMessage);
                try {
                    this.session.getBasicRemote().sendText(message);
                    result = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return result;
    }

    public void getQuestions(){
        try {
            QuestionResponse questionResponse = restContext.executeQueryGet();
            if (questionResponse.getQuestions().size() == 0) {
                this.account.getApplication().showErrorMessage(0, questionResponse.getMessage());
            } else {
                this.playBlock = new Playblock(questionResponse.getQuestions());
                this.player = new Player(this.playBlock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Question getQuestion(int questionNr){
            return this.playBlock.getQuestions().get(questionNr);
    }


    public boolean notifyWhenReady(int playerNr) {
        boolean result = false;
        if (this.playBlock.getQuestions().size() == 5) {
            result = true;
            if(this.account.getId() == 0) {
                this.account.getApplication().notifyStartGame(this.account.getId(), this.account.getName());
            } else {
                PlayerReadyMessage playerReadyPackage = new PlayerReadyMessage(MessageType.PLAYERREADY, player.getPlayerBlock().getQuestions());
                Gson gson = new Gson();
                try {
                    this.session.getBasicRemote().sendText(gson.toJson(playerReadyPackage));
                } catch (Exception e) {

                }
            }
        }

        return result;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof MatchMadeMessage) {
            this.setupMatch((MatchMadeMessage) arg);
        } else if (arg instanceof StartGameMessage) {
            this.startGame((StartGameMessage) arg);
        } else if (arg instanceof AnswerResultMessage){
            this.answerResult((AnswerResultMessage)arg);
        }
        //else if (arg instanceof MatchDoneMessage) {
          //  this.matchDone((MatchDoneMessage) arg);
        //}
    }

    private void answerResult(AnswerResultMessage answerResultMessage) {
        AnswerState state = answerResultMessage.getAnswerState();
        if(answerResultMessage.getPlayerId() == this.account.getId()) {
            if (state == AnswerState.CORRECT) {
                this.account.getApplication().showAnswer(this.account.getId(), currentButtonNr ,AnswerState.CORRECT );
            } else if (state == AnswerState.INCORRECT) {
                this.account.getApplication().showAnswer(this.account.getId(), currentButtonNr, AnswerState.INCORRECT);
            }
            this.account.getApplication().playerAnswers(this.account.getId(), state);
        } else {
            if (state == AnswerState.CORRECT) {
                this.account.getApplication().showAnswerOpponent(this.account.getId(), AnswerState.CORRECT, count);
            } else if (state == AnswerState.INCORRECT ) {
                this.account.getApplication().showAnswerOpponent(this.account.getId(), AnswerState.INCORRECT, count);
            } else if(state ==  AnswerState.ALLANSWERED){
                this.account.getApplication().showEndMatchOpponent(this.account.getId());
            }
            this.account.getApplication().opponentAnswers(this.account.getApplication().getOpponentNr(), state);
            this.count = count + 1;
        }
    }

    private void setupMatch(MatchMadeMessage matchMadePackage) {
        IQuizGameApplication application = this.account.getApplication();
        application.setPlayerNumber(this.account.getId(), this.account.getName());
        application.setOpponentName(matchMadePackage.getOpponentId(), matchMadePackage.getOpponentName());
    }

    //private void matchDone(MatchDoneMessage matchDoneMessage){
    //if(matchDoneMessage.getPlayerId() == this.account.getId()){
      //  this.account.getApplication().showEndMatchOpponent(this.account.getId());
    //}else{
      //  this.account.getApplication().showEndMatchOpponent(this.account.getId());
    //}
    //}

    private void startGame(StartGameMessage startGameMessage) {
        String name = "";


        if(startGameMessage.getStartingPlayer() == this.account.getId()) {
            name = this.account.getName();
        } else {
            name = this.account.getApplication().getOpponentName();
        }

        this.account.getApplication().setPlayerTurn(startGameMessage.getStartingPlayer());
        this.account.getApplication().notifyStartGame(this.account.getId(), name);
    }


}
