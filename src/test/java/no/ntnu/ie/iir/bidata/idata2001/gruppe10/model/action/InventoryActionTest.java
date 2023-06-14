package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;
import org.junit.jupiter.api.Test;

/**
 * Test the class InventoryAction.
 * <p>
 * The following positive test are performed:
 * InventoryActionConstructor.
 * Execute.
 * </p>
 * <p>
 * The following negative test are performed:
 * InventoryActionConstructor.
 * Execute.
 * </p>
 */
public class InventoryActionTest {
  @Test
  public void testInventoryActionConstructorWithValidSwordParameter() {
    InventoryAction inventoryAction = new InventoryAction("Sword");
    assertEquals("Sword", inventoryAction.getItem());
  }

  @Test
  public void testInventoryActionConstructorWithNullParameter() {
    InventoryAction inventoryAction = new InventoryAction((null));
    assertEquals(null, inventoryAction.getItem());
  }

  @Test
  public void testSetItemWithValidParameter() {
    InventoryAction inventoryAction = new InventoryAction("Sword");
    inventoryAction.setItem("Sword");
    assertEquals("Sword", inventoryAction.getItem());
  }

  @Test
  public void testSetItemWithNullParameter() {
    InventoryAction inventoryAction = new InventoryAction("Sword");
    inventoryAction.setItem(null);
    assertEquals(null, inventoryAction.getItem());
  }

  @Test
  public void testExecuteMethodWithValidParameter() {
    Player player = new Player.Builder()
        .build();
    InventoryAction inventoryAction = new InventoryAction("Sword");
    inventoryAction.execute(player);
    assertEquals(Arrays.asList("Sword"), player.getInventory());
  }

  @Test
  public void testExecuteMethodWithNullParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          InventoryAction inventoryAction = new InventoryAction("Sword");
          inventoryAction.execute(null);
        });
  }
}