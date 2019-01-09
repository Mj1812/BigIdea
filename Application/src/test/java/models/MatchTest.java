package models;

import domain.Match;
import domain.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

public class MatchTest {
    private Player playerOne;
    private Player playerTwo;

    @Before
    public void init() {
        playerOne = new Player(1, "Marco");
        playerTwo = new Player(2, "Britt");
    }

    @Test
    public void testMatch() {
        Match match = new Match(playerOne,playerTwo);
        Assert.assertEquals(1, match.getPlayerOne().getId());
        Assert.assertEquals(2, match.getPlayerTwo().getId());
        Assert.assertEquals("Marco", match.getPlayerOne().getUsername());
        Assert.assertEquals("Britt", match.getPlayerTwo().getUsername());
    }
}
