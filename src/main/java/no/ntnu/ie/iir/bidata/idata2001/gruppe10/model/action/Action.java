package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action;

import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;

/**
 * Contains a method to manipulate a player.
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public interface Action {
  /**
   * Executes an action for a given player.
   *
   * @param player the player to be executed.
   */
  public void execute(Player player);
}