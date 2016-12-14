package sample;

import com.google.gson.Gson;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("weatherApp.fxml"));
        primaryStage.setTitle("Weatherly");
        primaryStage.setScene(new Scene(root, 480, 800));
        primaryStage.setMaxHeight(640);
        primaryStage.setMinHeight(640);
        primaryStage.setMaxWidth(384);
        primaryStage.setMinWidth(384);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
