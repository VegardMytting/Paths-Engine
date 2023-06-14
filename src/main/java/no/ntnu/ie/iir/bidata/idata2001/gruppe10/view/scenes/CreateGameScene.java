package no.ntnu.ie.iir.bidata.idata2001.gruppe10.view.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.controller.CreateGameSceneController;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.controller.MainMenuAppController;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.logic.Story;

/**
 * Represents a scene for creating a game.
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class CreateGameScene {
  private Stage stage;
  private TextField dataFileTextField;

  private Label allItemsInStoryLabel;

  private Label storyTitleLabel;
  private MainMenuAppController mainMenuAppController;
  private CreateGameSceneController createGameSceneController;

  private Label playerValueName;
  private Label playerValueHealth;
  private Label playerValueGold;
  private Label playerValueScore;
  private Label playerValueInventory;

  private Label goalValueHealth;
  private Label goalValueGold;
  private Label goalValueScore;
  private Label goalValueInventory;

  /**
   * Constructs an instance of the CreateGameScene.
   *
   * @param mainMenuAppController the main application controller.
   */
  public CreateGameScene(MainMenuAppController mainMenuAppController) {
    this.mainMenuAppController = mainMenuAppController;
    this.createGameSceneController = new CreateGameSceneController(
        this, this.mainMenuAppController);
  }

  /**
   * Returns the controller for the CreateGameScene.
   *
   * @return the controller for the CreateGameScene.
   */
  public CreateGameSceneController getCreateGameSceneController() {
    return this.createGameSceneController;
  }

  /**
   * Creates and returns the create game scene window.
   *
   * @return the create game scene window.
   */
  public Scene createCreateGameScene() {
    final BorderPane borderPane = new BorderPane();

    final VBox menuBarVbox = new VBox(createMenuBar());
    borderPane.setTop(menuBarVbox);

    final Button openFileButton = new Button("Open File");
    openFileButton.setOnAction(event -> this.createGameSceneController.doOpenFile(event));
    openFileButton.setPrefSize(200, 50);
    final Button saveFileButton = new Button("Save File");
    saveFileButton.setOnAction(event -> this.createGameSceneController.doSaveFile(event));
    saveFileButton.setPrefSize(200, 50);
    final Button createNewPlayerButton = new Button("Create New Player");
    createNewPlayerButton.setOnAction(event -> 
        this.createGameSceneController.doCreationOfPlayer(event));
    createNewPlayerButton.setPrefSize(200, 50);
    final Button createNewGoalsButton = new Button("Create New Goals");
    createNewGoalsButton.setOnAction(event -> 
        this.createGameSceneController.doCreationOfGoals(event));
    createNewGoalsButton.setPrefSize(200, 50);
    final Button mainMenuButton = new Button("Main Menu");
    mainMenuButton.setOnAction(event -> {
      this.mainMenuAppController.clearGame();
      this.mainMenuAppController.switchScene(this.mainMenuAppController.getMainMenuScene());
    });
    mainMenuButton.setPrefSize(200, 50);
    final CheckBox miniGameCheckBox = new CheckBox();
    miniGameCheckBox.setPrefSize(50, 50);
    Label miniGameLabel = new Label("Disable Mini-Games");
    miniGameLabel.setGraphic(miniGameCheckBox);
    miniGameLabel.setContentDisplay(ContentDisplay.RIGHT);
    final HBox miniGameRow = new HBox(20, miniGameLabel, miniGameCheckBox);

    final GridPane buttonGridPane = new GridPane();
    buttonGridPane.setHgap(20);
    buttonGridPane.setVgap(20);

    buttonGridPane.add(openFileButton, 0, 0);
    buttonGridPane.add(saveFileButton, 1, 0);

    buttonGridPane.add(createNewPlayerButton, 0, 1);
    buttonGridPane.add(createNewGoalsButton, 1, 1);

    buttonGridPane.add(miniGameRow, 0, 2);
    buttonGridPane.add(mainMenuButton, 1, 2);

    final Label createGameInfo = new Label(" • Open a file to select a story "
            + "\n • Click 'Create New Player' to create a new character"
            + "\n • CLick 'Create New Goals' to set your requirements to win");

    final VBox setting = new VBox(createGameInfo, new Separator(), buttonGridPane);
    setting.setSpacing(40);
    setting.setAlignment(Pos.CENTER);
    setting.setPadding(new Insets(20, 20, 20, 100));


    buttonGridPane.setAlignment(Pos.CENTER);
    BorderPane.setMargin(buttonGridPane, new Insets(20, 20, 20, 100));

    this.storyTitleLabel = new Label();

    this.dataFileTextField = new TextField();
    this.dataFileTextField.setPrefSize(600, 20);
    dataFileTextField.setEditable(false);

    this.allItemsInStoryLabel = new Label();

    final Label playerAttributes = new Label("Player Attributes");
    final Label playerAttributeName = new Label("Name: ");
    final Label playerAttributeHealth = new Label("Health: ");
    final Label playerAttributeGold = new Label("Gold: ");
    final Label playerAttributeScore = new Label("Score: ");
    final Label playerAttributeInventory = new Label("Inventory: ");

    final Label playerValues = new Label("Player Values");
    this.playerValueName = new Label();
    this.playerValueHealth = new Label();
    this.playerValueGold = new Label();
    this.playerValueScore = new Label();
    this.playerValueInventory = new Label();

    final Label goalTypes = new Label("Goal Types");
    final Label goalTypeHealth = new Label("Health Goal: ");
    final Label goalTypeGold = new Label("Gold Goal: ");
    final Label goalTypeScore = new Label("Score Goal: ");
    final Label goalTypeInventory = new Label("Inventory Goal: ");

    final Label goalValues = new Label("Goal Values");
    this.goalValueHealth = new Label();
    this.goalValueGold = new Label();
    this.goalValueScore = new Label();
    this.goalValueInventory = new Label();

    final GridPane userInputGridPane = new GridPane();
    userInputGridPane.setHgap(20);
    userInputGridPane.setVgap(20);

    userInputGridPane.add(playerAttributes, 0, 0);
    userInputGridPane.add(playerAttributeName, 0, 1);
    userInputGridPane.add(playerAttributeHealth, 0, 2);
    userInputGridPane.add(playerAttributeGold, 0, 3);
    userInputGridPane.add(playerAttributeScore, 0, 4);
    userInputGridPane.add(playerAttributeInventory, 0, 5);

    userInputGridPane.add(playerValues, 1, 0);
    userInputGridPane.add(playerValueName, 1, 1);
    userInputGridPane.add(this.playerValueHealth, 1, 2);
    userInputGridPane.add(this.playerValueGold, 1, 3);
    userInputGridPane.add(this.playerValueScore, 1, 4);
    userInputGridPane.add(this.playerValueInventory, 1, 5);

    userInputGridPane.add(goalTypes, 2, 0);
    userInputGridPane.add(goalTypeHealth, 2, 2);
    userInputGridPane.add(goalTypeGold, 2, 3);
    userInputGridPane.add(goalTypeScore, 2, 4);
    userInputGridPane.add(goalTypeInventory, 2, 5);

    userInputGridPane.add(goalValues, 3, 0);
    userInputGridPane.add(this.goalValueHealth, 3, 2);
    userInputGridPane.add(this.goalValueGold, 3, 3);
    userInputGridPane.add(this.goalValueScore, 3, 4);
    userInputGridPane.add(this.goalValueInventory, 3, 5);

    userInputGridPane.setAlignment(Pos.CENTER);
    BorderPane.setMargin(userInputGridPane, new Insets(20, 100, 20, 20));

    final Button playButton = new Button("Play");
    playButton.setOnAction(event -> {
      if (miniGameCheckBox.isSelected()) {
        this.mainMenuAppController.setMiniGameStatus(false);
      } else {
        this.mainMenuAppController.setMiniGameStatus(true);
      }
      this.createGameSceneController.startGame(event);
      this.createGameSceneController.getGameScene().getGameSceneController().victoryDialog();
    });
    playButton.setPrefSize(400, 75);

    final VBox infoBox = new VBox(20,
        this.storyTitleLabel, new Separator(), this.dataFileTextField, new Separator(), 
        this.allItemsInStoryLabel, new Separator(), userInputGridPane, new Separator(), playButton);


    infoBox.setAlignment(Pos.CENTER);
    BorderPane.setMargin(infoBox, new Insets(20, 100, 20, 20));

    final HBox centerBox = new HBox(setting, infoBox);
    centerBox.setSpacing(100);
    centerBox.setAlignment(Pos.CENTER);

    borderPane.setCenter(centerBox);

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
    mainMenuItem.setOnAction(event -> {
      this.mainMenuAppController.setPlayer(null);
      this.mainMenuAppController.setStory(null);
      this.mainMenuAppController.setGoals(null);
      this.mainMenuAppController.switchScene(this.mainMenuAppController.getMainMenuScene());
    });

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
   * Displays the data file.
   *
   * @param dataFile the data file.
   */
  public void showDataFile(String dataFile) {
    this.dataFileTextField.setText(dataFile);
  }

  /**
   * Displays the number of passages in the story.
   *
   * @param story the story object containing the passages.
   */
  public void showAllItemsInStory(Story story) {
    this.allItemsInStoryLabel.setText("List of avaiable items in the story:\n"
        + this.getCreateGameSceneController().getFileHandler().getAllItemsAsString());
  }

  /**
   * Returns the story title label label.
   *
   * @return the story title label label.
   */
  public Label getStoryTitLabel() {
    return this.storyTitleLabel;
  }

  /**
   * returns the player's name label.
   *
   * @return the player's name label.
   */
  public Label getPlayerValueName() {
    return this.playerValueName;
  }

  /**
   * Returns the player's health label.
   *
   * @return the player's health label.
   */
  public Label getPlayerValueHealth() {
    return this.playerValueHealth;
  }

  /**
   * Returns the player's gold label.
   *
   * @return the player's gold label.
   */
  public Label getPlayerValueGold() {
    return this.playerValueGold;
  }

  /**
   * Returns the player's score label.
   *
   * @return the player's score label.
   */
  public Label getPlayerValueScore() {
    return this.playerValueScore;
  }

  /**
   * Returns the player's inventory label.
   *
   * @return the player's inventory label.
   */
  public Label getPlayerValueInventory() {
    return this.playerValueInventory;
  }

  /**
   * Returns the health goal label.
   *
   * @return the health goal label.
   */
  public Label getGoalValueHealth() {
    return this.goalValueHealth;
  }

  /**
   * Returns the gold goal label.
   *
   * @return the gold goal label.
   */
  public Label getGoalValueGold() {
    return this.goalValueGold;
  }

  /**
   * Returns the score goal label.
   *
   * @return the score goal label.
   */
  public Label getGoalValueScore() {
    return this.goalValueScore;
  }

  /**
   * Returns the inventory goal label.
   *
   * @return the inventory goal label.
   */
  public Label getGoalValueInventory() {
    return this.goalValueInventory;
  }

  /**
   * Returns the stage.
   *
   * @return the stage.
   */
  public Stage getStage() {
    return this.stage;
  }
}