package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal;

import java.util.LinkedList;
import java.util.List;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;

/**
 * Represents a goal for a player to achive the minimum score requirement.
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class ScoreGoal implements Goal {
  int minimumPoints;

  /**
   * Conrsturcts a score goal for the player to achive.
   *
   * @param minimumPoints the minimum amount of points to achive,
   */
  public ScoreGoal(int minimumPoints) {
    setMinimumPoints(minimumPoints);
  }

  /**
   * Sets the minimum amount of points for the goal.
   *
   * @param minimumPoints the minimum amount of points required.
   * @throws IllegalArgumentException if the minimum points is negative.
   */
  public void setMinimumPoints(int minimumPoints) {
    if (minimumPoints < 0) {
      throw new IllegalArgumentException("Minimum Points cannot be less than 0.");
    } else {
      this.minimumPoints = minimumPoints;
    }
  }

  /**
   * Returns the minimum points required.
   *
   * @return the minimum points required.
   */
  public int getScoreGoal() {
    return this.minimumPoints;
  }

  /**
   * Checks if the player has succesully achived the goal requirement.
   *
   * @param player the player to check.
   * @return {@code true} if the player has achived the required score amount,
   *         and {@code false} if the player has not achived the required score
   *         amount
   */
  public boolean isFulfilled(Player player) {
    return player.getScore() >= this.minimumPoints;
  }

  /**
   * Returns the score goal value as a String.
   *
   * @return the score goal value as a String.
   */
  public String getGoalValueAsString() {
    return this.minimumPoints + "";
  }

  /**
   * Returns a list containing the minimum points requirement.
   *
   * @return a list containing the minimum points requirement.
   */
  public List<String> getList() {
    List<String> hold = new LinkedList<>();
    hold.add(this.getGoalValueAsString());
    return hold;
  }
}