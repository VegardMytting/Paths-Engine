package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Test the class Passage.
 * <p>
 * The following positive test are performed:
 * CreationOfPassage.
 * SetTitle.
 * SetContent.
 * AddLink.
 * HasLink.
 * GetLinkByTest.
 * GetNumberOfLink.
 * GetNumberOfActions.
 * </p>
 * <p>
 * The following negative test are performed:
 * CreationOfPassage.
 * SetTitle.
 * SetContent.
 * AddLink.
 * HasLink.
 * GetLinkByTest.
 * GetNumberOfLink.
 * </p>
 */
public class PassageTest {
  @Test
  public void testCreationOfPassageWithValidparameters() {
    Passage passage = new Passage("Title", "Content");
    assertEquals("Title", passage.getTitle());
    assertEquals("Content", passage.getContent());
  }

  @Test
  public void testCerationOfPassageWithBlankTitleParameterAndValidContentParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Passage(" ", "Content");
        });
  }

  @Test
  public void testCerationOfPassageWithEmptyTitleParameterAndValidContentParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Passage("", "Content");
        });
  }

  @Test
  public void testCerationOfPassageWithNullTitleParameterAndValidContentParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Passage(null, "Content");
        });
  }

  @Test
  public void testCerationOfPassageWithValidTitleParameterAndBlankContentParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Passage("Text", " ");
        });
  }

  @Test
  public void testCerationOfPassageWithValidTitleParameterAndEmptyContentParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Passage("Text", "");
        });
  }

  @Test
  public void testCerationOfPassageWithValidTitleParameterAndNullContentParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Passage("Text", null);
        });
  }

  @Test
  public void testCerationOfPassageWithBlankTitleParameterAndBlankContentParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Passage(" ", " ");
        });
  }

  @Test
  public void testCerationOfPassageWithEmptyTitleParameterAndEmptyContentParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Passage("", "");
        });
  }

  @Test
  public void testCerationOfPassageWithNullTitleParameterAndNullContentParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Passage(null, null);
        });
  }

  @Test
  public void testCerationOfPassageWithNullTitleParameterAndBlankContentParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Passage(null, " ");
        });
  }

  @Test
  public void testCerationOfPassageWithNullTitleParameterAndEmptyContentParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Passage(null, "");
        });
  }

  @Test
  public void testCerationOfPassageWithBlankTitleParameterAndNullContentParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Passage(" ", null);
        });
  }

  @Test
  public void testCerationOfPassageWithEmptyTitleParameterAndNullContentParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Passage("", null);
        });
  }

  @Test
  public void testCerationOfPassageWithEmptyTitleParameterAndBlankContentParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Passage("", " ");
        });
  }

  @Test
  public void testCerationOfPassageWithBlankTitleParameterAndEmptyContentParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Passage(" ", "");
        });
  }

  @Test
  public void testSetTitleMethodWithValidParameter() {
    Passage passage = new Passage("Title", "Content");
    passage.setTitle("NewTitle");
    assertEquals("NewTitle", passage.getTitle());
  }

  @Test
  public void testSetTitleMethodWithBlankParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage passage = new Passage("Title", "Content");
          passage.setTitle(" ");
        });
  }

  @Test
  public void testSetTitleMethodWithEmptyParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage passage = new Passage("Title", "Content");
          passage.setTitle("");
        });
  }

  @Test
  public void testSetTitleMethodWithNullParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage passage = new Passage("Title", "Content");
          passage.setTitle(null);
        });
  }

  @Test
  public void testSetContentMethodWithValidParameter() {
    Passage passage = new Passage("Title", "Content");
    passage.setContent("NewContent");
    assertEquals("NewContent", passage.getContent());
  }

  @Test
  public void testSetContentMethodWithBlankParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage passage = new Passage("Title", "Content");
          passage.setContent(" ");
        });
  }

  @Test
  public void testSetContentMethodWithEmptyParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage passage = new Passage("Title", "Content");
          passage.setContent("");
        });
  }

  @Test
  public void testSetContentMethodWithNullParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage passage = new Passage("Title", "Content");
          passage.setContent(null);
        });
  }

  @Test
  public void testAddLinkMethodWithOneLink() {
    Passage passage = new Passage("Title", "Content");
    Link link = new Link("Text", "Reference");
    passage.addLink(link);
    assertEquals(Arrays.asList(link), passage.getLinks());
  }

  @Test
  public void testAddLinkMethodWithMultibleLinks() {
    Passage passage = new Passage("Title", "Content");
    Link link1 = new Link("Text1", "Reference1");
    Link link2 = new Link("Text2", "Reference2");
    passage.addLink(link1);
    passage.addLink(link2);
    assertEquals(Arrays.asList(link1, link2), passage.getLinks());

  }

  @Test
  public void testAddLinkMethodWithNullLink() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage passage = new Passage("Title", "Content");
          passage.addLink(null);
        });
  }

  @Test
  public void testHasLinksMethodWithLink() {
    Passage passage = new Passage("Title", "Content");
    Link link = new Link("Text", "Reference");
    passage.addLink(link);
    assertEquals(true, passage.hasLinks());
  }

  @Test
  public void testHasLinksMethodWithNoLink() {
    Passage passage = new Passage("Title", "Content");
    assertEquals(false, passage.hasLinks());
  }

  @Test
  public void testGetLinkBytextMethodWithValidParameter() {
    Passage passage = new Passage("Title", "Content");
    Link link = new Link("Text", "Reference");
    passage.addLink(link);
    assertEquals(link, passage.getLinkByText("Text"));
  }

  @Test
  public void testGetLinkByTextMethodWithBlankParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage passage = new Passage("Title", "Content");
          passage.getLinkByText(" ");
        });
  }

  @Test
  public void testGetLinkByTextMethodWithEmptyParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage passage = new Passage("Title", "Content");
          passage.getLinkByText("");
        });
  }

  @Test
  public void testGetLinkBytextMethodWithNullParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage passage = new Passage("Title", "Content");
          passage.getLinkByText(null);
        });
  }

  @Test
  public void testGetNumberOfLinksMethodWithZeroAction() {
    Passage passage = new Passage("Title", "Content");
    assertEquals(0, passage.getNumberOfLinks());
  }

  @Test
  public void testGetNumberOfLinksMethodWithOneAction() {
    Passage passage = new Passage("Title", "Content");
    Link link = new Link("Text", "Reference");
    passage.addLink(link);
    assertEquals(1, passage.getNumberOfLinks());
  }

  @Test
  public void testGetNumberOfActionsMethodWithFiveActions() {
    Passage passage = new Passage("Title", "Content");
    Link link1 = new Link("Text1", "Reference1");
    Link link2 = new Link("Text2", "Reference2");
    Link link3 = new Link("Text3", "Reference3");
    Link link4 = new Link("Text4", "Reference4");
    Link link5 = new Link("Text5", "Reference5");
    passage.addLink(link1);
    passage.addLink(link2);
    passage.addLink(link3);
    passage.addLink(link4);
    passage.addLink(link5);
    assertEquals(5, passage.getNumberOfLinks());
  }
}