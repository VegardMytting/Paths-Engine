package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;
import org.junit.jupiter.api.Test;

/**
 * Test the class GoldGoalTest.
 * <p>
 * The following positive test are performed:
 * GoldGoalConsturctor.
 * SetGold.
 * IsFulfilled.
 * </p>
 * <p>
 * The following negative test are performed:
 * GoldGoalConsturctor.
 * SetGold.
 * IsFulfilled.
 * </p>
 */
public class GoldGoalTest {
  @Test
  public void testGoldGoalConstructorWithValidParameter() {
    GoldGoal goldGoal = new GoldGoal(10);
    assertEquals(10, goldGoal.getGoldGoal());
  }

  @Test
  public void testGoldGoalConstructorWithInvalidParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new GoldGoal(-10);
        });
  }

  @Test
  public void testSetGoldGoalMethodWithValidParameter() {
    GoldGoal goldGoal = new GoldGoal(10);
    goldGoal.setGoldGoal(1);
    assertEquals(1, goldGoal.getGoldGoal());
  }

  @Test
  public void testSetGoldGoalMethodWithInvalidParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          GoldGoal goldGoal = new GoldGoal(10);
          goldGoal.setGoldGoal(-1);
        });
  }

  @Test
  public void testisFulfilledMethodWithExactParameter() {
    Player player = new Player.Builder()
        .setGold(10)
        .build();
    GoldGoal goldGoal = new GoldGoal(10);
    assertEquals(true, goldGoal.isFulfilled(player));
  }

  @Test
  public void testisFulfilledMethodWithHigherParameter() {
    Player player = new Player.Builder()
        .setGold(11)
        .build();
    GoldGoal goldGoal = new GoldGoal(10);
    assertEquals(true, goldGoal.isFulfilled(player));
  }

  @Test
  public void testisFulfilledMethodWithLowerParameter() {
    Player player = new Player.Builder()
        .setGold(9)
        .build();
    GoldGoal goldGoal = new GoldGoal(10);
    assertEquals(false, goldGoal.isFulfilled(player));
  }

  @Test
  public void testIsFulfilledMethodWithNullParameter() {
    assertThrows(NullPointerException.class,
        () -> {
          GoldGoal goldGoal = new GoldGoal(10);
          goldGoal.isFulfilled(null);
        });
  }
}