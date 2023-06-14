package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal;

import java.util.List;

/**
 * Represents a factory for the {@link Goal} interface.
 * <p>
 * Contains methods to create Goal-objects.
 * </p>
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class GoalFactory {
  /**
   * Creates a goal-object based on the given String.
   *
   * @param goal the type of goal to be created.
   * @return the goal-object created.
   * @throws IllegalArgumentException if goal is not 'health goal', 'score goal',
   *                                  'gold goal' or 'inventory goal'.
   */
  public static Goal createGoal(String goal) {
    switch (goal) {
      case "health goal":
        return new HealthGoal(0);
      case "score goal":
        return new ScoreGoal(0);
      case "gold goal":
        return new GoldGoal(0);
      case "inventory goal":
        return new InventoryGoal(null);
      default:
        throw new IllegalArgumentException(
            "goal must be 'health goal', 'score goal', 'gold goal' or 'inventory goal'.");
    }
  }

  /**
   * Creates a goal-object based on the given String.
   *
   * @param goal the type of goal to be created.
   * @param value the value of the goal.
   * @return the goal-object created.
   * @throws IllegalArgumentException if the goal is not 'health goal', 'score goal' or 'gold goal'.
   */
  public static Goal createGoal(String goal, int value) {
    switch (goal) {
      case "health goal":
        return new HealthGoal(value);
      case "score goal":
        return new ScoreGoal(value);
      case "gold goal":
        return new GoldGoal(value);
      default:
        throw new IllegalArgumentException(
            "goal must be 'health goal', 'score goal' or 'gold goal'.");
    }
  }

  /**
   * Creates a goal-object based on the given String.
   *
   * @param goal the type of goal to be created.
   * @param items the items list of the goal.
   * @return the goal-object created.
   * @throws IllegalArgumentException if the goal is not 'inventory goal'.
   */
  public static Goal createGoal(String goal, List<String> items) {
    if (!goal.equalsIgnoreCase("inventory goal")) {
      throw new IllegalArgumentException("goal must be 'inventory goal'.");
    }
    return new InventoryGoal(items);
  }
}