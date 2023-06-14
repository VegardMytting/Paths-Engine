package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;
import org.junit.jupiter.api.Test;

/**
 * Test the class ScoreGoal.
 * <p>
 * The following positive test are performed:
 * ScoreGoalConstructor.
 * SetMinimumPoints.
 * IsFulFilled.
 * </p>
 * <p>
 * The following negative test are performed:
 * ScoreGoalConstructor.
 * SetMinimumPoints.
 * IsFulFilled.
 * </p>
 */
public class ScoreGoalTest {
  @Test
  public void testScoreGoalConstructorWithValidParameter() {
    ScoreGoal scoreGoal = new ScoreGoal(10);
    assertEquals(10, scoreGoal.getScoreGoal());
  }

  @Test
  public void testScoreGoalConstructorWithInvalidParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new ScoreGoal(-10);
        });
  }

  @Test
  public void testSetMinimumPointsMethodWithValidPointsParameter() {
    ScoreGoal scoreGoal = new ScoreGoal(10);
    scoreGoal.setMinimumPoints(1);
    assertEquals(1, scoreGoal.getScoreGoal());
  }

  @Test
  public void testSetMinimumPointsMethodWithInvalidPointsParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          ScoreGoal scoreGoal = new ScoreGoal(10);
          scoreGoal.setMinimumPoints(-1);
        });
  }

  @Test
  public void testisFulfilledMethodWithExactParameter() {
    Player player = new Player.Builder()
        .setScore(10)
        .build();
    ScoreGoal scoreGoal = new ScoreGoal(10);
    assertEquals(true, scoreGoal.isFulfilled(player));
  }

  @Test
  public void testisFulfilledMethodWithHigherParameter() {
    Player player = new Player.Builder()
        .setScore(11)
        .build();
    ScoreGoal scoreGoal = new ScoreGoal(10);
    assertEquals(true, scoreGoal.isFulfilled(player));
  }

  @Test
  public void testisFulfilledMethodWithLowerParameter() {
    Player player = new Player.Builder()
        .setScore(9)
        .build();
    ScoreGoal scoreGoal = new ScoreGoal(10);
    assertEquals(false, scoreGoal.isFulfilled(player));
  }

  @Test
  public void testIsFulfilledMethodWithNullParameter() {
    assertThrows(NullPointerException.class,
        () -> {
          ScoreGoal scoreGoal = new ScoreGoal(10);
          scoreGoal.isFulfilled(null);
        });
  }
}