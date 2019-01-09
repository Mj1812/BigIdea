package domain;

import javax.websocket.Session;

public class Player {
    //region Fields
    private int id;
    private String username;
    private Session session;
    private int score = 0;
    private Playblock enemyBlock;
    private Playblock playerBlock;
    //endregion

    //region Constructors
    public Player(String username) {
        this.username = username;
    }

    public Player(Playblock playerBlock) {
        this.playerBlock = playerBlock;
    }

    public Player(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public Player(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public Player(Session session, int id, String username) {
        this.id = id;
        this.username = username;
        this.session = session;
    }
    //endregion

    //region Properties

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public double getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Session getSession() {
        return session;
    }

    public Playblock getEnemyBlock() {
        return enemyBlock;
    }

    public Playblock getPlayerBlock() {
        return playerBlock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnemyBlock(Playblock enemyBlock) {
        this.enemyBlock = enemyBlock;
    }

    public void setPlayerBlock(Playblock playerBlock) {
        this.playerBlock = playerBlock;
    }
    //endregion
}
