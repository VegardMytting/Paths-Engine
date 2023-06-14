package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;
import org.junit.jupiter.api.Test;

/**
 * Test the class GoldAction.
 * <p>
 * The following positive test are performed:
 * GoldActionConstructor.
 * Execute.
 * </p>
 * <p>
 * The following negative test are performed:
 * GoldActionConstructor.
 * Execute.
 * </p>
 */
public class GoldActionTest {
  @Test
  public void testGoldActionConrstructerWithPositiveParameter() {
    GoldAction goldAction = new GoldAction(10);
    assertEquals(10, goldAction.getGold());
  }

  @Test
  public void testGoldActionConstructionWithNegativeParameter() {
    GoldAction goldAction = new GoldAction(-10);
    assertEquals(-10, goldAction.getGold());
  }

  @Test
  public void testSetGoldMethodWithPositiveParameter() {
    GoldAction goldAction = new GoldAction(10);
    goldAction.setGold(1);
    assertEquals(1, goldAction.getGold());
  }

  @Test
  public void testSetGoldMethodWithNegativeParameter() {
    GoldAction goldAction = new GoldAction(-10);
    goldAction.setGold(-1);
    assertEquals(-1, goldAction.getGold());
  }

  @Test
  public void testExecuteMethodWithPositiveParameter() {
    GoldAction goldAction = new GoldAction(10);
    Player player = new Player.Builder()
        .build();
    goldAction.execute(player);
    assertEquals(10, player.getGold());
  }

  @Test
  public void testExecuteMethodWithNegativeParameter() {
    GoldAction goldAction = new GoldAction(-10);
    Player player = new Player.Builder()
        .build();
    goldAction.execute(player);
    assertEquals(0, player.getGold());
  }

  @Test
  public void testExecuteMethodWithNegativeParameterOnPositiveGold() {
    GoldAction goldAction = new GoldAction(-10);
    Player player = new Player.Builder()
        .setGold(20)
        .build();
    goldAction.execute(player);
    assertEquals(10, player.getGold());
  }

  @Test
  public void testExecuteMethodWithNullPlayer() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          GoldAction goldAction = new GoldAction(10);
          goldAction.execute(null);
      });
  }
}