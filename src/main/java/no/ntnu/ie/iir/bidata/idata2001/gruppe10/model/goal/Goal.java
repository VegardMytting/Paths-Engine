package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal;

import java.util.List;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;

/**
 * Contains methods to set goals for a player.
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public interface Goal {
  /**
   * Checks if the player has succesfully achived their goal.
   *
   * @param player the player to be checked.
   */
  public boolean isFulfilled(Player player);

  /**
   * Returns the goal values as a String.
   *
   * @return the goal values as a String.
   */
  public String getGoalValueAsString();

  /**
   * Returns a list over the values.
   * 
   * <p>
   * This method primary intended for the {@link InventoryGoal}-class.
   * </p>
   *
   * @return a list over the values.
   */
  public List<String> getList();
}