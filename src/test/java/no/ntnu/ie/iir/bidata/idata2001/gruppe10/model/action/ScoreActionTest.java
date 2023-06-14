package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;
import org.junit.jupiter.api.Test;

/**
 * Test the class ScoreAction.
 * <p>
 * The following positive test are performed:
 * ScoreActionConstructor.
 * Execute.
 * </p>
 * <p>
 * The following negative test are performed:
 * ScoreActionConstructor.
 * Execute.
 * </p>
 */
public class ScoreActionTest {
  @Test
  public void testScoreActionConrstructerWithPositiveParameter() {
    ScoreAction scoreAction = new ScoreAction(10);
    assertEquals(10, scoreAction.getPoints());
  }

  @Test
  public void testScoreActionConstructionWithNegativeParameter() {
    ScoreAction scoreAction = new ScoreAction(-10);
    assertEquals(-10, scoreAction.getPoints());
  }

  @Test
  public void testSetGoldMethodWithPositiveParameter() {
    ScoreAction scoreAction = new ScoreAction(10);
    scoreAction.setPoints(1);
    assertEquals(1, scoreAction.getPoints());
  }

  @Test
  public void testSetGoldMethodWithNegativeParameter() {
    ScoreAction scoreAction = new ScoreAction(-10);
    scoreAction.setPoints(-1);
    assertEquals(-1, scoreAction.getPoints());
  }

  @Test
  public void testExecuteMethodWithPositiveParameter() {
    ScoreAction scoreAction = new ScoreAction(10);
    Player player = new Player.Builder()
        .build();
    scoreAction.execute(player);
    assertEquals(10, player.getScore());
  }

  @Test
  public void testExecuteMethodWithNegativeParameter() {
    ScoreAction scoreAction = new ScoreAction(-10);
    Player player = new Player.Builder()
        .build();
    scoreAction.execute(player);
    assertEquals(0, player.getScore());
  }

  @Test
  public void testExecuteMethodWithNegativeParameterOnPositiveGold() {
    ScoreAction scoreAction = new ScoreAction(-10);
    Player player = new Player.Builder()
        .setScore(20)
        .build();
    scoreAction.execute(player);
    assertEquals(10, player.getScore());
  }

  @Test
  public void testExecuteMethodWithNullPlayer() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          ScoreAction scoreAction = new ScoreAction(10);
          scoreAction.execute(null);
        });
  }
}