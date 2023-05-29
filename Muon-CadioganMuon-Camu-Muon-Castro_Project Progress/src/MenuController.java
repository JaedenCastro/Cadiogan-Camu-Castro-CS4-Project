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
    /**
     * Starts the game and transitions to the battle screen.
     *
     * @param event The action event triggered by the button.
     * @throws IOException If an I/O error occurs.
     */
    public void startGame (ActionEvent event) throws IOException {
        newScreen(event, "BattleScreen.fxml");
    }
    /**
     * Quits the game and exits the application if confirmed by the user.
     *
     * @param event The action event triggered by the button.
     * @throws IOException If an I/O error occurs.
     */
        public void quitGame (ActionEvent event) throws IOException {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?", ButtonType.YES, ButtonType.CANCEL);
            alert.setTitle("Logout/Exit");
            alert.setHeaderText(null);


            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);


            if (result == ButtonType.YES) {

                System.exit(0);
            }
        }
    /**
     * Event handler for displaying the logo image when the mouse hovers over it.
     *
     * @param mouseEvent The mouse event triggered by hovering over the logo image.
     */
    @FXML
    public void showLogo(javafx.scene.input.MouseEvent mouseEvent) {
        logo.setOpacity((100));
    }
    @FXML
    private ImageView backgroundImage;
    /**
     * Initializes the controller and sets up the menu screen with the logo and background image.
     *
     * @param url  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb   The resources used to localize the root object, or null if the root object was not localized.
     */
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
