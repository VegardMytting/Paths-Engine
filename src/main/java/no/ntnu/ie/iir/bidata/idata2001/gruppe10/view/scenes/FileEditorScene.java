package no.ntnu.ie.iir.bidata.idata2001.gruppe10.view.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.controller.EditFileSceneController;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.controller.MainMenuAppController;

/**
 * Represents a scene for file editing.
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class FileEditorScene {
  private EditFileSceneController editFileSceneController;
  private MainMenuAppController mainMenuAppController;
  private Stage stage;
  private TextArea textArea;

  /**
   * Constructs an instance of the FileEditorScene.
   *
   * @param mainMenuAppController the main application controller.
   */
  public FileEditorScene(MainMenuAppController mainMenuAppController) {
    this.mainMenuAppController = mainMenuAppController;
    this.editFileSceneController = new EditFileSceneController(this);
  }

  /**
   * Creates and returns the file editor scene window.
   *
   * @return the file editor scene window.
   */
  public Scene createEditFileScene() {
    final BorderPane borderPane = new BorderPane();
    final Insets insets = new Insets(10, 10, 10, 10);
    final VBox menuBarVbox = new VBox();

    this.textArea = new TextArea();
    this.textArea.setEditable(true);
    borderPane.setCenter(this.textArea);
    BorderPane.setAlignment(this.textArea, Pos.CENTER);
    BorderPane.setMargin(this.textArea, insets);

    Button openFileButton = new Button("Open File");
    openFileButton.setOnAction(event -> this.editFileSceneController.openFile(event));
    openFileButton.setPrefSize(200, 50);
    openFileButton.setPadding(insets);

    Button saveFileButton = new Button("Save File");
    saveFileButton.setOnAction(event -> this.editFileSceneController.saveFile(event));
    saveFileButton.setPrefSize(200, 50);
    saveFileButton.setPadding(insets);

    Button createFileButton = new Button("Create File");
    createFileButton.setOnAction(event -> this.editFileSceneController.createFile(event));
    createFileButton.setPrefSize(200, 50);
    createFileButton.setPadding(insets);

    Button mainMenuButton = new Button("Main Menu");
    mainMenuButton.setOnAction(event -> this.mainMenuAppController
        .switchScene(this.mainMenuAppController.getMainMenuScene()));
    mainMenuButton.setPrefSize(200, 50);
    mainMenuButton.setPadding(insets);

    HBox buttonRow = new HBox(20);
    buttonRow.getChildren().addAll(
        openFileButton, saveFileButton, createFileButton, mainMenuButton);
    borderPane.setBottom(buttonRow);
    BorderPane.setAlignment(buttonRow, Pos.TOP_RIGHT);
    BorderPane.setMargin(buttonRow, insets);

    MenuBar menuBar = createMenuBar();
    borderPane.setTop(menuBarVbox);
    menuBarVbox.getChildren().add(menuBar);

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
    mainMenuItem.setOnAction(event -> 
        this.mainMenuAppController.switchScene(this.mainMenuAppController.getMainMenuScene()));
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
   * Returns the stage.
   *
   * @return the stage.
   */
  public Stage getStage() {
    return this.stage;
  }

  /**
   * Returns the TextArea of the scene.
   *
   * @return the TextArea of the scene.
   */
  public TextArea getTextArea() {
    return this.textArea;
  }

  /**
   * Returns the controller of the FileEditorScene.
   *
   * @return the controller of the FileEditorScene.
   */
  public EditFileSceneController getEditFileSceneController() {
    return this.editFileSceneController;
  }
}