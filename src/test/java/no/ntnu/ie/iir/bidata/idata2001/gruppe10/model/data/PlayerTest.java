package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Test the class Player.
 * <p>
 * The following positive test are performed:
 * CreationOfPlayer.
 * SetName.
 * SetHealth.
 * SetScore.
 * SetGold.
 * AddToInventory.
 * </p>
 * <p>
 * The following negative test are performed:
 * CreationOfPlayer.
 * SetName.
 * SetHealth.
 * SetScore.
 * SetGold.
 * AddToInventory.
 * </p>
 */
public class PlayerTest {
  @Test
  public void testCreationOfPlayerWithValidNameParameterAndValidHealthParameterAndValidScoreParameterAndValidGoldParameter() {
    Player player = new Player.Builder()
        .setName("Name")
        .setHealth(100)
        .setScore(0)
        .setGold(0)
        .build();
    assertEquals("Name", player.getName());
    assertEquals(100, player.getHealth());
    assertEquals(0, player.getScore());
    assertEquals(0, player.getGold());
  }

  @Test
  public void testCreationOfPlayerWithBlankNameParameterAndValidHealthParameterAndValidScoreParameterAndValidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName(" ")
              .setHealth(100)
              .setScore(0)
              .setGold(0)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithEmptyNameParameterAndValidHealthParameterAndValidScoreParameterAndValidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName("")
              .setHealth(100)
              .setScore(0)
              .setGold(0)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithNullNameParameterAndValidHealthParameterAndValidScoreParameterAndValidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName(null)
              .setHealth(100)
              .setScore(0)
              .setGold(0)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithValidNameParameterAndInvalidHealthParameterAndValidScoreParameterAndValidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName("Name")
              .setHealth(-1)
              .setScore(0)
              .setGold(0)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithBlankNameParameterAndInvalidHealthParameterAndValidScoreParameterAndValidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName(" ")
              .setHealth(-1)
              .setScore(0)
              .setGold(0)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithEmptyNameParameterAndInvalidHealthParameterAndValidScoreParameterAndValidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName("")
              .setHealth(-1)
              .setScore(0)
              .setGold(0)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithNullNameParameterAndInvalidHealthParameterAndValidScoreParameterAndValidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName(null)
              .setHealth(-1)
              .setScore(0)
              .setGold(0)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithValidNameParameterAndValidHealthParameterAndInvalidScoreParameterAndValidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName("Name")
              .setHealth(0)
              .setScore(-1)
              .setGold(0)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithBlankNameParameterAndValidHealthParameterAndInvalidScoreParameterAndValidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName(" ")
              .setHealth(0)
              .setScore(-1)
              .setGold(0)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithEmptyNameParameterAndValidHealthParameterAndInvalidScoreParameterAndValidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {

          new Player.Builder()
              .setName("")
              .setHealth(0)
              .setScore(-1)
              .setGold(0)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithNullNameParameterAndValidHealthParameterAndInvalidScoreParameterAndValidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName(null)
              .setHealth(0)
              .setScore(-1)
              .setGold(0)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithValidNameParameterAndInvalidHealthParameterAndInvalidScoreParameterAndValidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName("Name")
              .setHealth(-1)
              .setScore(-1)
              .setGold(0)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithBlankNameParameterAndInvalidHealthParameterAndInvalidScoreParameterAndValidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName(" ")
              .setHealth(-1)
              .setScore(-1)
              .setGold(0)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithEmptyNameParameterAndInvalidHealthParameterAndInvalidScoreParameterAndValidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {

          new Player.Builder()
              .setName("")
              .setHealth(-1)
              .setScore(-1)
              .setGold(0)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithNullNameParameterAndInvalidHealthParameterAndInvalidScoreParameterAndValidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName(null)
              .setHealth(-1)
              .setScore(-1)
              .setGold(0)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithValidNameParameterAndValidHealthParameterAndValidScoreParameterAndInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName("Name")
              .setHealth(0)
              .setScore(0)
              .setGold(-1)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithBlankNameParameterAndValidHealthParameterAndValidScoreParameterAndInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName(" ")
              .setHealth(0)
              .setScore(0)
              .setGold(-1)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithEmptyNameParameterAndValidHealthParameterAndValidScoreParameterAndInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName("")
              .setHealth(0)
              .setScore(0)
              .setGold(-1)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithNullNameParameterAndValidHealthParameterAndValidScoreParameterAndInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName(null)
              .setHealth(0)
              .setScore(0)
              .setGold(-1)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithValidNameParameterAndInvalidHealthParameterAndValidScoreParameterAndInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName("Name")
              .setHealth(-1)
              .setScore(0)
              .setGold(-1)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithBlankNameParameterAndInvalidHealthParameterAndValidScoreParameterAndInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName(" ")
              .setHealth(-1)
              .setScore(0)
              .setGold(-1)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithEmptyNameParameterAndInvalidHealthParameterAndValidScoreParameterAndInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName("")
              .setHealth(-1)
              .setScore(0)
              .setGold(-1)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithNullNameParameterAndInvalidHealthParameterAndValidScoreParameterAndInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName("name")
              .setHealth(-1)
              .setScore(0)
              .setGold(-1)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithValidNameParameterAndValidHealthParameterAndInvalidScoreParameterAndInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName("Name")
              .setHealth(0)
              .setScore(-1)
              .setGold(-1)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithBlankNameParameterAndValidHealthParameterAndInvalidScoreParameterAndInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName(" ")
              .setHealth(0)
              .setScore(-1)
              .setGold(-1)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithEmptyNameParameterAndValidHealthParameterAndInvalidScoreParameterAndInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName("")
              .setHealth(0)
              .setScore(-1)
              .setGold(-1)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithNullNameParameterAndValidHealthParameterAndInvalidScoreParameterAndInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName(null)
              .setHealth(0)
              .setScore(-1)
              .setGold(-1)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithValidNameParameterAndInvalidHealthParameterAndInvalidScoreParameterAndInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName("Name")
              .setHealth(-1)
              .setScore(-1)
              .setGold(-1)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithBlankNameParameterAndInvalidHealthParameterAndInvalidScoreParameterAndInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName(" ")
              .setHealth(-1)
              .setScore(-1)
              .setGold(-1)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithEmptyNameParameterAndInvalidHealthParameterAndInvalidScoreParameterAndInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName("")
              .setHealth(-1)
              .setScore(-1)
              .setGold(-1)
              .build();
        });
  }

  @Test
  public void testCreationOfPlayerWithNullNameParameterAndInvalidHealthParameterAndInvalidScoreParameterAndInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName(null)
              .setHealth(-1)
              .setScore(-1)
              .setGold(-1)
              .build();
        });
  }

  @Test
  public void testSetNameMethodWithValidNameParameter() {
    Player player = new Player.Builder()
        .build();
    player.setName("NewName");
    assertEquals("NewName", player.getName());
  }

  @Test
  public void testSetNameMethodWithBlankNameParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Player player = new Player.Builder()
              .build();
          player.setName(" ");
        });
  }

  @Test
  public void testSetNameMethodWithEmptyNameParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Player player = new Player.Builder()
              .build();
          player.setName("");
        });
  }

  @Test
  public void testSetNameMethodWithNullNameParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Player player = new Player.Builder()
              .build();
          player.setName(null);
        });
  }

  @Test
  public void testSetHealthMethodWithValidHealthParameter() {
    Player player = new Player.Builder()
        .build();
    player.setHealth(1);
    assertEquals(1, player.getHealth());
  }

  @Test
  public void testSetHealthMethodWithInvalidHealthParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Player player = new Player.Builder()
              .build();
          player.setHealth(-1);
        });
  }

  @Test
  public void testSetScoreMethodWithValidScoreParameter() {
    Player player = new Player.Builder()
        .build();
    player.setScore(1);
    assertEquals(1, player.getScore());
  }

  @Test
  public void testSetScoreMethodWithInvalidScoreParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Player player = new Player.Builder()
              .build();
          player.setScore(-1);
        });
  }

  @Test
  public void testSetGoldMethodWithValidGoldParameter() {
    Player player = new Player.Builder()
        .build();
    player.setGold(1);
    assertEquals(1, player.getGold());
  }

  @Test
  public void testSetGoldMethodWithInvalidGoldParameter() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Player player = new Player.Builder()
              .build();
          player.setGold(-1);
        });
  }

  @Test
  public void testAddToInventoryMethodWithSwordItem() {
    Player player = new Player.Builder()
        .setName("Name")
        .addToInventory(Arrays.asList("Sword"))
        .build();

    assertEquals(Arrays.asList("Sword"), player.getInventory());
  }

  @Test
  public void testAddToInventoryMethodWithSwordItemAndShieldItem() {
    Player player = new Player.Builder()
        .addToInventory(Arrays.asList("Sword", "Shield"))
        .build();
    assertEquals(Arrays.asList("Sword", "Shield"), player.getInventory());
  }

  @Test
  public void testAddToInventorymethodWithNullItem() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .setName("Name")
              .addToInventory(null)
              .build();
        });
  }

  @Test
  public void testRemoveFromInventoryMethodWithSwordItem() {
    Player player = new Player.Builder()
        .setName("Name")
        .addToInventory(Arrays.asList("Sword"))
        .build();

    player.removeFromInventory("Sword");

    assertEquals(Arrays.asList(), player.getInventory());
  }

  @Test
  public void testRemoveFromInventoryMethodWithSwordItemAndShieldItem() {
    Player player = new Player.Builder()
        .addToInventory(Arrays.asList("Sword", "Shield"))
        .build();

    player.removeFromInventory("Sword");

    assertEquals(Arrays.asList("Shield"), player.getInventory());
  }

  @Test
  public void testRemoveFromInventorymethodWithNullItem() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Player player = new Player.Builder()
              .build();
          player.removeFromInventory(null);
        });
  }
}