package startup;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import networking.apointment.appointmentClient;
import networking.authentication.authenticationClient;
import networking.shop.shopClient;
import ui.login.loginViewControler;
import ui.login.loginViewModel;
import ui.main.mainAdminViewControler;
import ui.main.mainViewUserController;
import ui.main.mainViewAdminModel;
import ui.main.mainViewUserModel;
import ui.register.signUpVIewModel;
import ui.register.signUpViewController;
import dtos.user.UserDataDto;

import java.io.IOException;

public class ViewHandler {
    private static Stage primaryStage;
    private static authenticationClient authClient;
    private static appointmentClient appointmentClient;
    private static shopClient shopClient;

    public static void initialize(Stage stage, authenticationClient authClient, 
                                appointmentClient appointmentClient, shopClient shopClient) {
        ViewHandler.primaryStage = stage;
        ViewHandler.authClient = authClient;
        ViewHandler.appointmentClient = appointmentClient;
        ViewHandler.shopClient = shopClient;
    }

    public static void show(ViewType viewToShow) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Scene scene = null;

            switch (viewToShow) {
                case LOGIN:
                    loader.setLocation(ViewHandler.class.getResource("/ui/login/loginViewFXML.fxml"));
                    loader.setControllerFactory(c -> new loginViewControler(new loginViewModel(authClient)));
                    scene = new Scene(loader.load());
                    break;
                case REGISTER:
                    loader.setLocation(ViewHandler.class.getResource("/ui/register/signUpViewFXML.fxml"));
                    loader.setControllerFactory(c -> new signUpViewController(new signUpVIewModel(authClient)));
                    scene = new Scene(loader.load());
                    break;
                case USER:
                    loader.setLocation(ViewHandler.class.getResource("/ui/main/mainViewUserFXML.fxml"));
                    loader.setControllerFactory(c -> new mainViewUserController(
                            new mainViewUserModel(appointmentClient, UserDataDto.currentUser),
                            shopClient));
                    scene = new Scene(loader.load());
                    break;
                case ADMIN:
                    loader.setLocation(ViewHandler.class.getResource("/ui/main/mainAdminViewFXML.fxml"));
                    loader.setControllerFactory(c -> new mainAdminViewControler(
                            new mainViewAdminModel(appointmentClient), 
                            shopClient));
                    scene = new Scene(loader.load());
                    break;
            }

            if (scene != null) {
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
