package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action;

import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;

/**
 * Represents an action that manipulates the score value of the player.
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class ScoreAction implements Action {
  private int points;

  /**
   * Constructs a score action to manipulate a player's score.
   *
   * @param points the score value of the action.
   */
  public ScoreAction(int points) {
    setPoints(points);
  }

  /**
   * Sets the score value of the action.
   *
   * @param points the new score value to set.
   */
  public void setPoints(int points) {
    this.points = points;
  }

  /**
   * Returns the score value of the action.
   *
   * @return the score value of the action.
   */
  public int getPoints() {
    return this.points;
  }

  /**
   * Executes the score action on the given player.
   *
   * @param player the player to be executed.
   * @throws IllegalArgumentException if the player is null.
   */
  public void execute(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null.");
    } else {
      if (player.getScore() + this.points < 0) {
        player.setScore(0);
      } else {
        player.setScore(player.getScore() + this.points);
      }
    }
  }
}