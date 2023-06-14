package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.logic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Link;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Passage;

/**
 * Represents a story responsible for the interactive, non-linear narrative
 * consisting of a collection of passages.
 * 
 * <p>
 * The Story-class has three attributes:
 * <ul>
 * <li>Title: The story's title.</li>
 * <li>Opening Passage: The first passage in the story.</li>
 * <li>Passages: A collection that contains the story's passages,
 * using a {@code HashMap}-class, and links as the reference-keys.</li>
 * </ul>
 * </p>
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class Story {
  private String title;
  private Passage openingPassage;
  private Map<Link, Passage> passages;

  /**
   * Constructs what will be represented as a empty story
   * with a title and an opening passage.
   *
   * @param title          the title of the story.
   * @param openingPassage the opening passage of the story.
   */
  public Story(String title, Passage openingPassage) {
    setTitle(title);
    setOpeningPassage(openingPassage);
    this.passages = new HashMap<>();
  }

  /**
   * Sets the title of the story as a text.
   * If the title is invalid, an IllegalArgumentException
   * will be activated.
   *
   * @param title the title of the story as a text.
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
   * Returns the title of the story as a text.
   *
   * @return the title of the story as a text.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Sets the opening passage of the story as a passage.
   * If the opening passage is invalid, an IllegalArgumentException
   * will be activated.
   *
   * @param openingPassage the opening passage of the story as a passage.
   * @throws IllegalArgumentException if the openingPassage argument is null.
   */
  public void setOpeningPassage(Passage openingPassage) {
    if (openingPassage == null) {
      throw new IllegalArgumentException("Opening Passage cannot be null");
    } else {
      this.openingPassage = openingPassage;
    }
  }

  /**
   * Returns the opening passage of the story as a passage.
   *
   * @returnthe opening passage of the story as a passage.
   */
  public Passage getOpeningPassage() {
    return this.openingPassage;
  }

  /**
   * Adds a passage to the story.
   *
   * @param passage the passage that is to be added to the story.
   * @throws IllegalArgumentException if the passage argument is null.
   */
  public void addPassage(Passage passage) {
    if (passage == null) {
      throw new IllegalArgumentException("Passage cannot be null.");
    }

    Link passageLink = new Link(passage.getTitle(), passage.getTitle());
    this.passages.put(passageLink, passage);
  }

  /**
   * Returns the collection of passages.
   *
   * @return the collection of passages.
   */
  public Collection<Passage> getAllPassages() {
    return this.passages.values();
  }

  /**
   * Returns the collection of links.
   *
   * @return the collection of links.
   */
  public Collection<Link> getAllLinks() {
    return this.passages.keySet();
  }

  /**
   * Returns the passage.
   *
   * @return the passage.
   */
  public Map<Link, Passage> getPassages() {
    return this.passages;
  }

  /**
   * Returns the number of passages in the map.
   *
   * @return the number of passages in the map.
   */
  public int getNumberOfPassages() {
    return this.passages.size();
  }

  /**
   * returns the passage found in the story,
   * that is being held by a link (key),
   * given by the parameter.
   *
   * @param link the link of the passage to be found.
   * @return the passage by the given link.
   * @throws IllegalArgumentException if the link argument is null.
   */
  public Passage getPassageByLink(Link link) {
    if (link == null) {
      throw new IllegalArgumentException("Link cannot be null");
    }

    Passage foundPassage = null;
    boolean notFound = true;

    Iterator<Passage> it = this.passages.values().iterator();
    while (notFound && it.hasNext()) {
      Passage passage = it.next();
      if (passage.getTitle().equals(link.getReference())) {
        foundPassage = passage;
        notFound = false;
      }
    }
    return foundPassage;
  }

  /**
   * Removes a passage from passages.
   * 
   * <p>
   * If the passage exist, the passage is removed and {@code true} is returned.
   * If the passage does not exist {@code false} is returned.
   * </p>
   *
   * @param passage the passage to ve removed.
   * @return {@code true} if the passage was successfully removed.
   *         If the passage does not exist {@code false} is returned.
   */
  public boolean removePassage(Passage passage) {
    return this.passages.values().remove(passage);
  }

  /**
   * Removes the first passage in passages matching the given link recieved by the
   * user.
   *
   * @param link the link that refers to the passage, to be removed.
   */
  public void removePassageByLink(Link link) {
    boolean succesfullRemoval = this.passages.values()
            .stream()
            .allMatch(passage -> passage.getLinks()
                    .stream()
                    .filter(functionalLink -> functionalLink.equals(link))
                    .collect(Collectors.toList())
                    .isEmpty());
    if (succesfullRemoval) {
      //'this.passages.remove(link)' does not work! I do not know why.
      Iterator<Passage> it = this.passages.values().iterator();
      while (it.hasNext()) {
        Passage passage = it.next();
        if (link.getReference().equals(passage.getTitle())) {
          it.remove();
        }
      }
    }
  }

  /**
   * Returns a list over every link that has a reference that does not match a
   * passages title.
   *
   * @return a list over every link that has a reference that does not match a
   *         passages title.
   */
  public List<Link> getBrokenLinks() {
    return passages.values()
        .stream()
        .flatMap(passage -> passage.getLinks()
            .stream())
        .filter(link -> this.getPassageByLink(link) == null)
        .toList();
  }

  /**
   * Returns a list over every passage that does not contain a link.
   *
   * @return a list over every passage that does not contain a link.
   */
  public List<Passage> getDeadEndPassages() {
    return passages.values()
        .stream()
        .filter(passage -> !passage.hasLinks())
        .toList();
  }
}