package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;
import org.junit.jupiter.api.Test;

/**
 * Test the class HealthGoal.
 * <p>
 * The following positive test are performed:
 * HealthGoalConstructor.
 * SetHealth.
 * IsFufilled.
 * </p>
 * <p>
 * The following negative test are performed:
 * HealthGoalConstructor.
 * SetHealth.
 * IsFufilled.
 * </p>
 */
public class HealthGoalTest {
  @Test
  public void testHealthGoalConstructorWithValidParameter() {
    HealthGoal healthGoal = new HealthGoal(10);
    assertEquals(10, healthGoal.getHealthGoal());
  }

  @Test
  public void testHealthGoalConstructorWithInvalidParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new HealthGoal(-10);
        });
  }

  @Test
  public void testSetHealthGoalMethodWithValidParameter() {
    HealthGoal healthGoal = new HealthGoal(10);
    healthGoal.setHealthGoal(1);
    assertEquals(1, healthGoal.getHealthGoal());
  }

  @Test
  public void testSetHealGoalMethodWithLowInvalidParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          HealthGoal healthGoal = new HealthGoal(10);
          healthGoal.setHealthGoal(-1);
        });
  }

  @Test
  public void testSetHealGoalMethodWithHighInvalidParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          HealthGoal healthGoal = new HealthGoal(10);
          healthGoal.setHealthGoal(101);
        });
  }

  @Test
  public void testIsFulfilledMethodWithExactParameter() {
    Player player = new Player.Builder()
        .setHealth(10)
        .build();
    HealthGoal healthGoal = new HealthGoal(10);
    assertEquals(true, healthGoal.isFulfilled(player));
  }

  @Test
  public void testIsFulfilledMethodWithHigherParameter() {
    Player player = new Player.Builder()
        .setHealth(11)
        .build();
    HealthGoal healthGoal = new HealthGoal(10);
    assertEquals(true, healthGoal.isFulfilled(player));
  }

  @Test
  public void testIsFulfilledMethodWithLowerParameter() {
    Player player = new Player.Builder()
        .setHealth(9)
        .build();
    HealthGoal healthGoal = new HealthGoal(10);
    assertEquals(false, healthGoal.isFulfilled(player));
  }

  @Test
  public void testIsFulfilledMethodWithNullParameter() {
    assertThrows(NullPointerException.class,
        () -> {
          HealthGoal healthGoal = new HealthGoal(10);
          healthGoal.isFulfilled(null);
        });
  }
}