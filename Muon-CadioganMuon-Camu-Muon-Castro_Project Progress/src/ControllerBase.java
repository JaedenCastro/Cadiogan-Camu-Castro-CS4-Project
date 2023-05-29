/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Classes.*;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jaede
 */
/**
 * The ControllerBase class is an abstract base class for JavaFX controllers.
 * It provides a method to transition to a new screen by loading the FXML file
 * and setting it as the scene for the current stage.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public abstract class ControllerBase{
    /**
     * Transitions to a new screen by loading the FXML file and setting it as the scene for the current stage.
     *
     * @param event The ActionEvent that triggered the screen transition.
     * @param s The name of the FXML file for the new screen.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    public void newScreen (ActionEvent event, String s) throws IOException {
        Node node = (Node) event.getSource();
        Scene currentScene = node.getScene();
        Stage currentStage = (Stage) currentScene.getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("FXML/"+s));
        Scene subjectScene = new Scene(root);
        currentStage.hide();
        currentStage.setScene(subjectScene);
        currentStage.show();
    }

}
