package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action;

import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;

/**
 * Represents an action that manipulates the inventory of a player.
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class InventoryAction implements Action {
  private String item;

  /**
   * Constructs a inventory action to add items to a player's inventory.
   *
   * @param item the item of the action.
   */
  public InventoryAction(String item) {
    setItem(item);
  }

  /**
   * Sets the item of the action.
   *
   * @param item the item to set.
   */
  public void setItem(String item) {
    this.item = item;
  }

  /**
   * Returns the item of the action.
   *
   * @return the item of the action.
   */
  public String getItem() {
    return this.item;
  }

  /**
   * Executes the inventory action on the given player.
   *
   * @param player the player to be executed.
   * @throws IllegalArgumentException if the player is null.
   */
  public void execute(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    } else {
      if (!player.getInventory().contains(this.item)) {
        player.addToInventory(this.item);
      }
    }
  }
}