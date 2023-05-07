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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    int cardNum=0, activeCardNum=0;
    @FXML
    private Text hpLabel, playerDisplay;
    @FXML private HBox cards;
    @FXML private HBox pile1, pile2, pile3;
    private HBox activeBox;
    Boolean counter = true;
    Boolean pileBool = false, handBool = false;
    Player currentPlayer = player1;
    Deck currentDeck = p1Deck;
    Board statPile = new Board(); Board conditionPile = new Board(); Board controlFlowPile = new Board(); Board activePile = new Board();
    StackPane activeStackPane;
    @FXML
    public void back (ActionEvent event) throws IOException {
        newScreen(event, "PauseScreen.fxml");
    }
    private void Draw () {
        try {
            Image cardA = new Image("Images/" + currentDeck.getDeck().get(0).getName() + ".png");
            ImageView cardIcon = new ImageView();
            cardIcon.setImage(cardA);
            cardIcon.setX(100);
            cardIcon.setY(100);
            cardIcon.setFitWidth(150);
            cardIcon.setPreserveRatio(true);
            StackPane box = new StackPane();
            StackPane stackPane = new StackPane();
            box.getChildren().addAll(cardIcon);
            stackPane.getChildren().addAll(box);
            cards.getChildren().add(stackPane);

            System.out.println( "activeStackPane ==>" + activeStackPane );


            stackPane.setOnMouseEntered(e -> {
                activeStackPane = stackPane;
            });
            currentDeck.getHand().add(currentDeck.getDrawList().get(0));
            currentDeck.getDrawList().remove(0);

            stackPane.getChildren().forEach((this::makeDraggable));
            stackPane.getChildren().forEach((this::makeDroppable));
        } catch (IndexOutOfBoundsException e) {

        }
    }
    public  void resetCardNum(HBox hbox) {
        int cardNum = 0;
        for (Node n : hbox.getChildren()) {
            n.setUserData(new Integer(cardNum));
            cardNum++;
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
    public void addToPile(HBox pile, Board board) {
        try {
            resetCardNum(cards);

            int tempint = ((Integer) (activeStackPane.getUserData())).intValue();

            Image cardA = new Image("Images/" + currentDeck.getHand().get(tempint).getName() + ".png");
            ImageView cardIcon = new ImageView();
            cardIcon.setImage(cardA);
            cardIcon.setX(100);
            cardIcon.setY(100);
            cardIcon.setFitWidth(150);
            cardIcon.setPreserveRatio(true);
            StackPane box = new StackPane();
            StackPane stackPane = new StackPane();
            box.getChildren().addAll(cardIcon);
            stackPane.getChildren().addAll(box);
            pile.getChildren().add(stackPane);

            ObservableList<Node> children = cards.getChildren();
            children.remove(children.size() - 1);
            stackPane.getChildren().forEach(node -> {
                makeDraggable(node);
                makeDroppable(node);
            });
            stackPane.setOnMouseEntered(e -> {
                activeStackPane = stackPane;
                System.out.println( "Draw activeStackPane = " + activeStackPane );
            });
            board.getPile().add(currentDeck.getHand().get(tempint));
            currentDeck.getHand().remove(tempint);

            resetCardNum(activeBox);

            updateHand();

        } catch (IndexOutOfBoundsException i) {
        }
    }

    private void updateHand() {
        cards.getChildren().clear();

        for (Card card : currentDeck.getHand()) {
            Image cardImage = new Image("Images/" + card.getName() + ".png");
            ImageView cardIcon = new ImageView(cardImage);
            cardIcon.setFitWidth(150);
            cardIcon.setPreserveRatio(true);

            StackPane box = new StackPane();
            StackPane stackPane = new StackPane();
            box.getChildren().addAll(cardIcon);
            stackPane.getChildren().addAll(box);
            cards.getChildren().add(stackPane);

            stackPane.setOnMouseEntered(e -> {
                activeStackPane = stackPane;
            });

            stackPane.getChildren().forEach(this::makeDraggable);
            stackPane.getChildren().forEach(this::makeDroppable);
        }
    }


    @FXML
    public void detectRelease1(javafx.scene.input.MouseEvent mouseEvent) {
        activeBox = pile1;
        activePile = statPile;
        if (pileBool) {
            pileBool = false;
            addToPile(pile1, statPile);
        }
    }

    @FXML
    public void detectRelease2(javafx.scene.input.MouseEvent mouseEvent) {
        activeBox = pile2;
        activePile = conditionPile;
        if (pileBool) {
            pileBool = false;
            addToPile(pile2, conditionPile);
        }
    }

    @FXML
    public void detectRelease3(javafx.scene.input.MouseEvent mouseEvent) {
        activeBox = pile3;
        activePile = controlFlowPile;
        handBool = false;
        if (pileBool) {
            pileBool = false;
            addToPile(pile3, controlFlowPile);
        }
    }

    @FXML
    public void detectReleaseHand(javafx.scene.input.MouseEvent mouseEvent) {
        pileBool = false;

        if (handBool) {
            handBool = false;
            try {
                resetCardNum(activeBox);
                int tempint = 0;

                try {
                    tempint = ((Integer) (activeStackPane.getUserData())).intValue();
                }  catch(Exception e) {
                    tempint = 0;
                }
                Image cardA = new Image("Images/" + activePile.getPile().get(tempint).getName() + ".png");
                ImageView cardIcon = new ImageView();
                cardIcon.setImage(cardA);
                cardIcon.setX(100);
                cardIcon.setY(100);
                cardIcon.setFitWidth(150);
                cardIcon.setPreserveRatio(true);
                StackPane box = new StackPane();
                StackPane stackPane = new StackPane();
                box.getChildren().addAll(cardIcon);
                stackPane.getChildren().addAll(box);
                cards.getChildren().add(stackPane);

                ObservableList<Node> children = activeBox.getChildren();
                children.remove(children.size() - 1);
                stackPane.getChildren().forEach(node -> {
                    makeDraggable(node);
                    makeDroppable(node);
                });
                stackPane.setOnMouseEntered(e -> {
                    activeStackPane = stackPane;
                });
                currentDeck.getHand().add(activePile.getPile().get(tempint));
                activePile.getPile().remove(tempint);
                updateHand();
                resetBools(mouseEvent);
            } catch (IndexOutOfBoundsException i) {

            } catch (NullPointerException n) {
                System.out.print( "detectReleaseHand: activeStackPane=  ");
                System.out.println(activeStackPane);
                System.out.print(" aSP.getUserData()=  ");
                System.out.println(activeStackPane.getUserData() );
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
        activeCardNum = 0;
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
        activePile = statPile;
    }

}
