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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.xml.soap.Text;

/**
 *
 * @author jaede
 */
public class BattleScreenController extends ControllerBase implements Initializable {
    Deck p1Deck = new Deck();
    Card Strike = new Card("Strike", 0, 0, 6);
    @FXML
    private Label label;
    @FXML private HBox cards;

    @FXML
    private void back (ActionEvent event) throws IOException {
        newScreen(event, "MenuScreen.fxml");
    }
    @FXML
    public void Draw (ActionEvent event) throws IOException {
        try {
            Rectangle square = new Rectangle();
            square.setWidth(75);
            square.setHeight(100);
            square.setFill(Color.WHITE);
            square.setStroke(Color.BLACK);
            StackPane stackPane = new StackPane();
            Label label = new Label(p1Deck.getDeck().get(0).getName());
            stackPane.getChildren().addAll(square, label);
            cards.getChildren().add(stackPane);
            p1Deck.getHand().add(p1Deck.getDeck().get(0));
            p1Deck.getDeck().remove(0);
        } catch (IndexOutOfBoundsException e) {

        }
    }
    @FXML
    public void Discard (ActionEvent event) throws IOException {
        try {
            ObservableList<Node> children = cards.getChildren();
            children.remove(children.size() - 1);
            p1Deck.getDeck().add(p1Deck.getHand().get(0));
            p1Deck.getHand().remove((0));
        } catch (IndexOutOfBoundsException e) {

        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p1Deck.addToDeck(Strike);
    }

    
}
