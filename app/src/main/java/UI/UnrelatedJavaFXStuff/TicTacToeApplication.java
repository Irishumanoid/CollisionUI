package UI;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TicTacToeApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        BorderPane bPane = new BorderPane();
        Label turnStatus = new Label("Turn: X");
        turnStatus.setFont(Font.font("Monospaced", 40));
        GridPane gameBoard = new GridPane();
        Label gameEnd = new Label(" ");

        bPane.setTop(turnStatus);
        bPane.setCenter(gameBoard);
        bPane.setBottom(gameEnd);


        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button(" ");
                button.setFont(Font.font("Monospaced", 40));
                gameBoard.add(button, i, j);

                button.setOnAction((event) -> {
                    if (turnStatus.getText().equals("Turn: X") && button.getText().equals(" ")) {
                        button.setText("O");
                        button.setStyle("-fx-background-color: Purple");
                        turnStatus.setText("Turn: O");
                    } else if (turnStatus.getText().equals("Turn: O") && button.getText().equals(" ")) {
                        button.setText("X");
                        button.setStyle("-fx-background-color: Green");
                        turnStatus.setText("Turn: X");
                    }
                });
            }
        }

        if(hasWon(gameBoard)) {
            gameEnd.setText("The End!");
        }

        Scene scene = new Scene(bPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static String getText(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == null || GridPane.getColumnIndex(node) == null) {
                continue;
            } else if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return ((Labeled) node).getText(); //nodes are only buttons in this instance so cast should be okay
            }
        }
        return null;
    }

    Boolean hasWon(GridPane gridPane) {
        for (int i = 0; i < 3; i++) {
            if (!getText(gridPane, i, 0).equals(" ") && 
                getText(gridPane, i, 0).equals(getText(gridPane, i, 1)) &&
                getText(gridPane, i, 0).equals(getText(gridPane, i, 2))) {
                    return true;
            } else if (!getText(gridPane, i, 0).equals(" ") &&
                getText(gridPane, 0, i).equals(getText(gridPane, 1, i)) &&
                getText(gridPane, i, 0).equals(getText(gridPane, 2, i))) {
                    return true;
            }
        }
        if (!getText(gridPane, 0, 0).equals(" ") &&
            getText(gridPane, 0, 0).equals(getText(gridPane, 1, 1)) &&
            getText(gridPane, 0, 0).equals(getText(gridPane, 2, 2))) {
                return true;
        } else if ((!getText(gridPane, 0, 2).equals(" ") &&
             getText(gridPane, 0, 2).equals(getText(gridPane, 1, 1)) &&
             getText(gridPane, 0, 2).equals(getText(gridPane, 2, 0)))) {
                return true;
        } else {
            return false;
        }
    }
    
}
