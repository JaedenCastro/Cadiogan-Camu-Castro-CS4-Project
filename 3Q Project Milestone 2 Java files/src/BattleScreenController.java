/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Classes.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jaede
 */
public class BattleScreenController extends ControllerBase implements Initializable {
    Deck p1Deck = new Deck();
    Card Strike = new Card("Strike", 0, 0, 6);
    @FXML
    private Label label;
    private HBox cards;
    @FXML
    private void back (ActionEvent event) throws IOException {
        newScreen(event, "MenuScreen.fxml");
    }
    public void Draw (ActionEvent event) throws IOException {

        p1Deck.getDeck().remove(0);
        Rectangle square = new Rectangle();
        square.setWidth(50);
        square.setHeight(50);
        square.setFill(Color.BLUE);
        cards.getChildren().add(square);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p1Deck.addToDeck(Strike);
    }

    
}
