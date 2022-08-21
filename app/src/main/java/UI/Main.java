package UI;

import UI.UnrelatedJavaFXStuff.TicTacToeApplication;
import javafx.application.Application;

import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) throws Exception {launch(TicTacToeApplication.class); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField textField = new TextField();
        Button button = new Button("Click me!");
        Label newLabel = new Label("Update");
        VBox group = new VBox();
        group.setSpacing(20);
        group.getChildren().addAll(textField, button, newLabel);

        
        button.setOnAction(actionEvent -> newLabel.setText(textField.getText()));

        TextField text = new TextField();

        /*text.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> change, String newValue, String oldValue) {
                this.text = new TextField();
                System.out.println(oldValue + "->" + newValue);
                text.setText(newValue);
            }
        });*/

        text.textProperty().addListener((change, newValue, oldValue) -> {
            System.out.println(oldValue +" -> "+newValue);
            text.setText(newValue);
        });

        Scene newScene = new Scene(group);
        primaryStage.setScene(newScene);
        
        primaryStage.show();
    }
}

