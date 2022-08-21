package UI.UnrelatedJavaFXStuff;

import java.util.Arrays;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


//to do: add key press and key release event handlers

public class TextEditor extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button button = new Button("Hello there");
        Label label = new Label("I'm a label");
        TextField field = new TextField("Bello");
        Timeline scheduler = new Timeline();
        GridPane pane = new GridPane();
        
        field.setPrefColumnCount(7);
        button.setOnAction(actionEvent -> button.setText("Thanks for clicking!"));
        
        FlowPane componentGroup = new FlowPane();
        componentGroup.getChildren().addAll(label, button, field, pane);

        for (int x = 1; x <= 3; x++) {
            for (int y = 1; y <= 3; y++) {
                Button b = new Button("button at (" + x+","+ y+")");
                pane.add(b, x, y);
                b.setOnAction(actionEvent -> b.setText("Clicked!"));
            }
        }

        scheduler.setOnFinished(actionEvent -> componentGroup.getChildren().remove(label));
        
        BorderPane layout = new BorderPane();
        TextArea text = new TextArea("");
        HBox stats = new HBox();
        Label label1 = new Label("Letters: 0 ");
        Label label2 = new Label("Words: 0 ");
        Label label3 = new Label("The longest word is: ");
        
        layout.setCenter(text);
        stats.getChildren().addAll(label1, label2, label3);
        layout.setBottom(stats);
        layout.setTop(button);

        csvread read = new csvread();

        text.textProperty().addListener((change, oldValue, newValue) -> {
            int characters = newValue.length();
            String[] parts = newValue.split(" ");
            int word = parts.length;
        
            //get the longest element of a string sorted by novelty
            String longest = Arrays.stream(parts) 
                .sorted((stringOld, stringNew) -> stringNew.length() - stringOld.length())
                .findFirst().get(); 

                label1.setText("Letters: " + characters + "  ");
                label2.setText("Words: " + word + "  ");
                label3.setText("The longest word is  " + longest);

                for (String part : parts) {
                    if (!read.englishWords().contains(part)) {
                        text.setStyle("-fx-text-fill: red");
                    } else {
                        text.setStyle("-fx-text-fill: green");
                    }
                }
        });
        text.setWrapText(true);

        Scene scene = new Scene(componentGroup); //layout
        primaryStage.setScene(scene);
        
        primaryStage.show();

        
    }
}


