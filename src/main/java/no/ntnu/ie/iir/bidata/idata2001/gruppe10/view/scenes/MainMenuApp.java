package no.ntnu.ie.iir.bidata.idata2001.gruppe10.view.scenes;

import java.io.IOException;
import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.controller.MainMenuAppController;

/**
 * The main application window.
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class MainMenuApp extends Application {
  private Stage primaryStage;
  private CreateGameScene createGameScene;
  private HowToPlayScene howToPlayScene;
  private FileEditorScene fileEditorScene;
  private MainMenuAppController mainMenuAppController;

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;

    this.mainMenuAppController = new MainMenuAppController(this, this.primaryStage);

    this.primaryStage.setOnCloseRequest(event -> {
      event.consume();
      this.mainMenuAppController.doExitApplication();
    });

    this.mainMenuAppController.switchScene(createMainMenuScene());
    primaryStage.setTitle("Paths");
    primaryStage.show();
  }

  /**
   * Creates and returns the main menu scene window.
   *
   * @return the main menu scene window.
   */
  public Scene createMainMenuScene() {
    this.createGameScene = new CreateGameScene(this.mainMenuAppController);

    this.howToPlayScene = new HowToPlayScene(this.mainMenuAppController);

    this.fileEditorScene = new FileEditorScene(this.mainMenuAppController);

    BorderPane borderPane = new BorderPane();
    final VBox vBox = new VBox();

    try {
      this.createGameScene.getCreateGameSceneController().createDataDirectory();
    } catch (IOException ioe) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Error creating data directory");
      alert.setContentText(ioe.getMessage());
      alert.showAndWait();
      Platform.exit();
    }

    MenuBar menuBar = createMenuBar();
    borderPane.setTop(vBox);
    vBox.getChildren().add(menuBar);

    borderPane.setCenter(createMainMenuButtons());

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

    final Menu helpMenu = new Menu("Help");
    MenuItem aboutMenuItem = new MenuItem("About");
    aboutMenuItem.setOnAction(event -> this.mainMenuAppController.aboutAlert());
    helpMenu.getItems().addAll(aboutMenuItem);

    menuBar.getMenus().add(fileMenu);
    menuBar.getMenus().add(helpMenu);

    return menuBar;
  }

  /**
   * Creates and returns the main funcional buttons of the application.
   *
   * @return the main funcional buttons of the application.
   */
  private VBox createMainMenuButtons() {
    final Button newGameButton = new Button("New Game");
    newGameButton.setOnAction(event -> {
      if (this.mainMenuAppController.getPlayer() != null
          || this.mainMenuAppController.getStory() != null
          || this.mainMenuAppController.getGoals() != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to create a new game?");
        alert.setContentText("If you create a new game, your previous game will be deleted.");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
          this.mainMenuAppController.clearGame();
          this.mainMenuAppController.setGame(null);
          this.mainMenuAppController.switchScene(
              this.createGameScene.createCreateGameScene());
        }
      } else {
        this.mainMenuAppController.clearGame();
        this.mainMenuAppController.switchScene(
            this.createGameScene.createCreateGameScene());
      }
    });

    final Button continueGameButton = new Button("Continue Game");
    continueGameButton
        .setOnAction(event -> this.createGameScene.getCreateGameSceneController()
            .getGameScene()
            .getGameSceneController()
            .continueGame());
    if (this.mainMenuAppController.getGame() == null
        || this.mainMenuAppController.getPlayer() == null
        || this.mainMenuAppController.getStory() == null
        || this.mainMenuAppController.getGoals() == null) {
      continueGameButton.setDisable(true);
    }

    final Button howToPlayButton = new Button("How to Play");
    howToPlayButton.setOnAction(event -> this.mainMenuAppController.switchScene(
        this.howToPlayScene.createHowToPlayScene()));

    final Button editFileButton = new Button("File Editor");
    editFileButton.setOnAction(event -> this.mainMenuAppController.switchScene(
        this.fileEditorScene.createEditFileScene()));

    newGameButton.setPrefSize(200, 50);
    continueGameButton.setPrefSize(200, 50);
    howToPlayButton.setPrefSize(200, 50);
    editFileButton.setPrefSize(200, 50);

    Label welcomText = new Label("Welcome to Paths");
    welcomText.setFont(new Font(50));

    Label helpText = new Label("Select new game to start playing");
    helpText.setFont(new Font(18));
    ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(4), helpText);
    scaleTransition.setFromX(1);
    scaleTransition.setToX(1.1);
    scaleTransition.setAutoReverse(true);
    scaleTransition.setCycleCount(Animation.INDEFINITE);
    scaleTransition.play();

    VBox mainMenuButtons = new VBox(20);
    mainMenuButtons.getChildren().addAll(welcomText, newGameButton, continueGameButton,
        howToPlayButton, editFileButton, helpText);
    mainMenuButtons.setAlignment(Pos.CENTER);

    return mainMenuButtons;
  }

  /**
   * Returns the FileEditor.
   *
   * @return the FileEditor.
   */
  public FileEditorScene getFileEditorScene() {
    return this.fileEditorScene;
  }

  public static void appLauncher(String[] args) {
    launch(args);
  }
}