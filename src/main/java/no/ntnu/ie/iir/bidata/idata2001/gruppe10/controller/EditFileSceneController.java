package no.ntnu.ie.iir.bidata.idata2001.gruppe10.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.view.scenes.FileEditorScene;

/**
 * Represents a controller for {@link FileEditorScene}.
 * <p>
 * The controller is responsible for handling events in the scene. Events such as:
 * <ul>
 * <li>Opening a file from the file-explorer.</li>
 * <li>Re-writes the chosen file to the pre-determined data-directory.</li>
 * <li>Creates a new file to the pre-determined data-directory.</li>
 * </ul>
 * </p>
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class EditFileSceneController {
  private FileEditorScene fileEditorScene;
  private File file;

  private static final String DATA_DIRECTORY = "src/main/resources/saved-stories/";

  /**
   * Constructs an instance of the EditFIleSceneController.
   *
   * @param fileEditorScene the scene this controller is responsible for.
   */
  public EditFileSceneController(
      FileEditorScene fileEditorScene) {
    this.fileEditorScene = fileEditorScene;
  }
  
  /**
   * Opens a file-explorer and gives the user a choice to select a "*.paths" file.
   *
   * @param event the action triggered by the user.
   */
  public void openFile(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("Paths Files", "*.paths"));
    fileChooser.setInitialDirectory(Path.of(DATA_DIRECTORY).toFile());
    this.file = fileChooser.showOpenDialog(this.fileEditorScene.getStage());
    if (this.file != null) {
      try {
        String fileContent = Files.readString(this.file.toPath());
        this.fileEditorScene.getTextArea().setText(fileContent);
      } catch (IOException ioe) {
        ioe.getMessage();
      }
    }
  }

  /**
   * Re-writes a already selected file from the 
   * {@link EditFileSceneController#openFile(ActionEvent) openFile-method}
   * with the new changes the user has implemented in the TextArea.
   *
   * @param event the action triggered by the user.
   */
  public void saveFile(ActionEvent event) {
    if (this.file != null) {
      try (FileWriter fileWriter = new FileWriter(this.file)) {
        fileWriter.write(this.fileEditorScene.getTextArea().getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("File has been saved");
        alert.setContentText("File has been changed and is now ready to be played with!");
        alert.showAndWait();
      } catch (IOException ioe) {
        ioe.getMessage();
      }
    }
  }

  /**
   * Creates a new file to the pre-determined data-directory
   * with the text the user has written in the TextArea.
   * 
   * <p>
   * The title of the story will be the name of the file.
   * </p>
   *
   * @param event the action triggered by the user.
   */
  public void createFile(ActionEvent event) {
    String content = this.fileEditorScene.getTextArea().getText();
    String[] lines = content.split("\n");

    if (lines.length >= 1) {
      String fileName = lines[0].strip() + ".paths";
      String fileContent = content.strip();

      if (!fileName.isEmpty()) {
        File dataDirectory = new File(DATA_DIRECTORY);
        File newFile = new File(dataDirectory, fileName);

        try (FileWriter fileWriter = new FileWriter(newFile)) {
          fileWriter.write(fileContent);

          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Information");
          alert.setHeaderText("File has been added to the saved-stories folder");
          alert.setContentText("File has been added and is now ready to be played with!");
          alert.showAndWait();
        } catch (IOException ioe) {
          ioe.getMessage();
        }
      }
    }
  }

  /**
   * Sets the file as a file.
   *
   * @param file the file to set.
   */
  public void setFile(File file) {
    this.file = file;
  }
}