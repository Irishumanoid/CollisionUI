package UI.UnrelatedJavaFXStuff.Vocabulary;

import java.util.Collection;
//import java.util.Timer;
//import java.util.TimerTask;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


 /*class Helper extends TimerTask {
 
    /*public static int time = 0;
    @Override
    public void run() {
        //notify after 3 seconds
        if (time == 3) {
            synchronized(PracticeView.obj) {
                PracticeView.obj.notify();
            }
        }
    }
}*/


//translate randomly generated word (to-do: set task timeout)
public class PracticeView implements vocabMethods {

    public static PracticeView obj;
    private Dictionary dict;
    private String word;
    //Timer timer;
    //TimerTask task;

    public PracticeView(Dictionary dict) {
        this.dict = dict;
        this.word = dict.getRandomWord();
        //timer = new Timer();
        //task = new Helper();
    }

    @Override
    public Parent getView() {
        //obj = new PracticeView(dict);
        //UI
        GridPane layout = new GridPane();
        Label insert = new Label("Translate the word '"+this.word+"'");
        TextField translationField = new TextField();
        Button check = new Button("Check");
        Label feedback = new Label();

        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(15, 15, 15, 15));
        layout.add(insert, 0, 0);
        layout.add(translationField, 0, 1);
        layout.add(check, 0 , 2);
        layout.add(feedback, 0, 3);

        //when button pressed action event handler, update feedback label and reset text boxes
        check.setOnMouseClicked((event) -> {
            if (dict.get(word).equals(translationField.getText())) {
                feedback.setText("Correct!");
            } else {
                feedback.setText("Incorrect, the correct translation is " + dict.get(word));
            }

            this.word = this.dict.getRandomWord();
            insert.setText("Translate the word '" + this.word + "'");
            translationField.clear();

            /*timer.schedule(task, 1000);
            synchronized(obj) {
                try {
                    obj.wait();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }

            timer.cancel();
            feedback.setText(" ");*/
        });

        return layout;
    }

    @Override
    public Collection<String> getAll() {
        return null;
    }
}
