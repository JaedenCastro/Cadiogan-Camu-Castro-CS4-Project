/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Classes.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
/**
 *
 * @author jaede
 */
public class MenuController extends ControllerBase implements Initializable  {
    @FXML private ImageView logo;
    public void startGame (ActionEvent event) throws IOException {
        newScreen(event, "BattleScreen.fxml");
    }
        public void quitGame (ActionEvent event) throws IOException {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?", ButtonType.YES, ButtonType.CANCEL);
            alert.setTitle("Logout/Exit");
            alert.setHeaderText(null);


            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);


            if (result == ButtonType.YES) {

                System.exit(0);
            }
        }
    @FXML
    public void showLogo(javafx.scene.input.MouseEvent mouseEvent) {
        logo.setOpacity((100));
    }
    @FXML
    private ImageView backgroundImage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image img = new Image(getClass().getResourceAsStream("Images/Logo.png"));
        logo.setImage(img);
        logo.setOpacity((0));
        Image bgImage = new Image(getClass().getResourceAsStream("Images/bg.png"));
        backgroundImage.setImage(bgImage);
        backgroundImage.setFitWidth(1920);
        backgroundImage.setFitHeight(1000);
    }

}
