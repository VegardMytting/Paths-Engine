package no.ntnu.ie.iir.bidata.idata2001.gruppe10.view.scenes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.controller.MainMenuAppController;

/**
 * Represents a scene for how to play.
 *
 * @version Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class HowToPlayScene {
  private MainMenuAppController mainMenuAppController;

  private static final String DATA_DIRECTORY = "src/main/resources/TutorialInstructions.txt/";

  /**
   * Constructs an instance of the HowToPlayScene.
   *
   * @param mainMenuAppController the main application menu.
   */
  public HowToPlayScene(MainMenuAppController mainMenuAppController) {
    this.mainMenuAppController = mainMenuAppController;
  }

  /**
   * Creates and returns the how to play scene window.
   *
   * @return the how to play scene window.
   */
  public Scene createHowToPlayScene() {
    final BorderPane borderPane = new BorderPane();
    final VBox vBox = new VBox();
    final Insets insets = new Insets(10, 10, 10, 10);
    vBox.setSpacing(20);

    TextArea textArea = new TextArea();
    textArea.setEditable(false);
    borderPane.setCenter(textArea);
    BorderPane.setAlignment(textArea, Pos.CENTER);
    BorderPane.setMargin(textArea, insets);

    try {
      String file = Files.readString(Paths.get(DATA_DIRECTORY));
      textArea.setText(file);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    Button mainMenuButton = new Button("Main Menu");
    mainMenuButton.setOnAction(event -> this.mainMenuAppController
        .switchScene(this.mainMenuAppController.getMainMenuScene()));
    mainMenuButton.setPrefSize(200, 50);
    mainMenuButton.setPadding(insets);
    borderPane.setBottom(mainMenuButton);
    BorderPane.setAlignment(mainMenuButton, Pos.TOP_RIGHT);
    BorderPane.setMargin(mainMenuButton, insets);

    final VBox menuBarVbox = new VBox();
    borderPane.setTop(menuBarVbox);
    MenuBar menuBar = createMenuBar();
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
}