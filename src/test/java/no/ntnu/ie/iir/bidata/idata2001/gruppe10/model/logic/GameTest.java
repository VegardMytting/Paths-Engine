package no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Passage;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal.Goal;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal.GoalFactory;
import org.junit.jupiter.api.Test;

/**
 * Test the class Game.
 * <p>
 * The following positive test are performed:
 * CreationOfGame.
 * SetPlayer.
 * SetStory.
 * SetGoals.
 * Begin.
 * </p>
 * <p>
 * The following negative test are performed:
 * CreationOfGame.
 * SetPlayer.
 * SetStory.
 * SetGoals.
 * </p>
 */
public class GameTest {
  @Test
  public void testCreationOfGameWithValidParameters() {
    Player player = new Player.Builder()
        .build();
    Passage openingPassage = new Passage("Title", "Content");
    Story story = new Story("Title", openingPassage);
    Goal goal = GoalFactory.createGoal("gold goal", 10);
    Game game = new Game(player, story, Arrays.asList(goal));
    assertEquals(player, game.getPlayer());
    assertEquals(story, game.getStory());
    assertEquals(Arrays.asList(goal), game.getGoals());
  }

  @Test
  public void testCreationOfGameWithNullPlayer() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Passage openingPassage = new Passage("Title", "Content");
          Story story = new Story("Title", openingPassage);
          Goal goal = GoalFactory.createGoal("gold goal", 10);
          new Game(null, story, Arrays.asList(goal));
        });
  }

  @Test
  public void testCreationOfGameWithNullStory() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Player player = new Player.Builder()
              .build();
          Goal goal = GoalFactory.createGoal("gold goal", 10);
          new Game(player, null, Arrays.asList(goal));
        });
  }

  @Test
  public void testCreationOfGameWithNullGoals() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Player player = new Player.Builder()
              .build();
          Passage openingPassage = new Passage("Title", "Content");
          Story story = new Story("Title", openingPassage);
          GoalFactory.createGoal("gold goal", 10);
          new Game(player, story, null);
        });
  }

  @Test
  public void testCreationOfGameWithNullParameters() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          new Player.Builder()
              .build();
          Passage openingPassage = new Passage("Title", "Content");
          new Story("Title", openingPassage);
          GoalFactory.createGoal("gold goal", 10);
          new Game(null, null, null);
        });
  }

  @Test
  public void testSetPlayerMethodOfGameWithValidParameters() {
    Player player1 = new Player.Builder()
        .build();
    Player player2 = new Player.Builder()
        .build();
    Passage openingPassage = new Passage("Title", "Content");
    Story story = new Story("Title", openingPassage);
    Goal goal = GoalFactory.createGoal("gold goal", 10);
    Game game = new Game(player1, story, Arrays.asList(goal));
    game.setPlayer(player2);
    assertEquals(player2, game.getPlayer());

  }

  @Test
  public void testSetPlayerMethodOfGameWithInvalidParameters() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Player player = new Player.Builder()
              .build();
          Passage openingPassage = new Passage("Title", "Content");
          Story story = new Story("Title", openingPassage);
          Goal goal = GoalFactory.createGoal("gold goal", 10);
          Game game = new Game(player, story, Arrays.asList(goal));
          game.setPlayer(null);
        });
  }

  @Test
  public void testSetStoryMethodOfGameWithValidParameters() {
    Player player = new Player.Builder()
        .build();
    Passage openingPassage = new Passage("Title", "Content");
    Story story = new Story("Title", openingPassage);
    Story story2 = new Story("Title", openingPassage);
    Goal goal = GoalFactory.createGoal("gold goal", 10);
    Game game = new Game(player, story, Arrays.asList(goal));
    game.setStory(story2);
    assertEquals(story2, game.getStory());

  }

  @Test
  public void testSetStoryMethodOfGameWithNullParameters() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Player player = new Player.Builder()
              .build();
          Passage openingPassage = new Passage("Title", "Content");
          Story story = new Story("Title", openingPassage);
          Goal goal = GoalFactory.createGoal("gold goal", 10);
          Game game = new Game(player, story, Arrays.asList(goal));
          game.setStory(null);
        });
  }

  @Test
  public void testSetGoalsMethodOfGameWithValidParameters() {
    Player player = new Player.Builder()
        .build();
    Passage openingPassage = new Passage("Title", "Content");
    Story story = new Story("Title", openingPassage);
    Goal goal = GoalFactory.createGoal("gold goal", 10);
    Goal goal1 = GoalFactory.createGoal("health goal", 10);
    Game game = new Game(player, story, Arrays.asList(goal));
    game.setGoals(Arrays.asList(goal1));
    assertEquals(Arrays.asList(goal1), game.getGoals());
  }

  @Test
  public void testSetGoalsMethodOfGameWithNullParameters() {
    assertThrows(IllegalArgumentException.class,
        () -> {
          Player player = new Player.Builder()
              .build();
          Passage openingPassage = new Passage("Title", "Content");
          Story story = new Story("Title", openingPassage);
          Goal goal = GoalFactory.createGoal("gold goal", 10);
          Game game = new Game(player, story, Arrays.asList(goal));
          game.setGoals(null);
        });
  }

  @Test
  public void testbeginMethodOfGameWithValidParameters() {
    Player player = new Player.Builder()
        .build();
    Passage openingPassage = new Passage("Title", "Content");
    Story story = new Story("Title", openingPassage);
    Goal goal = GoalFactory.createGoal("gold goal", 10);
    Game game = new Game(player, story, Arrays.asList(goal));
    assertEquals(openingPassage, game.begin());
  }

}
