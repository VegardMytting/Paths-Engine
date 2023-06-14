package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action.Action;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action.ActionFactory;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Link;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Passage;

/**
 * Provides methods to read a story from a file and parse it into a story
 * object.
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class FileHandler {
  private List<String> allItems = new LinkedList<>();

  /**
   * Reads a story from a file and returns a story object.
   *
   * @param fileName the path of the file containing the story.
   * @return the story representing the story read from the file.
   * @throws IOException if an I/O error occurs while reading the file.
   */
  public Story readStory(Path fileName) throws IOException {
    Story story = null;
    String storyBlock = Files.readString(fileName);
    story = parseStory(storyBlock);
    return story;
  }

  /**
   * Parses a story from a given story-block.
   *
   * @param storyBlock the given story-block.
   * @return the story representing the parsed story.
   */
  private Story parseStory(String storyBlock) {
    try (Scanner scanner = new Scanner(storyBlock)) {
      String title = scanner.nextLine();
      scanner.useDelimiter("::");
      Story story = null;
      scanner.next();

      while (scanner.hasNext()) {
        String passageBlock = scanner.next();
        Passage passage = parsePassage(passageBlock);
        if (story == null) {
          story = new Story(title, passage);
        }
        story.addPassage(passage);
      }

      return story;
    }
  }

  /**
   * Parses a passage from a given passage-block.
   *
   * @param passageBlock the given passage-block.
   * @return the passage representing the parsed passage.
   * @throws FaultyPassageException if the passage is faulty.
   */
  private Passage parsePassage(String passageBlock) {
    String[] lines = passageBlock.split("\\r?\\n");
    String title = lines[0].strip();
    String content = lines[1].strip();
    Passage passage = new Passage(title, content);

    if (lines.length > 2) {
      for (int i = 2; i < lines.length; i++) {
        String linkBlock = lines[i];
        if (!linkBlock.isBlank()) {
          Link link = parseLink(linkBlock);
          passage.addLink(link);
        }
      }
    } else if (lines.length < 2) {
      try {
        throw new FaultyPassageException("Passage must have a 'title' and a 'content'.");
      } catch (FaultyPassageException fpe) {
        fpe.getMessage();
      }
    }

    return passage;
  }

  /**
   * Parses a link from a given link-block.
   *
   * @param linkBlock the given link-block.
   * @return the link representing the parsed link.
   * @throws FaultyLinkException if the link is faulty.
   */
  private Link parseLink(String linkBlock) {
    String[] lines = linkBlock.split("]");

    String textBlock = lines[0].strip();
    String text = null;
    if (textBlock.startsWith("[")) {
      text = textBlock.substring(1, textBlock.length());
    }

    String referenceBlock = lines[1].strip();
    String reference = null;
    if (referenceBlock.startsWith("(") && referenceBlock.endsWith(")")) {
      reference = referenceBlock.substring(1, referenceBlock.length() - 1);
    }

    Link link = new Link(text, reference);

    if (lines.length > 2) {
      for (int i = 2; i < lines.length; i++) {
        String actionBlock = lines[i];
        if (!actionBlock.isBlank()) {
          Action action = parseAction(actionBlock);
          link.addAction(action);
        }
      }
    } else if (lines.length < 2) {
      try {
        throw new FaultyLinkException("Link must have a 'text' and a 'reference'.");
      } catch (FaultyLinkException fle) {
        fle.getMessage();
      }
    }

    return link;
  }

  /**
   * Parses an action from a given action-block.
   *
   * @param actionBlock the given action-block.
   * @return the action representing the parsed action.
   * @throws FaultyActionException if the action is faulty.
   */
  private Action parseAction(String actionBlock) {
    String[] lines = actionBlock.split(",");
    String type = lines[0].toLowerCase().strip();
    String value = lines[1].strip();
    Action action = null;

    switch (type) {
      case "health action":
        int health = Integer.parseInt(value);
        action = ActionFactory.createAction(type, health);
        break;

      case "gold action":
        int gold = Integer.parseInt(value);
        action = ActionFactory.createAction(type, gold);
        break;

      case "score action":
        int score = Integer.parseInt(value);
        action = ActionFactory.createAction(type, score);
        break;

      case "inventory action":
        action = ActionFactory.createAction(type, value);
        break;

      default:
        try {
          throw new FaultyActionException(
              "Action must be 'Health Action', 'Score Action', "
                  + "'Gold Action' or 'Inventory Action'.");
        } catch (FaultyActionException fae) {
          fae.getMessage();
        }
    }

    if (checkIfString(value)) {
      this.allItems.add(value);
    }

    return action;
  }

  /**
   * Checks if the value is a number.
   *
   * @param value the value to test.
   * @return {@code} true if the value is a String, and
   *         {@code false} if the value is an Integer.
   */
  private boolean checkIfString(String value) {
    try {
      Integer.parseInt(value);
    } catch (NumberFormatException nfe) {
      return true;
    }

    return false;
  }

  /**
   * returns a String over all story items.
   *
   * @return a String over all story items.
   */
  public String getAllItemsAsString() {
    String allItems = "";
    for (String item : this.allItems) {
      allItems += item.strip() + ", ";
    }
    return allItems;
  }

  /**
   * Returns a list of all story items.
   *
   * @returna list of all story items.
   */
  public List<String> getAllItems() {
    return this.allItems;
  }

  /**
   * Custom exception class for faulty passages.
   */
  class FaultyPassageException extends Exception {
    /**
     * Constructs a FaultyPassageException with the specified detail message.
     *
     * @param message the detail message.
     */
    private FaultyPassageException(String message) {
      super(message);
    }
  }

  /**
   * Custom exception class for faulty links.
   */
  class FaultyLinkException extends Exception {
    /**
     * Constructs a FaultyLinkException with the specified detail message.
     *
     * @param message the detail message.
     */
    private FaultyLinkException(String message) {
      super(message);
    }
  }

  /**
   * Custom exception class for faulty actions.
   */
  class FaultyActionException extends Exception {
    /**
     * Constructs a FaultyActionException with the specified detail message.
     *
     * @param message the detail message.
     */
    private FaultyActionException(String message) {
      super(message);
    }
  }
}