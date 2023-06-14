package no.ntnu.ie.iir.bidata.idata2001.gruppe10.view.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.controller.GameSceneController;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.controller.MainMenuAppController;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Link;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Passage;

/**
 * represents a scene for the game.
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class GameScene {
  private GameSceneController gameSceneController;
  private MainMenuAppController mainMenuAppController;
  private Label passageTitleLabel;
  private Label passageContentLabel;
  private Label playerLabel;
  private Label goalLabel;
  private HBox linkButtons;
  private BorderPane passageLabels;

  /**
   * Constructs an instance of the GameScene.
   *
   * @param mainMenuAppController the main application controller.
   */
  public GameScene(MainMenuAppController mainMenuAppController) {
    this.mainMenuAppController = mainMenuAppController;
    this.gameSceneController = new GameSceneController(this, this.mainMenuAppController);
    this.linkButtons = new HBox(20);
    this.passageLabels = new BorderPane();
  }

  /**
   * Creates and returns the game scene window.
   *
   * @return the game scene window.
   */
  public Scene createGameScene() {
    BorderPane borderPane = new BorderPane();
    final Passage passage = this.mainMenuAppController.getGame()
        .getStory().getOpeningPassage();

    MenuBar menuBar = createMenuBar();
    borderPane.setTop(menuBar);

    this.playerLabel = new Label(
        "Name: " + this.mainMenuAppController.getGame().getPlayer().getName()
            + "\nHealth: " + this.mainMenuAppController.getGame().getPlayer().getHealth()
            + "\nGold: " + this.mainMenuAppController.getGame().getPlayer().getGold()
            + "\nScore: " + this.mainMenuAppController.getGame().getPlayer().getScore()
            + "\nInventory: " + this.mainMenuAppController.getGame().getPlayer()
            .getInventoryAsString());

    this.goalLabel = new Label(
        "Goals: "
            + "\nHealth Goal: "
            + this.mainMenuAppController.getSpecificGoal("health goal").getGoalValueAsString()
            + "\nGold Goal: "
            + this.mainMenuAppController.getSpecificGoal("gold goal").getGoalValueAsString()
            + "\nScore Goal: "
            + this.mainMenuAppController.getSpecificGoal("score goal").getGoalValueAsString()
            + "\nInventory Goal: "
            + this.mainMenuAppController.getSpecificGoal("inventory goal").getGoalValueAsString());

    final Label storyTitleLabel = new Label(
        this.mainMenuAppController.getGame().getStory().getTitle());
    storyTitleLabel.setFont(new Font("Arial", 34));
    storyTitleLabel.setPadding(new Insets(30, 30, 100, 30));
    storyTitleLabel.setWrapText(true);
    storyTitleLabel.setAlignment(Pos.CENTER);
    storyTitleLabel.setTextAlignment(TextAlignment.CENTER);

    this.passageTitleLabel = new Label(passage.getTitle());
    passageTitleLabel.setFont(new Font("Arial", 24));

    this.passageContentLabel = new Label(passage.getContent());
    passageContentLabel.setFont(new Font("Arial", 16));
    passageContentLabel.setWrapText(true);
    passageContentLabel.setTextAlignment(TextAlignment.CENTER);

    this.createLinkButtons(passage);

    VBox passageContent = new VBox();
    passageContent.getChildren().addAll(storyTitleLabel, passageTitleLabel, passageContentLabel);

    passageLabels.setCenter(passageContent);

    passageContent.setAlignment(Pos.CENTER);

    passageLabels.setLeft(playerLabel);
    BorderPane.setAlignment(playerLabel, Pos.CENTER);
    playerLabel.setFont(new Font("Arial", 24));
    BorderPane.setMargin(playerLabel, new Insets(20, 20, 20, 100));

    passageLabels.setRight(goalLabel);
    BorderPane.setAlignment(goalLabel, Pos.CENTER);
    goalLabel.setFont(new Font("Arial", 24));
    BorderPane.setMargin(goalLabel, new Insets(20, 100, 20, 20));

    borderPane.setCenter(passageLabels);

    this.createLinkButtons(passage);
    borderPane.setBottom(this.linkButtons);
    linkButtons.setAlignment(Pos.CENTER);
    BorderPane.setMargin(this.linkButtons, new Insets(20, 20, 100, 20));

    return new Scene(borderPane, 1200, 600);
  }

  /**
   * Returns and creates the menu bar.
   * 
   * <p>
   * The menu bar contains the File, View and Help menus.
   * </p>
   *
   * @return the menu bar.
   */
  private MenuBar createMenuBar() {
    final MenuBar menuBar = new MenuBar();

    final Menu fileMenu = new Menu("File");
    MenuItem exitMenuItem = new MenuItem("Exit");
    exitMenuItem.setOnAction(event -> this.mainMenuAppController.doExitApplication());
    fileMenu.getItems().add(exitMenuItem);

    final Menu viewMenu = new Menu("View");
    final MenuItem mainMenuItem = new MenuItem("Main Menu");
    mainMenuItem
        .setOnAction(event -> this.mainMenuAppController.switchScene(
          this.mainMenuAppController.getMainMenuScene()));
    viewMenu.getItems().addAll(mainMenuItem);

    final Menu helpMenu = new Menu("Help");
    MenuItem aboutMenuItem = new MenuItem("About");
    aboutMenuItem.setOnAction(event -> this.mainMenuAppController.aboutAlert());
    helpMenu.getItems().addAll(aboutMenuItem);

    menuBar.getMenus().add(fileMenu);
    menuBar.getMenus().add(viewMenu);
    menuBar.getMenus().add(helpMenu);

    return menuBar;
  }

  /**
   * Creates link buttons to the current passage.
   *
   * @param passage the current passage.
   */
  public void createLinkButtons(Passage passage) {
    this.linkButtons.getChildren().clear();
    for (Link link : passage.getLinks()) {
      Button button = new Button(link.getText());
      button.setPrefSize(200, 50);
      button.setOnAction(event -> this.gameSceneController.followLinkMiniGame(link));
      this.linkButtons.getChildren().add(button);
    }
  }

  /**
   * Updates the passage labels to the current passage.
   *
   * @param passage the current passage.
   */
  public void updatePassage(Passage passage) {
    this.passageTitleLabel.setText(passage.getTitle());
    this.passageContentLabel.setText(passage.getContent());
  }

  /**
   * Updates the player label to the current passage.
   */
  public void updatePlayer() {
    this.playerLabel.setText("Name: " + this.mainMenuAppController.getGame().getPlayer().getName()
        + "\nHealth: " + this.mainMenuAppController.getGame().getPlayer().getHealth()
        + "\nGold: " + this.mainMenuAppController.getGame().getPlayer().getGold()
        + "\nScore: " + this.mainMenuAppController.getGame().getPlayer().getScore()
        + "\nInventory: " + this.mainMenuAppController.getGame().getPlayer()
        .getInventoryAsString());

  }

  /**
   * Returns the controller of the GameScene.
   *
   * @return the controller of the GameScene.
   */
  public GameSceneController getGameSceneController() {
    return this.gameSceneController;
  }
}