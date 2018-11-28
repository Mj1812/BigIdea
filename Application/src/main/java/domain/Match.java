package domain;

public class Match {
    //region Fields
    private Player playerOne;
    private Player playerTwo;
    //endregion

    //region Constructor
    public Match(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }
    //endregion

    //region Properties
    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
    //endregion
}
