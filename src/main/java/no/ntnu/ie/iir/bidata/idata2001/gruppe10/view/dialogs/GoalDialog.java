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
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal.Goal;
import no.ntnu.ie.iir.bidata.idata2001.gruppe10.model.goal.GoalFactory;

/**
 * A custom made dialog for gathering information about a player so that an
 * instance of {@link Player} can be cerated (if the details provided are
 * valid).
 *
 * @author Vegard Arnesen Mytting
 * @version 2023-05-23
 */
public class GoalDialog extends Dialog<List<Goal>> {
  /**
   * Creates an instance of the dialog.
   */
  public GoalDialog() {
    super();
    this.createContent();
  }

  /**
   * Creates the content of the dialog.
   */
  private void createContent() {
    setTitle("New Goal Details");
    ButtonType applyButton = new ButtonType("Apply Goals");
    getDialogPane().getButtonTypes().addAll(applyButton, ButtonType.CANCEL);

    TextField healthField = new TextField();
    healthField.setPromptText("E.g 0 - 100");

    healthField.textProperty().addListener((observable, oldValue, newValue) -> {
      try {
        if (newValue.length() > 0) {
          Integer.parseInt(newValue);
        }
      } catch (NumberFormatException nfe) {
        healthField.setText(oldValue);
      }
    });

    TextField goldField = new TextField();
    goldField.setPromptText("E.g 0 - ...");

    goldField.textProperty().addListener((observable, oldValue, newValue) -> {
      try {
        if (newValue.length() > 0) {
          Integer.parseInt(newValue);
        }
      } catch (NumberFormatException nfe) {
        goldField.setText(oldValue);
      }
    });

    TextField scoreField = new TextField();
    scoreField.setPromptText("E.g 0 - ...");

    scoreField.textProperty().addListener((observable, oldValue, newValue) -> {
      try {
        if (newValue.length() > 0) {
          Integer.parseInt(newValue);
        }
      } catch (NumberFormatException nfe) {
        scoreField.setText(oldValue);
      }
    });

    TextField inventoryField = new TextField();
    inventoryField.setPromptText("E.g Artifact, Relic, ...");

    final Label label = new Label("""
        Please enter the health goal, gold goal, score goal and inventory goal to try to achive.
        If no values are registerd, all goals will be set to 0 and nothing.
        This means you will win the game automatically.
        """);

    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setPadding(new Insets(20, 20, 20, 20));
    gridPane.add(label, 0, 0);
    gridPane.add(new Label("Health Goal:"), 0, 1);
    gridPane.add(healthField, 1, 1);
    gridPane.add(new Label("Gold Goal:"), 0, 2);
    gridPane.add(goldField, 1, 2);
    gridPane.add(new Label("Score Goal:"), 0, 3);
    gridPane.add(scoreField, 1, 3);
    gridPane.add(new Label("Inventory Goal:"), 0, 4);
    gridPane.add(inventoryField, 1, 4);
    getDialogPane().setContent(gridPane);

    setResultConverter(
        (ButtonType button) -> {
          List<Goal> goals = new LinkedList<>();
          if (button == applyButton) {

            int health;
            try {
              health = Integer.parseInt(healthField.getText());
            } catch (NumberFormatException nfe) {
              health = 0;
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
              String[] inventoryStrings = inventoryField.getText().split(",");
              for (int i = 0; i < inventoryStrings.length; i++) {
                if (!inventoryStrings[i].isBlank()
                    && !inventoryList.contains(inventoryStrings[i].strip())) {
                  inventoryList.add(inventoryStrings[i].strip());
                }
              }
            } catch (IllegalArgumentException iae) {
              inventoryList = null;
            }

            try {
              goals.add(GoalFactory.createGoal("Health Goal".toLowerCase(), health));
              goals.add(GoalFactory.createGoal("Gold Goal".toLowerCase(), gold));
              goals.add(GoalFactory.createGoal("Score Goal".toLowerCase(), score));
              goals.add(GoalFactory.createGoal("Inventory Goal".toLowerCase(), inventoryList));
            } catch (IllegalArgumentException iae) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Error");
              alert.setHeaderText("Could not create Goals");
              alert.setContentText(iae.getMessage());
              alert.showAndWait();
            }
          }
          return goals;
        });
  }
}