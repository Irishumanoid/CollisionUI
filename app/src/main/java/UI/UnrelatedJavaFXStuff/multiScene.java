package UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class multiScene extends Application{

  @Override
  public void start(Stage window) {

      Button toSecond = new Button("To second view!");
      Button toThird = new Button("To second view!");
      Button toFirst = new Button("To first view!");
  
    
      BorderPane bPane = new BorderPane();
      Text txt = new Text("First View!");
      bPane.setTop(txt);
      bPane.setCenter(toSecond);

      VBox vBox = new VBox();
      vBox.getChildren().addAll(toThird, new Text("Second view!"));

      GridPane gPane = new GridPane();
      gPane.add(new Text("Third view!"), 0, 0);
      gPane.add(toFirst, 1, 1);

      Scene first = new Scene(bPane);
      Scene second = new Scene(vBox);
      Scene third = new Scene(gPane);

      toSecond.setOnAction((event) -> {
          window.setScene(second);
      });

      toThird.setOnAction((event) -> {
          window.setScene(third);
      });

      toFirst.setOnAction((event) -> {
        window.setScene(first);
      });

      window.setScene(first);
      window.show();
  }
}
