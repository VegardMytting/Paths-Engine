package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.logic;

import java.util.LinkedList;
import java.util.List;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Link;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Passage;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal.Goal;

/**
 * Represents a Game responsible for the facade of the paths-game.
 * The class connects a player to a story, starts and maneuvering in the game.
 * 
 * <p>
 * The Game-class has three attributes:
 * <ul>
 * <li>Player: The player that is to play the game.</li>
 * <li>Story: The story that is to be played by the player.</li>
 * <li>goals: A list with specific goals the player has to achive.</li>
 * </ul>
 * </p>
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class Game {
  private Player player;
  private Story story;
  private List<Goal> goals;

  /**
   * Constucts what will be represented as a game.
   *
   * @param player the player of the game.
   * @param story the story of the game.
   * @param goals the goals of the game.
   */
  public Game(Player player, Story story, List<Goal> goals) {
    this.goals = new LinkedList<>();
    setPlayer(player);
    setStory(story);
    setGoals(goals);
  }

  /**
   * Sets the player of the game.
   *
   * @param player the player of the game.
   * @throws IllegalArgumentException if the player argument is null.
   */
  public void setPlayer(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    } else {
      this.player = player;
    }
  }

  /**
   * Returns the player of the game.
   *
   * @return the player of the game.
   */
  public Player getPlayer() {
    return this.player;
  }

  /**
   * Sets the story of the game.
   *
   * @param story the story of the game.
   * @throws llegalArgumentException if the story argument is null.
   */
  public void setStory(Story story) {
    if (story == null) {
      throw new IllegalArgumentException("Story cannot be null");
    } else {
      this.story = story;
    }
  }

  /**
   * Returns the story of the game.
   *
   * @return the story of the game.
   */
  public Story getStory() {
    return this.story;
  }

  /**
   * Sets the goals of the game.
   *
   * @param goals the goals of the game.
   * @throws IllegalArgumentException if the goals are null.
   */
  public void setGoals(List<Goal> goals) {
    if (goals == null) {
      throw new IllegalArgumentException("Goals cannot be null");
    } else {
      this.goals = goals;
    }
  }

  /**
   * Returns a list of goals.
   *
   * @return a list of goals.
   */
  public List<Goal> getGoals() {
    return this.goals;
  }

  /**
   * Returns the opening passage of the game.
   *
   * @return the opening passage of the game.
   */
  public Passage begin() {
    return this.story.getOpeningPassage();
  }

  /**
   * Returns the passage the link leads to.
   *
   * @param link the link of the passage.
   * @return the passage the link leads to.
   */
  public Passage go(Link link) {
    link.getActions().forEach(action -> action.execute(this.player));
    return story.getPassageByLink(link);
  }
}