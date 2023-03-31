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
        newScreen(event, "QuitScreen.fxml");
    }
    @FXML
    public void showLogo(javafx.scene.input.MouseEvent mouseEvent) {
        logo.setOpacity((100));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image img = new Image(getClass().getResourceAsStream("Logo.png"));
        logo.setImage(img);
        logo.setOpacity((0));
    }

}
