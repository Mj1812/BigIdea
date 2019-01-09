package websockets;

import domain.Match;
import domain.Player;

public class MatchPlayers {
    //region Fields
    private Match match;
    private Player player;
    private Player opponent;
    //endregion

    //region Constructors
    public MatchPlayers(Match match, Player player, Player opponent) {
        this.match = match;
        this.player = player;
        this.opponent = opponent;
    }
    //endregion

    //region Properties
    public Match getMatch() {
        return match;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getOpponent() {
        return opponent;
    }
//endregion
}
