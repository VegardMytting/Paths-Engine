package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action;

/**
 * Represents a factory for the {@link Action} interface.
 * <p>
 * Contains methods to create Action-objects.
 * </p>
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class ActionFactory {
  /**
   * Creates a action-object based on the given String.
   *
   * @param action the type of action to be created.
   * @return the action-object created.
   * @throws IllegalArgumentException if action is not 'health action', 'score
   *                                  action', 'gold action' or 'inventory
   *                                  action'.
   */
  public static Action createAction(String action) {
    switch (action) {
      case "health action":
        return new HealthAction(0);
      case "score action":
        return new ScoreAction(0);
      case "gold action":
        return new GoldAction(0);
      case "inventory action":
        return new InventoryAction(null);
      default:
        throw new IllegalArgumentException(
            "action must be 'health action', 'score action', 'gold action' or 'inventory action'.");
    }
  }

  /**
   * Creates a action-object based on the given String.
   *
   * @param action the type of action to be created.
   * @param value the value of the action.
   * @return the action-object created.
   * @throws IllegalArgumentException if action is not 'health action', 'score
   *                                  action', or 'gold action'.
   */
  public static Action createAction(String action, int value) {
    switch (action) {
      case "health action":
        return new HealthAction(value);
      case "score action":
        return new ScoreAction(value);
      case "gold action":
        return new GoldAction(value);
      default:
        throw new IllegalArgumentException(
            "action must be 'health action', 'score action', or 'gold action'.");
    }
  }

  /**
   * Creates a action-object based on the given String.
   *
   * @param action the type of action to be created.
   * @param item the item of the action.
   * @return the action-object created.
   * @throws IllegalArgumentException if action is not 'inventory action'.
   */
  public static Action createAction(String action, String item) {
    if (!action.equalsIgnoreCase("inventory action")) {
      throw new IllegalArgumentException("action must be 'inventory action'.");
    }
    return new InventoryAction(item);
  }
}