package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action.GoldAction;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action.HealthAction;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action.InventoryAction;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action.ScoreAction;
import org.junit.jupiter.api.Test;

/**
 * Test the class LinkTest.
 * <p>
 * The following positive test are performed:
 * CreationOfLink.
 * SetText.
 * SetReference.
 * AddAction.
 * GetNumber.
 * Equals.
 * </p>
 * <p>
 * The following negative test are performed:
 * CreationOfLink.
 * SetText.
 * SetReference.
 * AddAction.
 * </p>
 */
public class LinkTest {
  @Test
  public void testCreationOfLinkWithValidTextParametersAndValidReferenceParameter() {
    Link link = new Link("Text", "Reference");
    assertEquals("Text", link.getText());
    assertEquals("Reference", link.getReference());
  }

  @Test
  public void testCreationOfLinkWithBlankTextParameterAndValidReferenceParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Link(" ", "Reference");
        });
  }

  @Test
  public void testCreationOfLinkWithEmptyTextParameterAndValidReferenceParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Link("", "Reference");
        });
  }

  @Test
  public void testCreationOfLinkWithNullTextParameterAndValidReferenceParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Link(null, "Reference");
        });
  }

  @Test
  public void testCreationOfLinkWithValidTextParameterAndBlankReferenceParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Link("Text", " ");
        });
  }

  @Test
  public void testCreationOfLinkWithValidTextParameterAndEmptyReferenceParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Link("Text", "");
        });
  }

  @Test
  public void testCreationOfLinkWithValidTextParameterAndNullReferenceParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Link("Text", null);
        });
  }

  @Test
  public void testCreationOfLinkWithBlankTextParameterAndBlankReferenceParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Link(" ", " ");
        });
  }

  @Test
  public void testCreationOfLinkWithEmptyTextParameterAndEmptyReferenceParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Link("", "");
        });
  }

  @Test
  public void testCreationOfLinkWithNullTextParameterAndNullReferenceParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Link(null, null);
        });
  }

  @Test
  public void testCreationOfLinkWithNullTextParameterAndBlankReferenceParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Link(null, " ");
        });
  }

  @Test
  public void testCreationOfLinkWithNullTextParameterAndEmptyReferenceParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Link(null, "");
        });
  }

  @Test
  public void testCreationOfLinkWithBlankTextParameterAndNullReferenceParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Link(" ", null);
        });
  }

  @Test
  public void testCreationOfLinkWithBlankTextParameterAndEmptyReferenceParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Link(" ", "");
        });
  }

  @Test
  public void testCreationOfLinkWithEmptyTextParameterAndNullReferenceParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Link("", null);
        });
  }

  @Test
  public void testCreationOfLinkWithEmptyTextParameterAndBlankReferenceParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Link("", " ");
        });
  }

  @Test
  public void testSetTextMethodWithValidParameter() {
    Link link = new Link("Text", "Reference");
    link.setText("NewText");
    assertEquals("NewText", link.getText());
  }

  @Test
  public void testSetTextMethodWithBlankParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Link link = new Link("Text", "Reference");
          link.setText(" ");
        });
  }

  @Test
  public void testSetTextMethodWithEmptyParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Link link = new Link("Text", "Reference");
          link.setText("");
        });
  }

  @Test
  public void testSetTextMethodWithNullParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Link link = new Link("Text", "Reference");
          link.setText(null);
        });
  }

  @Test
  public void testSetreferenceMethodWithValidParameter() {
    Link link = new Link("Text", "Reference");
    link.setReference("NewReference");
    assertEquals("NewReference", link.getReference());
  }

  @Test
  public void testSetReferenceMethodWithBlankParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Link link = new Link("Text", "Reference");
          link.setReference(" ");
        });
  }

  @Test
  public void testSetReferenceMethodWithEmptyParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Link link = new Link("Text", "Reference");
          link.setReference("");
        });
  }

  @Test
  public void testSetReferenceMethodWithNullParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Link link = new Link("Text", "Reference");
          link.setReference(null);
        });
  }

  @Test
  public void testAddActionMethodWithOneActionAndValidParameter() {
    Link link = new Link("Text", "Reference");
    HealthAction healthAction = new HealthAction(10);
    link.addAction(healthAction);
    assertEquals(Arrays.asList(healthAction), link.getActions());
  }

  @Test
  public void testAddActionMethodWithTwoActionAndValidParameter() {
    Link link = new Link("Text", "Reference");
    HealthAction healthAction = new HealthAction(10);
    GoldAction goldAction = new GoldAction(10);
    link.addAction(healthAction);
    link.addAction(goldAction);
    assertEquals(Arrays.asList(healthAction, goldAction), link.getActions());
  }

  @Test
  public void testAddActionMethodWithOneActionAndNullParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Link link = new Link("Text", "Reference");
          link.addAction(null);
        });
  }

  @Test
  public void testGetNumberOfActionsMethodWithZeroAction() {
    Link link = new Link("Text", "Reference");
    assertEquals(0, link.getNumberOfActions());
  }

  @Test
  public void testGetNumberOfActionsMethodWithOneAction() {
    Link link = new Link("Text", "Reference");
    HealthAction healthAction = new HealthAction(10);
    link.addAction(healthAction);
    assertEquals(1, link.getNumberOfActions());
  }

  @Test
  public void testGetNumberOfActionsMethodWithFiveActions() {
    Link link = new Link("Text", "Reference");
    HealthAction healthAction = new HealthAction(10);
    GoldAction goldAction = new GoldAction(10);
    ScoreAction scoreAction = new ScoreAction(10);
    InventoryAction inventoryAction1 = new InventoryAction("Item1");
    InventoryAction inventoryAction2 = new InventoryAction("item2");
    link.addAction(healthAction);
    link.addAction(goldAction);
    link.addAction(scoreAction);
    link.addAction(inventoryAction1);
    link.addAction(inventoryAction2);
    assertEquals(5, link.getNumberOfActions());
  }

  @Test
  public void testequals() {
    Link link = new Link("Text", "reference");
    Link link2 = new Link("Text", "reference");
    assertTrue(link2.equals(link));
  }
}