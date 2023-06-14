package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal;

import java.util.List;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;

/**
 * Represents a goal for a player to achive the mandatory items requirement.
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class InventoryGoal implements Goal {
  private List<String> mandatoryItems;

  /**
   * Constructs a inventory goal for the player to achive.
   *
   * @param mandatoryItems the list of mandatory items to achive.
   */
  public InventoryGoal(List<String> mandatoryItems) {
    setInventoryGoal(mandatoryItems);
  }

  /**
   * Sets the mandatory items for the goal.
   *
   * @param mandatoryItems the list of mandatory items required.
   */
  public void setInventoryGoal(List<String> mandatoryItems) {
    this.mandatoryItems = mandatoryItems;
  }

  /**
   * Returns the list of mandatory items requirement.
   *
   * @return the list of mandatory items requirement.
   */
  public List<String> getMandatoryItems() {
    return this.mandatoryItems;
  }

  /**
   * Checks if the player has succesfully achived the goal requirement.
   *
   * @param player the player to check.
   * @return {@code true} if the player has achived the required mandatoty items,
   *         and {@code false} if the player has not achived the required
   *         mandatory items.
   */
  public boolean isFulfilled(Player player) {
    return player.getInventory().containsAll(this.mandatoryItems);
  }

  /**
   * Returns the inventory goal as a String.
   *
   * @return the inventory goal as a String.
   */
  public String getGoalValueAsString() {
    String allInventory = "";
    for (String inventory : this.mandatoryItems) {
      allInventory += "\n" + inventory.strip();
    }
    return allInventory;
  }

  /**
   * Returns a list containing the mandatory items requirement.
   *
   * @return a list containing the mandatory items requirement.
   */
  public List<String> getList() {
    return this.mandatoryItems;
  }
}