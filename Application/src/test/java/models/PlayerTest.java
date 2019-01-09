package models;

import domain.Match;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    private domain.Player playerOne;
    private domain.Player playerTwo;

    @Before
    public void init() {
        playerOne = new domain.Player(1, "Marco");
        playerTwo = new domain.Player(2, "Britt");
    }

    @Test
    public void testMatch() {
        Match match = new Match(playerOne,playerTwo);
        Assert.assertEquals(1, playerOne.getId());
        Assert.assertEquals(2, playerTwo.getId());
        Assert.assertEquals("Marco", playerOne.getUsername());
        Assert.assertEquals("Britt", playerTwo.getUsername());
    }
}
