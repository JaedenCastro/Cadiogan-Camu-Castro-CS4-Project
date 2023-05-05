/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

import java.awt.*;
import java.awt.event.MouseEvent;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

/**
 *
 * @author jaede
 */
public class BattleScreenController extends ControllerBase implements Initializable {
    Deck p1Deck = new Deck();
    Deck p2Deck = new Deck();
    Player player1 = new Player(100, 100, p1Deck);
    Player player2 = new Player(100,100, p2Deck);
    Card Strike = new Card("Strike", 0, 0, 6);
    Card Defend = new Card("Defend", 0, 0, 5);
    Card Heal = new Card ("Heal",0, 0, 4);
    int pile1Int=0, pile2Int=1, pile3Int=2;
    @FXML
    private Text hpLabel, playerDisplay;
    @FXML private HBox cards;
    @FXML private HBox pile1, pile2, pile3;
    private HBox activePile;
    Boolean counter = true;
    Boolean pileBool = false, handBool = false;
    Player currentPlayer = player1;
    Deck currentDeck = p1Deck;
    @FXML
    public void back (ActionEvent event) throws IOException {
        newScreen(event, "PauseScreen.fxml");
    }
    private void dropTarget () {

    }
    private void Draw () {
        try {
            Rectangle square = new Rectangle();
            square.setWidth(75);
            square.setHeight(100);
            square.setFill(Color.WHITE);
            square.setStroke(Color.BLACK);
            Label label = new Label(currentDeck.getDeck().get(0).getName());

            StackPane box = new StackPane();
            StackPane stackPane = new StackPane();
            box.getChildren().addAll(square, label);
            stackPane.getChildren().addAll(box);
            cards.getChildren().add(stackPane);

            currentDeck.getHand().add(currentDeck.getDrawList().get(0));
            currentDeck.getDrawList().remove(0);

            stackPane.getChildren().forEach(node -> {
                makeDraggable(node);
                makeDroppable(node);
            });
        } catch (IndexOutOfBoundsException e) {

        }
    }
    private double startX, originalTranslateX;
    private double startY, originalTranslateY;

    /*

    This code is still kind of buggy, for example the card might suddenly be let go
    However, I think it's mostly bug free apart from my mouse letting go of the card sometimes
    At this point I think it's a hardware issue

     */
    private void makeDraggable(Node node) {
        node.setOnMousePressed(e -> {
            startX = e.getSceneX() - node.getTranslateX();
            startY = e.getSceneY() - node.getTranslateY();
            originalTranslateX = node.getTranslateX();
            originalTranslateY = node.getTranslateY();
            pileBool = false;
            handBool = false;
        });

        node.setOnMouseDragged(e -> {
            node.setTranslateX(e.getSceneX()-startX);
            node.setTranslateY(e.getSceneY()-startY);
            pileBool = false;
            handBool = false;
        });
    }


    private void makeDroppable(Node node) {
        node.setOnMouseReleased(e -> {
            node.setTranslateX(originalTranslateX);
            node.setTranslateY(originalTranslateY);
            pileBool = true;
            handBool = true;

        }); // bug with letting go outside of a pile which causes the next time you hover over a pile to create a card instance
    }
    public void addToPile (HBox pile) {
        Rectangle square = new Rectangle();
        square.setWidth(75);
        square.setHeight(100);
        square.setFill(Color.WHITE);
        square.setStroke(Color.BLACK);
        try {
            Label label = new Label(currentDeck.getHand().get(0).getName());
            StackPane box = new StackPane();
            StackPane stackPane = new StackPane();
            box.getChildren().addAll(square, label);
            stackPane.getChildren().addAll(box);
            pile.getChildren().add(stackPane);
            ObservableList<Node> children = cards.getChildren();
            children.remove(children.size() - 1);
            stackPane.getChildren().forEach(node -> {
                makeDraggable(node);
                makeDroppable(node);
            });
        } catch (IndexOutOfBoundsException i) {

        }

    }
    public void detectRelease1(javafx.scene.input.MouseEvent mouseEvent) {
        activePile = pile1;
        if (pileBool) {
            pileBool = false;
            addToPile(pile1);
        }
    }
    public void detectRelease2(javafx.scene.input.MouseEvent mouseEvent) {
        activePile = pile2;
        if (pileBool) {
            pileBool = false;
            addToPile(pile2);
        }
    }
    public void detectRelease3(javafx.scene.input.MouseEvent mouseEvent) {
        activePile = pile3;
        handBool = false;
        if (pileBool) {
            pileBool = false;
            addToPile(pile3);
        }
    }
    public void detectReleaseHand(javafx.scene.input.MouseEvent mouseEvent) {
        pileBool = false;

        if (handBool) {
            System.out.println(handBool);
            handBool = false;
            Rectangle square = new Rectangle();
            square.setWidth(75);
            square.setHeight(100);
            square.setFill(Color.WHITE);
            square.setStroke(Color.BLACK);
            try {
                Label label = new Label(currentDeck.getHand().get(0).getName());
                StackPane box = new StackPane();
                StackPane stackPane = new StackPane();
                box.getChildren().addAll(square, label);
                stackPane.getChildren().addAll(box);
                cards.getChildren().add(stackPane);
                ObservableList<Node> children = activePile.getChildren();
                children.remove(children.size() - 1);
                stackPane.getChildren().forEach(node -> {
                    makeDraggable(node);
                    makeDroppable(node);
                });
            } catch (IndexOutOfBoundsException i) {

            }
        }
    }
    public void resetBools(javafx.scene.input.MouseEvent mouseEvent) {
        handBool = false;
        pileBool = false;
    }

    @FXML
    public void Discard () {
        try {
            ObservableList<Node> children = cards.getChildren();
            children.remove(children.size() - 1);
            // currentDeck.getDiscardList().add(currentDeck.getHand().get(0)); // will be used later when shuffle is implemented
            currentDeck.getDrawList().add(currentDeck.getHand().get(0));
            currentDeck.getHand().remove((0));
        } catch (IndexOutOfBoundsException e) {

        }
    }
    @FXML
    public void setPlayer() {
        if (!counter) {
            currentPlayer = player1;
            currentDeck = p1Deck;
            playerDisplay.setText("Player 1");
        } else {
            currentPlayer = player2;
            currentDeck = p2Deck;
            playerDisplay.setText("Player 2");
        }
        hpLabel.setText(Integer.toString(currentPlayer.getHealth())+"/100");
        counter = !counter;
    }
    @FXML
    public void startTurn (ActionEvent event) throws IOException {
        try {

            for (int i = 0; i < 5; i++) {
                Draw();
            }


        } catch (IndexOutOfBoundsException e) {

        }
    }
    @FXML
    public void endTurn (ActionEvent event) throws IOException {
        try {
            int size = currentDeck.getHand().size();
            for (int x = 0; x < size; x++ ) {
                Discard();
            }
            setPlayer();
        } catch (IndexOutOfBoundsException e) {

        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p1Deck.addToDeck(Strike);
        p1Deck.addToDeck(Strike);
        p1Deck.addToDeck(Defend);
        p1Deck.addToDeck(Defend);
        p1Deck.addToDeck(Heal);

        p2Deck.addToDeck(Heal);
        p2Deck.addToDeck(Heal);
        p2Deck.addToDeck(Heal);
        p2Deck.addToDeck(Strike);
        p2Deck.addToDeck(Defend);

    }

}
