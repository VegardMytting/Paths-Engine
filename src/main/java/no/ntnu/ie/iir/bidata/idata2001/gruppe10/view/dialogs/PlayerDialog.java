package no.ntnu.ie.iir.bidata.idata2001.gruppe10.view.dialogs;

import java.util.LinkedList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.data.Player;

/**
 * A custom made dialog for garthering information about goals so that an
 * instances of the interface {@link Goal} can be cerated (if the details are
 * valid).
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class PlayerDialog extends Dialog<Player> {

  /**
   * Creates an instance of the dialog.
   */
  public PlayerDialog() {
    super();
    this.createContent();
  }

  /**
   * Creates the content of the dialog.
   */
  private void createContent() {
    setTitle("Player Details");
    ButtonType applyButton = new ButtonType("Apply Player");
    getDialogPane().getButtonTypes().addAll(applyButton, ButtonType.CANCEL);

    final Label label = new Label("""
        Please enter the name, health, gold, score and inventory for the player to be played.
        You must choose at least a name. If no other values are registered, all player values
        will be set to 0 and nothing.
        """);

    TextField nameField = new TextField();
    nameField.setPromptText("E.g Arne");

    TextField healthField = new TextField();
    healthField.setPromptText("E.g 1 - 100");

    TextField goldField = new TextField();
    goldField.setPromptText("E.g 0 - ...");

    TextField scoreField = new TextField();
    scoreField.setPromptText("E.g 0 - ...");

    TextField inventoryField = new TextField();
    inventoryField.setPromptText("E.g Artifact, Relic, ...");

    healthField.textProperty().addListener((observable, oldValue, newValue) -> {
      try {
        if (newValue.length() > 0) {
          Integer.parseInt(newValue);
        }
      } catch (NumberFormatException nfe) {
        healthField.setText(oldValue);
      }
    });

    goldField.textProperty().addListener((observable, oldValue, newValue) -> {
      try {
        if (newValue.length() > 0) {
          Integer.parseInt(newValue);
        }
      } catch (NumberFormatException nfe) {
        goldField.setText(oldValue);
      }
    });

    scoreField.textProperty().addListener((observable, oldValue, newValue) -> {
      try {
        if (newValue.length() > 0) {
          Integer.parseInt(newValue);
        }
      } catch (NumberFormatException nfe) {
        scoreField.setText(oldValue);
      }
    });

    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setPadding(new Insets(20, 20, 20, 20));
    gridPane.add(label, 0, 0);
    gridPane.add(new Label("*Name:"), 0, 1);
    gridPane.add(nameField, 1, 1);
    gridPane.add(new Label("Health:"), 0, 2);
    gridPane.add(healthField, 1, 2);
    gridPane.add(new Label("Gold:"), 0, 3);
    gridPane.add(goldField, 1, 3);
    gridPane.add(new Label("Score:"), 0, 4);
    gridPane.add(scoreField, 1, 4);
    gridPane.add(new Label("Inventory:"), 0, 5);
    gridPane.add(inventoryField, 1, 5);
    getDialogPane().setContent(gridPane);

    setResultConverter(
        (ButtonType button) -> {
          Player player = null;
          if (button == applyButton) {

            int health;
            try {
              health = Integer.parseInt(healthField.getText());
            } catch (NumberFormatException nfe) {
              health = 100;
            }

            int gold;
            try {
              gold = Integer.parseInt(goldField.getText());
            } catch (NumberFormatException nfe) {
              gold = 0;
            }

            int score;
            try {
              score = Integer.parseInt(scoreField.getText());
            } catch (NumberFormatException nfe) {
              score = 0;
            }

            List<String> inventoryList = new LinkedList<>();
            try {
              String[] inventoryString = inventoryField.getText().split(",");
              for (int i = 0; i < inventoryString.length; i++) {
                if (!inventoryString[i].isBlank()
                    && !inventoryList.contains(inventoryString[i].strip())) {
                  inventoryList.add(inventoryString[i].strip());
                }
              }
            } catch (IllegalArgumentException iae) {
              inventoryList = null;
            }

            try {
              player = new Player.Builder()
                  .setName(nameField.getText())
                  .setHealth(health)
                  .setGold(gold)
                  .setScore(score)
                  .addToInventory(inventoryList)
                  .build();

            } catch (IllegalArgumentException iae) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Error");
              alert.setHeaderText("Could not create Player");
              alert.setContentText(iae.getMessage());
              alert.showAndWait();
            }
          }
          return player;
        });
  }
}