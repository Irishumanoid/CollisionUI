package UI;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//3 scene login UI: first page is login screen, if 2-word name, sucess, else failure
public class Login extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        GridPane gPane = new GridPane();
        Label intro = new Label("Enter your name and start.");
        PasswordField pwd = new PasswordField();
        Button validate = new Button("Start");

        Button b1 = new Button("Menu");
        Button b2 = new Button("Info");
        HBox buttons = new HBox();
        buttons.getChildren().addAll(b1, b2);
        BorderPane widgets = new BorderPane();
        StackPane firstView = createView("1. Homework 2. Chores 3. Play");
        StackPane secondView = createView("You owe us $500");
        widgets.setTop(buttons);
        //widgets.setLeft(firstView);
        //widgets.setRight(secondView);

        b1.setOnAction((event) -> widgets.setCenter(firstView));
        b2.setOnAction((event) -> widgets.setCenter(secondView));

        gPane.add(intro, 0, 0);
        gPane.add(pwd, 0, 1);
        gPane.add(validate, 0, 3);
        gPane.setPrefSize(300, 180);
        gPane.setAlignment(Pos.CENTER);
        gPane.setPadding(new Insets(10, 10, 10, 10));

        Label fail = new Label("Invalid Username");

        Scene loginScreen = new Scene(gPane);
        Scene failure = new Scene(fail);

        validate.setOnAction((event) -> {
            String[] numNames = pwd.getText().toString().split(" ");
            if (numNames.length != 2) {
                primaryStage.setScene(failure);
                return;
            }
            
            Label valid = new Label();
            valid.setPrefSize(150, 100);
            valid.setAlignment(Pos.CENTER);
            valid.setText("Welcome " + pwd.getText() + "!");
            widgets.setCenter(valid);

            Scene success = new Scene(widgets);
            primaryStage.setScene(success);
        });

        primaryStage.setScene(loginScreen);
        primaryStage.show();
    }  
    
    private StackPane createView(String input) {
        StackPane pane = new StackPane();
        pane.setPrefSize(300, 180);
        pane.getChildren().add(new Label(input));
        pane.setAlignment(Pos.CENTER);
        return pane;
    }
}