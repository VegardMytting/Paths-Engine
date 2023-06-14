package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal;

import java.util.LinkedList;
import java.util.List;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;

/**
 * Represents a goal for a player to achive the minimum health requirement.
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class HealthGoal implements Goal {
  private int minimumHealth;

  /**
   * Constructs a health goal for the player to achive.
   *
   * @param minimumHealth the minimum amount of health to achive.
   */
  public HealthGoal(int minimumHealth) {
    setHealthGoal(minimumHealth);
  }

  /**
   * Sets the minimum amount of health for the goal.
   *
   * @param minimumHealth the minimum amount of health required.
   * @throws IllegalArgumentException if the minimum health is negative or greater
   *                                  than 100.
   */
  public void setHealthGoal(int minimumHealth) {
    if (minimumHealth < 0 || minimumHealth > 100) {
      throw new IllegalArgumentException(
          "Minimum Health cannot be less than 0 nor greater than 100");
    } else {
      this.minimumHealth = minimumHealth;
    }
  }

  /**
   * Returns the minimum health requirement.
   *
   * @return the minimum health requirement.
   */
  public int getHealthGoal() {
    return this.minimumHealth;
  }

  /**
   * Checks if the player has succesfully achived the goal requrement.
   *
   * @param player the player to check.
   * @return {@code true} if the player has achived the required health amount,
   *         and {@code false} if the player has not achived the required gold
   *         amount.
   */
  public boolean isFulfilled(Player player) {
    return player.getHealth() >= this.minimumHealth;
  }

  /**
   * Returns the health goal value as a String.
   *
   * @returns the health goal value as a String.
   */
  public String getGoalValueAsString() {
    return this.minimumHealth + "";
  }

  /**
   * Returns a list containing the minimum health requirement.
   *
   * @return a list containing the minimum health requirement.
   */
  public List<String> getList() {
    List<String> hold = new LinkedList<>();
    hold.add(this.getGoalValueAsString());
    return hold;
  }
}