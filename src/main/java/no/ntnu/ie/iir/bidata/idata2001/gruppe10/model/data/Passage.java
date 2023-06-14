package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents an episode in a larger story. It is possible to
 * travel between passages by links.
 *
 * <p>
 * The Passage-class has three attributes:
 * <ul>
 * <li>Title: A description that also works as an identifier.</li>
 * <li>Content: Text that typically contains what would be represented
 * as an episode or a part of a dialog.</li>
 * <li>Links: Links are what connects one passage to others. A passage
 * with two or more links makes a story non-linear.</li>
 * </ul>
 * </p>
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class Passage {
  private String title;
  private String content;
  private List<Link> links;

  /**
   * Constructs what will be represented as a room, an episode, a passage in a
   * story.
   *
   * @param title   the title of the passage as a text.
   * @param content the content of the passage as a text.
   */
  public Passage(String title, String content) {
    setTitle(title);
    setContent(content);
    this.links = new LinkedList<>();
  }

  /**
   * Sets the title of the passage as a text.
   *
   * @param title the title of the passage to be set.
   * @throws IllegalArgumentException if the title argument is blank, empty or
   *                                  null.
   */
  public void setTitle(String title) {
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be blank, empty or null");
    } else {
      this.title = title.trim();
    }
  }

  /**
   * Returns the title of the passage as a text.
   *
   * @return the title of the passage as a text.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Sets the content of the passage as a text.
   *
   * @param content the content of the passage as a text.
   * @throws IllegalArgumentException if the content argument is blank, empty or
   *                                  null.
   */
  public void setContent(String content) {
    if (content == null || content.isBlank()) {
      throw new IllegalArgumentException("Content cannot be blank, empty or null");
    } else {
      this.content = content.trim();
    }
  }

  /**
   * Returns the content of the passage as a text.
   *
   * @return the content of the passage as a text.
   */
  public String getContent() {
    return this.content;
  }

  /**
   * Adds a link to the passage.
   *
   * @param link the link that is to be added to the passage.
   * @return {@code true} if the item was successfully added.
   * @throws IllegalArgumentException if the link argument is null.
   */
  public boolean addLink(Link link) {
    if (link == null) {
      throw new IllegalArgumentException("link cannot be null");
    } else {
      return this.links.add(link);
    }
  }

  /**
   * Returns the collection of links.
   *
   * @return The collection of links.
   */
  public List<Link> getLinks() {
    return this.links;
  }

  /**
   * Checks if the passage has any links that connects to other passages
   * to be able to continue the story.
   *
   * @return {@code true} if the passage has links that connects to other
   *         passages.
   *         If the passage does not have any links that connect to other passages
   *         {@code false} is returned.
   */
  public boolean hasLinks() {
    return !this.links.isEmpty();
  }

  /**
   * Returns the first link found matching the text given by the parameter.
   *
   * @param text the text of the link to be found
   * @return the link matching the given text. If no links were found with the
   *         matching text, {@code null} is returned.
   * @throws IllegalArgumentException if the text argument is blank, empty or
   *                                  null.
   */
  public Link getLinkByText(String text) {
    if (text == null || text.isBlank()) {
      throw new IllegalArgumentException("Text cannot be blank, empty or null.");
    }

    Link foundLink = null;
    boolean notFound = true;

    Iterator<Link> it = this.links.iterator();
    while (notFound && it.hasNext()) {
      Link link = it.next();
      if (link.getText().equalsIgnoreCase(text.trim())) {
        foundLink = link;
        notFound = false;
      }
    }
    return foundLink;
  }

  /**
   * Returns the number of links in a passage.
   *
   * @return the number of links in a passage.
   */
  public int getNumberOfLinks() {
    return this.links.size();
  }

  @Override
  public String toString() {
    return "Title: " + this.title
        + "\nContent:" + this.content;
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

    Passage passage = (Passage) object;
    return (title.equals(passage.title)
        && content.equals(passage.title)
        && links.equals(passage.links));
  }

  @Override
  public int hashCode() {
    int code = 7;
    code = 31 * code + (this.title == null ? 0 : this.title.hashCode());
    code = 31 * code + (this.content == null ? 0 : this.content.hashCode());
    code = 31 * code + (this.links == null ? 0 : this.links.hashCode());
    return code;
  }
}