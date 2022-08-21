package UI.Vocabulary;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.scene.Parent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;


// when button is pressed, a word-definition pair is added to the dictionary and input field is cleared
public class InputView implements vocabMethods {

    private Dictionary dict;

    public InputView(Dictionary dict) {
        this.dict = dict;
    }

    @Override
    public Parent getView() {
        //UI
        GridPane layout = new GridPane();
        Label word = new Label("Insert word here");
        Label definition = new Label("Insert definition here");
        TextField wordText = new TextField();
        TextField defText = new TextField();
        Button submit = new Button("Submit");

        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(15, 15, 15, 15));
        layout.add(word, 0, 0);
        layout.add(wordText, 0 , 1);
        layout.add(definition, 0, 2);
        layout.add(defText, 0 , 3);
        layout.add(submit, 0, 4);
        

        //when button pressed action event handler, use add() method and clear input field
        submit.setOnAction((event) -> {
            dict.add(wordText.getText(), defText.getText());
            wordText.clear();
            defText.clear();
        });

        word.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("you're not supposed to enter your word here! "+event.getSource());
            }
        });

        return layout;
    }

    @Override
    public Collection<String> getAll() {
        List<String> totWords = new ArrayList<>();
        for (String key : dict.getKeys()) {
            totWords.add(key);
        }
        return totWords;
    }
}
