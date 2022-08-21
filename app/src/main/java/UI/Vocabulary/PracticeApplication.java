package UI.Vocabulary;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PracticeApplication extends Application {

    private Dictionary dictionary;

    @Override
    public void init() throws Exception {
        this.dictionary = new Dictionary();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        InputView inputScene = new InputView(dictionary);
        PracticeView practiceScene = new PracticeView(dictionary);

        BorderPane layout = new BorderPane();
       
        HBox menu = new HBox();
        menu.setPadding(new Insets(20, 20, 20, 20));
        menu.setSpacing(10);

        Button enterButton = new Button("Enter new words");
        Button practiceButton = new Button("Practice");

        menu.getChildren().addAll(enterButton, practiceButton);
        layout.setTop(menu);

        enterButton.setOnAction((event) -> layout.setCenter(inputScene.getView()));
        practiceButton.setOnAction((event) -> layout.setCenter(practiceScene.getView()));

        layout.setCenter(inputScene.getView());

        Scene view = new Scene(layout, 400, 400);
        stage.setScene(view);
        stage.show();
    }
 
    public static void main(String[] args) throws Exception {
        launch(PracticeApplication.class);
    }
   
}
