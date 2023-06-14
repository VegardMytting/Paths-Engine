package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;
import org.junit.jupiter.api.Test;

/**
 * Test the class HealthAction.
 * <p>
 * The following positive test are performed:
 * HealthActionConstructor.
 * Execute.
 * </p>
 * <p>
 * The following negative test are performed:
 * HealthActionConstructor.
 * Execute.
 * </p>
 */
public class HealthActionTest {
  @Test
  public void testHealthActionConrstructerWithPositiveParameter() {
    HealthAction healthAction = new HealthAction(10);
    assertEquals(10, healthAction.getHealth());
  }

  @Test
  public void testHealthActionConrstructerWithNegativeParameter() {
    HealthAction healthAction = new HealthAction(-10);
    assertEquals(-10, healthAction.getHealth());
  }

  @Test
  public void testSetHealthMethodWithPositiveParameter() {
    HealthAction healthAction = new HealthAction(10);
    healthAction.setHealth(1);
    assertEquals(1, healthAction.getHealth());
  }

  @Test
  public void testSetHealthMethodWithNegativeParameter() {
    HealthAction healthAction = new HealthAction(-10);
    healthAction.setHealth(-1);
    assertEquals(-1, healthAction.getHealth());
  }

  @Test
  public void testExecuteMethodWithPositiveParameter() {
    HealthAction healthAction = new HealthAction(10);
    Player player = new Player.Builder()
        .setHealth(100)
        .build();
    healthAction.execute(player);
    assertEquals(100, player.getHealth());
  }

  @Test
  public void testExecuteMethodWithPositiveParameterOnDamagedPlayer() {
    HealthAction healthAction = new HealthAction(10);
    Player player = new Player.Builder()
        .setHealth(10)
        .build();
    healthAction.execute(player);
    assertEquals(20, player.getHealth());
  }

  @Test
  public void testExecuteMethodWithNegativeParameter() {
    HealthAction healthAction = new HealthAction(-10);
    Player player = new Player.Builder()
        .setHealth(100)
        .build();
    healthAction.execute(player);
    assertEquals(90, player.getHealth());
  }

  @Test
  public void testExecuteMethodWithNullPlayer() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          HealthAction healthAction = new HealthAction(10);
          healthAction.execute(null);
        });
  }
}