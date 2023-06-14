package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal;

import java.util.LinkedList;
import java.util.List;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;

/**
 * Represents a goal for a player to achive the minimum gold requirement.
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class GoldGoal implements Goal {
  private int minimumGold;

  /**
   * Constructs a gold goal for the player to achive.
   *
   * @param minimumGold the minimum amount of gold to achive.
   */
  public GoldGoal(int minimumGold) {
    setGoldGoal(minimumGold);
  }

  /**
   * Sets the minimum amount of gold for the goal.
   *
   * @param minimumGold the minimum amount of gold required.
   * @throws IllegalArgumentException if the minimum gold is negative.
   */
  public void setGoldGoal(int minimumGold) {
    if (minimumGold < 0) {
      throw new IllegalArgumentException("Minimum Gold cannot be less than 0.");
    } else {
      this.minimumGold = minimumGold;
    }
  }

  /**
   * Returns the minimum gold requirement.
   *
   * @return the the minimum gold requirement.
   */
  public int getGoldGoal() {
    return this.minimumGold;
  }

  /**
   * Checks if the player has succesfully achived the goal requirement.
   *
   * @param player the player to check.
   * @returns {@code true} if the player has achived the required gold amount,
   *          and {@code false} if the player has not achived the required gold
   *          amount.
   */
  public boolean isFulfilled(Player player) {
    return player.getGold() >= this.minimumGold;
  }

  /**
   * Returns the gold goal value as a String.
   *
   * @return the gold goal value as a String.
   */
  public String getGoalValueAsString() {
    return this.minimumGold + "";
  }

  /**
   * Returns a list containing the minimum gold requirement.
   *
   * @return a list containing the minimum gold requirement.
   */
  public List<String> getList() {
    List<String> hold = new LinkedList<>();
    hold.add(this.getGoalValueAsString());
    return hold;
  }
}