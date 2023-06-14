package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;
import org.junit.jupiter.api.Test;

/**
 * Test the class InventoryGoal.
 * <p>
 * The following positive test are performed:
 * InventoryGoal.
 * Constructor.
 * SetInventoryGoal.
 * IsFulFilled.
 * </p>
 * <p>
 * The following negative test are performed:
 * InventoryGoal.
 * Constructor.
 * SetInventoryGoal.
 * IsFulFilled.
 * </p>
 */
public class InventoryGoalTest {
  @Test
  public void testInventoryGoalConstructorWithOneParameter() {
    InventoryGoal inventoryGoal = new InventoryGoal(Arrays.asList("Sword"));
    assertEquals(Arrays.asList("Sword"), inventoryGoal.getMandatoryItems());
  }

  @Test
  public void testInventoryGoalConstructorWithTwoParameter() {
    InventoryGoal inventoryGoal = new InventoryGoal(Arrays.asList("Sword", "Shield"));
    assertEquals(Arrays.asList("Sword", "Shield"), inventoryGoal.getMandatoryItems());
  }

  @Test
  public void testInventoryGoalConstructorWithNullParameter() {
    InventoryGoal inventoryGoal = new InventoryGoal(null);
    assertEquals(null, inventoryGoal.getMandatoryItems());
  }

  @Test
  public void testSetInventoryGoalMethodWithOneParameter() {
    InventoryGoal inventoryGoal = new InventoryGoal(null);
    inventoryGoal.setInventoryGoal(Arrays.asList("Sword"));
    assertEquals(Arrays.asList("Sword"), inventoryGoal.getMandatoryItems());
  }

  @Test
  public void testSetInventoryGoalMethodWithTwoParameter() {
    InventoryGoal inventoryGoal = new InventoryGoal(null);
    inventoryGoal.setInventoryGoal(Arrays.asList("Sword", "Shield"));
    assertEquals(Arrays.asList("Sword", "Shield"), inventoryGoal.getMandatoryItems());
  }

  @Test
  public void testIsFulfilledMethodWithExactParameter() {
    Player player = new Player.Builder()
        .addToInventory(Arrays.asList("Sword"))
        .build();
    InventoryGoal inventoryGoal = new InventoryGoal(Arrays.asList("Sword"));
    assertEquals(true, inventoryGoal.isFulfilled(player));
  }

  @Test
  public void testIsFulfilledMethodWithHigherParameter() {
    Player player = new Player.Builder()
        .addToInventory(Arrays.asList("Sword", "Shield"))
        .build();
    InventoryGoal inventoryGoal = new InventoryGoal(Arrays.asList("Sword"));
    assertEquals(true, inventoryGoal.isFulfilled(player));
  }

  @Test
  public void testIsFulfilledMethodWithHigherParameterReversedInventory() {
    Player player = new Player.Builder()
        .addToInventory(Arrays.asList("Shield", "Sword"))
        .build();
    InventoryGoal inventoryGoal = new InventoryGoal(Arrays.asList("Sword"));
    assertEquals(true, inventoryGoal.isFulfilled(player));
  }

  @Test
  public void testIsFulfilledMethodWithLowerParameter() {
    Player player = new Player.Builder()
        .addToInventory(Arrays.asList("Sword"))
        .build();
    InventoryGoal inventoryGoal = new InventoryGoal(Arrays.asList("Sword", "Shield"));
    assertEquals(false, inventoryGoal.isFulfilled(player));
  }

  @Test
  public void testIsFulfilledMethodWithNullParameter() {
    assertThrows(NullPointerException.class,
        () -> {
          InventoryGoal inventoryGoal = new InventoryGoal(Arrays.asList("Sword"));
          inventoryGoal.isFulfilled(null);
        });
  }
}