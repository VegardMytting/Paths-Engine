package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action;

import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;

/**
 * Represents an action that manipulates the gold value of a player.
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class GoldAction implements Action {
  private int gold;

  /**
   * Constructs a gold action to manipulate a player's gold.
   *
   * @param gold the gold value of the action.
   */
  public GoldAction(int gold) {
    setGold(gold);
  }

  /**
   * Sets the gold value of the action.
   *
   * @param gold the new gold value to set.
   */
  public void setGold(int gold) {
    this.gold = gold;
  }

  /**
   * Returns the gold value of the action.
   *
   * @return the gold value of the action.
   */
  public int getGold() {
    return this.gold;
  }

  /**
   * Executes the gold action on the given player.
   *
   * @param player the player to be executed.
   * @throws IllegalArgumentException if the player is null.
   */
  public void execute(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    } else {
      if (player.getGold() + this.gold < 0) {
        player.setGold(0);
      } else {
        player.setGold(player.getGold() + this.gold);
      }
    }
  }
}