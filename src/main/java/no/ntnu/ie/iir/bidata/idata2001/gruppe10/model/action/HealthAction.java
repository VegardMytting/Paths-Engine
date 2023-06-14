package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action;

import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;

/**
 * Represents an action that manipulates the health value of a player.
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class HealthAction implements Action {
  private int health;

  /**
   * Constructs a health action to manipulate a player's health.
   *
   * @param health the health value of the action.
   */
  public HealthAction(int health) {
    setHealth(health);
  }

  /**
   * Sets the health value of the action.
   *
   * @param health the new health value to set.
   */
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Returns the health value of the action.
   *
   * @return the current health value of the action.
   */
  public int getHealth() {
    return this.health;
  }

  /**
   * Executes the health action on the guven player.
   *
   * @param player the player to be executed.
   * @throws IllegalArgumentException if the player is null.
   */
  public void execute(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    } else {
      if (player.getHealth() + this.health >= 100) {
        player.setHealth(100);
      } else if (player.getHealth() + this.health <= 0) {
        player.setHealth(0);
      } else {
        player.setHealth(player.getHealth() + this.health);
      }
    }
  }
}