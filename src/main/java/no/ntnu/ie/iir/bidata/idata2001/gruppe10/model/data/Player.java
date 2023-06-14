package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a player with different abilities that
 * can affect a story to make it non-linear.
 * 
 * <p>
 * The Player class has five attributes:
 * <ul>
 * <li>Name: The name of a player that is to play the game.</li>
 * <li>Health: The health of a player, may never be less than 0.</li>
 * <li>Score: The score a player earns throuout the game.</li>
 * <li>Gold: The gold a the player collects along the way.</li>
 * <li>Inventory: The inventory of a player, as a list with different items.
 * </li>
 * </ul>
 * </p>
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-17
 */
public class Player {
  private String name;
  private int health;
  private int score;
  private int gold;
  private List<String> inventory;

  /**
   * Constructs a player with different abilities that
   * can affect a story to make it non-linear.
   * 
   * <p>
   * This is the default constructor, and it is made <b>private</b>
   * to disable the possibility to create an instance of the player-class
   * without going via the {@link Builder}.
   * </p>
   */
  private Player() {
    setName("PLAYER_NAME");
    setHealth(100);
    setScore(0);
    setGold(0);
    this.inventory = new LinkedList<>();
  }

  /**
   * Sets the name of the player as a String.
   *
   * @param name the name of the player as a text.
   * @throws IllegalArgumentException if the name argument is blank, empty or
   *                                  null.
   */
  public void setName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be blank, empty or null.");
    } else {
      this.name = name.strip();
    }
  }

  /**
   * Returns the name of the player.
   *
   * @return the name of the player.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the health of the player as a positive value,
   * may never be less than 0 nor higher than 100.
   *
   * @param health the health of the player as a positive value,
   *               may never be less than 0 nor higher than 100.
   * @throws IllegalArgumentException if the health argument is lower than 0 or
   *                                  greater than 100.
   */
  public void setHealth(int health) {
    if (health < 0 || health > 100) {
      throw new IllegalArgumentException("Health cannot be lower than 0 or greater than 100");
    } else {
      this.health = health;
    }
  }

  /**
   * Returns the health of the player as a positive value,
   * may never be less than 0 nor higher than 100.
   *
   * @return the health of the player as a positive value,
   *         may never be less than 0 nor higher than 100.
   */
  public int getHealth() {
    return this.health;
  }

  /**
   * Sets the score of the player as a positive value,
   * may never be less than 0.
   *
   * @param score the score of the player as a positive value,
   *              may never be less than 0.
   * @throws IllegalArgumentException if the score argument is lower than 0.
   */
  public void setScore(int score) {
    if (score < 0) {
      throw new IllegalArgumentException("Score cannot be lower than 0");
    } else {
      this.score = score;
    }
  }

  /**
   * Returns the score of the player as a positive value,
   * may never be less than 0.
   *
   * @return the score of the player as a positive value,
   *         may never be less than 0.
   */
  public int getScore() {
    return this.score;
  }

  /**
   * Sets the gold of the player as a positive value,
   * may never be less than 0.
   *
   * @param gold the gold of the player as a positive value,
   *             may never be less than 0.
   * @throws IllegalArgumentException if the gold argument is lower than 0.
   */
  public void setGold(int gold) {
    if (gold < 0) {
      throw new IllegalArgumentException("Gold cannot be lower than 0");
    } else {
      this.gold = gold;
    }
  }

  /**
   * Returns the gold of the player as a positive value,
   * may never be less than 0.
   *
   * @return the gold of the player as a positive value,
   *         may never be less than 0.
   */
  public int getGold() {
    return this.gold;
  }

  /**
   * Adds an item to the inventory.
   *
   * @param item the item that is to be added to the inventory.
   * @throws IllegalArgumentException if the item argument is null.
   */
  public void addToInventory(String item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null");
    } else {
      this.inventory.add(item);
    }
  }

  /**
   * Sets the inventory of the player with a list, may never be {@code null}.
   *
   * @param items the items list to set as the inventory.
   */
  public void setInventory(List<String> items) {
    if (items == null) {
      throw new IllegalArgumentException("Items cannot be null");
    } else {
      this.inventory = items;
    }
  }

  /**
   * Removes an item from the inventory.
   *
   * @param item the item that is to be removed from the inventory.
   * @throws IllegalArgumentException if the item argument is null.
   */
  public void removeFromInventory(String item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null");
    } else {
      this.inventory.remove(item);
    }
  }

  /**
   * Returns the collection of items.
   * Represented as the player's inventory.
   *
   * @return the collection of items.
   *         Represented as the player's inventory.
   */
  public List<String> getInventory() {
    return this.inventory;
  }

  /**
   * Returns the inventory as a String.
   *
   * @return the inventory as a String.
   */
  public String getInventoryAsString() {
    String allItems = "";
    for (String item : this.inventory) {
      allItems += "\n" + item.strip();
    }
    return allItems;
  }

  /**
   * Returns the builder for the class.
   *
   * @return the builder for the class.
   */
  public static Builder getBuilder() {
    return new Builder();
  }

  /**
   * Represents the Builder-class for a player with different abilities that
   * can affect a story to make it non-linear.
   *
   * <p>
   * The Builder-class has five attributes for the player:
   * <ul>
   * <li>Name: The name of a player that is to play the game.</li>
   * <li>Health: The health of a player, may never be less than 0.</li>
   * <li>Score: The score a player earns throuout the game.</li>
   * <li>Gold: The gold a the player collects along the way.</li>
   * <li>Inventory: The inventory of a player, as a list with different
   * items.</li>
   * </ul>
   * </p>
   * Builder class for constructing instances of the {@link Player} class using
   * the builder pattern.
   *
   * @author Vegard Arnesen Mytting
   * @version 2023-05-23
   */
  public static class Builder {
    private String name;
    private int health;
    private int score;
    private int gold;
    private List<String> inventory;

    /**
     * Constructs a new instance of the Builder class.
     */
    public Builder() {
      this.inventory = new LinkedList<>();
    }

    /**
     * Sets the name of the player.
     *
     * @param name the name of the player.
     * @return the name.
     * @throws IllegalArgumentException if the name is blank, empty or null.
     */
    public Builder setName(String name) {
      if (name == null || name.isBlank()) {
        throw new IllegalArgumentException("Name cannot be blank, empty or null.");
      } else {
        this.name = name.strip();
      }
      return this;
    }

    /**
     * Sets the health of the player.
     *
     * @param health the health of the player.
     * @return the health.
     * @throws IllegalArgumentException if the health is lower than 0 or greater
     *                                  than 100.
     */
    public Builder setHealth(int health) {
      if (health <= 0 || health > 100) {
        throw new IllegalArgumentException(
          "Health cannot be lower than zero or zero, or greater than 100");
      } else {
        this.health = health;
      }
      return this;
    }

    /**
     * Sets the score of the player.
     *
     * @param score the score of the player.
     * @return the score.
     * @throws IllegalArgumentException if the score is lower than 0.
     */
    public Builder setScore(int score) {
      if (score < 0) {
        throw new IllegalArgumentException("Score cannot be lower than 0");
      } else {
        this.score = score;
      }
      return this;
    }

    /**
     * Sets the gold of the player.
     *
     * @param gold the gold of the player.
     * @return the gold.
     * @throws IllegalArgumentException if the gold is lower than 0.
     */
    public Builder setGold(int gold) {
      if (gold < 0) {
        throw new IllegalArgumentException("Gold cannot be lower than 0");
      } else {
        this.gold = gold;
      }
      return this;
    }

    /**
     * Adds an item to the inventory of the player.
     *
     * @param items the item to be added to the inventory of the player.
     * @return the inventory
     * @throws IllegalArgumentException if item is null.
     */
    public Builder addToInventory(List<String> items) {
      if (items == null) {
        throw new IllegalArgumentException("Item cannot be null");
      } else {
        this.inventory.addAll(items);
      }
      return this;
    }

    /**
     * Builds and returns the instance of the player class on the
     * values set by the builder instance.
     *
     * @return an instance of the Player class.
     */
    public Player build() {
      Player player = new Player();
      player.name = this.name;
      player.health = this.health;
      player.score = this.score;
      player.gold = this.gold;
      player.inventory = this.inventory;
      return player;
    }
  }
}