package websockets;

import com.google.gson.Gson;
import domain.Match;
import domain.Playblock;
import domain.Player;
import domain.Question;
import enums.AnswerState;
import enums.MessageType;
import websockets.messages.*;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ServerEndpoint(value = "/gameserver/")
public class ServerSocket {
    private static HashSet<Session> sessions = new HashSet<>();
    private static ArrayList<Player> lobby = new ArrayList<>();
    private static ArrayList<Match> matches = new ArrayList<Match>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    private static Gson gson = new Gson();

    @OnOpen
    public void onUserConnect(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onUserDisconnect(CloseReason reason, Session session) {
        for(int i = 0; i < lobby.size(); i++) {
            if(lobby.get(i).getSession() == session) {
                lobby.remove(i);
                i = lobby.size();
            }
        }
        sessions.remove(session);
    }

    @OnMessage
    public void onMessageReceived(String message, Session session) {
        Message mess = gson.fromJson(message, Message.class);
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                if(mess.getMessageType().equals(MessageType.INITIALIZE)) {
                    initPlayer(gson.fromJson(message, InitMessage.class), session);
                } else if(mess.getMessageType().equals(MessageType.ANSWER)) {
                    playerAnswered(gson.fromJson(message, AnsweredMessage.class), session);
                } else if(mess.getMessageType().equals(MessageType.PLAYERREADY)){
                    playerReady(gson.fromJson(message, PlayerReadyMessage.class), session);
                }
            }
        });
    }

    @OnError
    public void onError(Throwable cause, Session session) {

    }

    private synchronized void initPlayer(InitMessage initMessage, Session session) {
        Player player = new Player(session, initMessage.getUserId(), initMessage.getUsername());
        lobby.add(player);
        System.out.println("Players in lobby: " + lobby.size());
        System.out.println("Player " + player.getUsername() + " has joined the queue. (" + session.getId() + ")");

        Player opponent = null;

        for(int i = 0; i < lobby.size() - 1; i++) {
            if(!lobby.get(i).getUsername().equals(player.getUsername())) {
                opponent = lobby.get(i);
                i = lobby.size();
            }
        }

        if(opponent != null) {
            matches.add(new Match(opponent, player));
            lobby.remove(opponent);
            lobby.remove(player);

            Gson gson = new Gson();
            try {
                opponent.getSession().getBasicRemote().sendText(gson.toJson(new MatchMadeMessage(player.getId(), player.getUsername(), MessageType.MATCHMADE)));
                player.getSession().getBasicRemote().sendText(gson.toJson(new MatchMadeMessage(opponent.getId(), opponent.getUsername(), MessageType.MATCHMADE)));
            } catch (Exception e) {

            }
        }
    }

    private MatchPlayers getMatchPlayers(Session session) {
        MatchPlayers matchPlayers = null;
        for(Match match : matches) {
            if (match.getPlayerOne().getSession() == session) {
                matchPlayers = new MatchPlayers(match, match.getPlayerOne(), match.getPlayerTwo());
            } else if (match.getPlayerTwo().getSession() == session) {
                matchPlayers = new MatchPlayers(match, match.getPlayerTwo(), match.getPlayerOne());
            }
        }

        return matchPlayers;
    }

    private void playerReady(PlayerReadyMessage playerReadyMessage, Session session) {
        MatchPlayers matchPlayers = this.getMatchPlayers(session);
        matchPlayers.getPlayer().setPlayerBlock(new Playblock(playerReadyMessage.getQuestions()));
        if (matchPlayers != null) {
            if (matchPlayers.getPlayer().getPlayerBlock().getQuestions().size() == 5 && matchPlayers.getOpponent().getPlayerBlock().getQuestions().size() == 5) {
                Random random = new Random();
                int startingPlayer = random.nextInt() % 2;
                if (startingPlayer == 0) {
                    startingPlayer = matchPlayers.getPlayer().getId();
                } else {
                    startingPlayer = matchPlayers.getOpponent().getId();
                }

                Gson gson = new Gson();
                StartGameMessage startGamePackage = new StartGameMessage(MessageType.STARTGAME, startingPlayer);
                String message = gson.toJson(startGamePackage);
                try {
                    matchPlayers.getPlayer().getSession().getBasicRemote().sendText(message);
                    matchPlayers.getOpponent().getSession().getBasicRemote().sendText(message);
                } catch (Exception e) {

                }
            }
        }
    }

    private void playerAnswered(AnsweredMessage answerMessage, Session session) {
        MatchPlayers matchPlayers = this.getMatchPlayers(session);
        Match finishedMatch = null;

        if (matchPlayers != null) {
            AnswerState state = matchPlayers.getPlayer().getPlayerBlock().answerQuestion(answerMessage.getAnswer(), answerMessage.getQuestion());
            AnswerResultMessage answerResultMessage = new AnswerResultMessage(MessageType.ANSWERRESULT, state, matchPlayers.getPlayer().getId(), answerMessage.getQuestion());
            Gson gson = new Gson();
            String message = gson.toJson(answerResultMessage);
            try {
                matchPlayers.getPlayer().getSession().getBasicRemote().sendText(message);
                matchPlayers.getOpponent().getSession().getBasicRemote().sendText(message);

                if (state.equals(AnswerState.ALLANSWERED)) {
                    MatchDoneMessage matchDoneMessage = new MatchDoneMessage(MessageType.DONE, AnswerState.ALLANSWERED, matchPlayers.getPlayer().getId());
                        finishedMatch = matchPlayers.getMatch();
                    String message2 = gson.toJson(matchDoneMessage);
                    matchPlayers.getPlayer().getSession().getBasicRemote().sendText(message2);
                    matchPlayers.getOpponent().getSession().getBasicRemote().sendText(message2);
                }
            } catch (Exception e) {

            }
        }

        if (finishedMatch != null) {
            matches.remove(matchPlayers.getMatch());
                    }
    }
}
