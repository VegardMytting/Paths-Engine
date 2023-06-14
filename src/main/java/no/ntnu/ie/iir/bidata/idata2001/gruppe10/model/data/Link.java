package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data;

import java.util.LinkedList;
import java.util.List;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action.Action;

/**
 * Represents a connection (a link) from a passage to another. Multible
 * links and passages forms a story with choices to make the story
 * non-linear.
 *
 * <p>
 * The Link-class has three attributes:
 * <ul>
 * <li>Text: Describes a text that indicates a choice or an action
 * in a story. The text is the part that the player can see.</li>
 * <li>Reference: A string that identifies a passage (an episode in a story).
 * This will in practice be the title for the passage one would
 * want to refere to.</li>
 * <li>Actions: A list with special objects which makes it possible to alter
 * the attributes of a player.</li>
 * </ul>
 * </p>
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class Link {
  private String text;
  private String reference;
  private List<Action> actions;

  /**
   * Constructs what will be represented as a connection between passages, a link.
   *
   * @param text      the text of the link as a text.
   * @param reference the reference of the link as a text.
   */
  public Link(String text, String reference) {
    setText(text);
    setReference(reference);
    this.actions = new LinkedList<>();
  }

  /**
   * Sets the text of the link as a text.
   *
   * @param text the text of the link as a text.
   * @throws IllegalArgumentException if the text argument is blank, empty or
   *                                  null.
   */
  public void setText(String text) {
    if (text == null || text.isBlank()) {
      throw new IllegalArgumentException("Text cannot be blank, empty or null");
    } else {
      this.text = text.trim();
    }
  }

  /**
   * Returns the text of the link as a text.
   *
   * @return the text of tthe link as a text.
   */
  public String getText() {
    return this.text;
  }

  /**
   * Sets the reference of the link as a text.
   *
   * @param reference the reference of the link as a text.
   * @throws IllegalArgumentException if the reference argument is blank, empty or
   *                                  null.
   */
  public void setReference(String reference) {
    if (reference == null || reference.isBlank()) {
      throw new IllegalArgumentException("Reference cannot be blank, empty or null");
    } else {
      this.reference = reference.trim();
    }
  }

  /**
   * Returns the reference of the link as a text.
   *
   * @return the reference of the link as a text.
   */
  public String getReference() {
    return this.reference;
  }

  /**
   * Adds an action to the actions-list.
   *
   * @param action the action that is to be added to the actions-list.
   * @throws IllegalArgumentException if the action argument is null.
   */
  public void addAction(Action action) {
    if (action == null) {
      throw new IllegalArgumentException("Action cannot be null");
    } else {
      this.actions.add(action);
    }
  }

  /**
   * Returns the Iterator to the collection of actions.
   *
   * @return the Iterator to the collection of actions.
   */
  public List<Action> getActions() {
    return this.actions;
  }

  /**
   * Returns the number of actions in a link.
   *
   * @return the number of actions in a link.
   */
  public int getNumberOfActions() {
    return this.actions.size();
  }

  @Override
  public String toString() {
    return "text: " + this.text
        + "\nreference: " + this.reference;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (this.getClass() != object.getClass()) {
      return false;
    }

    Link link = (Link) object;
    return (text.equals(link.text)
        && reference.equals(link.reference)
        && actions.equals(link.actions));
  }

  @Override
  public int hashCode() {
    int code = 7;
    code = 31 * code + (this.text == null ? 0 : this.text.hashCode());
    code = 31 * code + (this.reference == null ? 0 : this.reference.hashCode());
    code = 31 * code + (this.actions == null ? 0 : this.actions.hashCode());
    return code;
  }
}