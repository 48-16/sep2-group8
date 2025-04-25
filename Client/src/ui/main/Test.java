package ui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import networking.apointment.appointmentClient;
import networking.authentication.authenticationClient;
import networking.shop.shopClient;
import startup.ViewHandler;
import startup.ViewType;

import java.io.IOException;

public class Test extends Application {
    private authenticationClient authClient;
    private appointmentClient appointmentClient;
    private shopClient shopClient;

    @Override
    public void start(Stage primaryStage) throws IOException {
        authClient = new authenticationClient();
        appointmentClient = new appointmentClient();

        ViewHandler.initialize(primaryStage, authClient, appointmentClient, shopClient);

        ViewHandler.show(ViewType.USER);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
