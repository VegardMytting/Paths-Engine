package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Link;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Passage;
import org.junit.jupiter.api.Test;

/**
 * Test the class Story.
 * <p>
 * The following positive test are performed:
 * CreationOfStory.
 * SetTitle.
 * SetOpeningPassage.
 * AddPassage.
 * RemovePassageByLink.
 * </p>
 * <p>
 * The following negative test are performed:
 * CreationOfStory.
 * SetTitle.
 * SetOpeningPassage.
 * AddPassage.
 * </p>
 */
public class StoryTest {
  @Test
  public void testCreationOfStoryWithValidParameters() {
    Passage openingPassage = new Passage("Title", "Content");
    Story story = new Story("Title", openingPassage);
    assertEquals("Title", story.getTitle());
    assertEquals("Title", story.getOpeningPassage().getTitle());
    assertEquals("Content", story.getOpeningPassage().getContent());
  }

  @Test
  public void testCreationOfStoryWithBlankTitleParameterAndValidOpeningPassageParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage openingPassage = new Passage("Title", "Content");
          new Story(" ", openingPassage);
        });
  }

  @Test
  public void testCreationOfStoryWithEmptyTitleParameterAndValidOpeningPassageParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage openingPassage = new Passage("Title", "Content");
          new Story("", openingPassage);
        });
  }

  @Test
  public void testCreationOfStoryWithNullTitleParameterAndValidOpeningPassageParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage openingPassage = new Passage("Title", "Content");
          new Story(null, openingPassage);
        });
  }

  @Test
  public void testCreationOfStoryWithValidTitleParameterAndNullOpeningPassageParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Story("Title", null);
        });
  }

  @Test
  public void testCreationOfStoryWithBlankTitleParameterAndNullOpeningPassageParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Story(" ", null);
        });
  }

  @Test
  public void testCreationOfStoryWithEmptyTitleParameterAndNullOpeningPassageParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Story("", null);
        });
  }

  @Test
  public void testCreationOfStoryWithNullTitleParameterAndNullOpeningPassageParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Story(null, null);
        });
  }

  @Test
  public void testsetTitleWithValidParameters() {
    Passage openingPassage = new Passage("Title", "Content");
    Story story = new Story("Title", openingPassage);
    story.setTitle("Test");
    assertEquals("Test", story.getTitle());
  }

  @Test
  public void testsetTitleWithInvalidParameters() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage openingPassage = new Passage("Title", "Content");
          Story story = new Story("Title", openingPassage);
          story.setTitle("");
        });
  }

  @Test
  public void testsetTitleWithNullParameters() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage openingPassage = new Passage("Title", "Content");
          Story story = new Story("Title", openingPassage);
          story.setTitle(null);
        });
  }

  @Test
  public void testsetOpeningPassageWithValidParameters() {
    Passage openingPassage = new Passage("Title", "Content");
    Passage openingPassage2 = new Passage("Title", "Content");
    Story story = new Story("Title", openingPassage);
    story.setOpeningPassage(openingPassage2);
    assertEquals(openingPassage2, story.getOpeningPassage());
  }

  @Test
  public void testsetOpeningPassageWithNullParameters() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage openingPassage = new Passage("Title", "Content");
          Story story = new Story("Title", openingPassage);
          story.setOpeningPassage(null);
        });
  }

  @Test
  public void testAddPassageWithValidParameters() {
    Passage openingPassage = new Passage("Title", "Content");
    Story story = new Story("Title", openingPassage);
    story.addPassage(openingPassage);
    assertEquals(1, story.getAllPassages().size());
  }

  @Test
  public void testAddPassageWithNullParameters() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage openingPassage = new Passage("Title", "Content");
          Story story = new Story("Title", openingPassage);
          story.addPassage(null);
        });
  }

  @Test
  public void testRemovePassageByLink() {
    Passage openingPassage = new Passage("Title", "Content");
    Story story = new Story("Title", openingPassage);
    Story story1 = new Story("Title", openingPassage);

    final Link link1 = new Link("Text1", "Title1");
    Passage passage1 = new Passage("Title1", "Content1");
    story.addPassage(passage1);
    story1.addPassage(passage1);

    final Link link2 = new Link("Text2", "Title2");
    Passage passage2 = new Passage("Title2", "Content2");
    story.addPassage(passage2);
    story1.addPassage(passage2);

    final Link link3 = new Link("Text3", "Title3");
    Passage passage3 = new Passage("Title3", "Content3");
    story.addPassage(passage3);

    passage1.addLink(link2);
    passage3.addLink(link1);

    story.removePassageByLink(link3);
    ;

    assertEquals(story1.getPassages(), story.getPassages());
  }

  @Test
  public void testRemovePassageByLinkWhenLinkIsReferingToPassage() {
    Passage openingPassage = new Passage("Title", "Content");
    Story story = new Story("Title", openingPassage);
    Story story1 = new Story("Title", openingPassage);

    final Link link1 = new Link("Text1", "Title1");
    Passage passage1 = new Passage("Title1", "Content1");
    story.addPassage(passage1);
    story1.addPassage(passage1);

    final Link link2 = new Link("Text2", "Title2");
    Passage passage2 = new Passage("Title2", "Content2");
    story.addPassage(passage2);
    story1.addPassage(passage2);

    Link link3 = new Link("Text3", "Title3");
    Passage passage3 = new Passage("Title3", "Content3");
    story.addPassage(passage3);

    passage1.addLink(link2);
    passage2.addLink(link3);
    passage3.addLink(link1);

    story.removePassageByLink(link3);
    ;

    assertNotEquals(story1.getPassages(), story.getPassages());
  }
}