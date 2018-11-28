package domain;

public class Player {
    //region Fields
    private String username;
    private double score = 0;
    //endregion

    //region Constructors
    public Player(String username) {
        this.username = username;
    }

    public Player(String username, double score) {
        this.username = username;
        this.score = score;
    }
    //endregion

    //region Properties
    public String getUsername() {
        return username;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    //endregion
}
