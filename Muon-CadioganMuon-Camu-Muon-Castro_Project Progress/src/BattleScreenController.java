/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Classes.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 *
 * @author jaede
 */
public class BattleScreenController extends ControllerBase implements Initializable {

    Deck p1Deck = new Deck();
    Deck p2Deck = new Deck();
    Player player1 = new Player("Player 1",100, 100, p1Deck);
    Player player2 = new Player("Player 2",100,100, p2Deck);
    Stat Strike = new Stat("Strike");
    Stat Defend = new Stat("Defend");
    Stat Heal = new Stat ("Heal");
    Condition ifelse = new Condition("ifelse");
    ControlFlow forloop = new ControlFlow("forloop");
    ControlFlow whileloop = new ControlFlow ("whileloop");
    int cardNum=0;
    @FXML
    private Text hpLabel, playerDisplay, blockLabel, eHpLabel, deckDisplay1, deckDisplay2, currPlayerDisplay;
    @FXML private HBox cards;
    @FXML private HBox pile1, pile2, pile3;
    private HBox activeBox;
    Boolean counter = true;
    Boolean pileBool = false, handBool = false;
    Player currentPlayer = player1;
    Player inactivePlayer = player2;
    Deck currentDeck = p1Deck;
    Board statPile = new Board(); Board conditionPile = new Board(); Board controlFlowPile = new Board(); Board activePile = new Board();
    StackPane activeStackPane;
    int turn = 0;
    /**
     * Event handler for the back button. It switches to the pause screen.
     *
     * @param event The action event triggered by the button.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void back (ActionEvent event) throws IOException {
        newScreen(event, "PauseScreen.fxml");
    }
    /**
     * Draws a card from the current player's deck and adds it to the hand.
     * It also updates the UI to reflect the changes.
     */
    private void Draw () {
        try {
            deckDisplay1.setText("Player 1 Draw list count:  " + (p1Deck.getDeck().size()-1));
            deckDisplay2.setText("Player 2 Draw list count:  " + (p2Deck.getDeck().size()-1));
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



            stackPane.setOnMouseEntered(e -> {
                activeStackPane = stackPane;
            });
            currentDeck.draw();

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
    private void updatePile() {
        activeBox.getChildren().clear();
        for (Card card : activePile.getPile()) {
            Image cardImage = new Image("Images/" + card.getName() + ".png");
            ImageView cardIcon = new ImageView(cardImage);
            cardIcon.setFitWidth(150);
            cardIcon.setPreserveRatio(true);

            StackPane box = new StackPane();
            StackPane stackPane = new StackPane();
            box.getChildren().addAll(cardIcon);
            stackPane.getChildren().addAll(box);
            activeBox.getChildren().add(stackPane);

            stackPane.setOnMouseEntered(e -> {
                activeStackPane = stackPane;
            });
            stackPane.getChildren().forEach(this::makeDraggable);
            stackPane.getChildren().forEach(this::makeDroppable);
        }
    }

    @FXML
    public void detectRelease1(javafx.scene.input.MouseEvent mouseEvent) {
        resetCardNum(cards);
        int tempint = 0;
        try {
            tempint = ((Integer) (activeStackPane.getUserData())).intValue();
        } catch (NullPointerException n) {

        }
        activeBox = pile1;
        activePile = statPile;
        if (pileBool && currentDeck.getHand().get(tempint).getType().equalsIgnoreCase("Stat")) {
            pileBool = false;
            addToPile(activeBox, activePile);
        }
    }

    @FXML
    public void detectRelease2(javafx.scene.input.MouseEvent mouseEvent) {
        resetCardNum(cards);
        int tempint = 0;
        try {
            tempint = ((Integer) (activeStackPane.getUserData())).intValue();
        } catch (NullPointerException n) {

        }
        activeBox = pile2;
        activePile = conditionPile;
        if (pileBool && currentDeck.getHand().get(tempint).getType().equalsIgnoreCase("Condition")) {
            pileBool = false;
            addToPile(activeBox, activePile);
        }
    }

    @FXML
    public void detectRelease3(javafx.scene.input.MouseEvent mouseEvent) {
        resetCardNum(cards);
        int tempint = 0;
        try {
            tempint = ((Integer) (activeStackPane.getUserData())).intValue();
        } catch (NullPointerException n) {

        }
        activeBox = pile3;
        activePile = controlFlowPile;
        handBool = false;
        if (pileBool && currentDeck.getHand().get(tempint).getType().equalsIgnoreCase("ControlFlow")) {
            pileBool = false;
            addToPile(activeBox, activePile);
        }
        activeBox = pile3;
        activePile = controlFlowPile;

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
                updatePile();
                updateHand();
                resetBools(mouseEvent);

            } catch (IndexOutOfBoundsException i) {

            } catch (NullPointerException n) {

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
            currentDeck.getDiscardList().add(currentDeck.getHand().get(0)); // will be used later when shuffle is implemented
            currentDeck.getHand().remove((0));
        } catch (IndexOutOfBoundsException e) {

        }
        cardNum = 0;
    }
    @FXML
    public void setPlayer() {
        if (!counter) {
            currentPlayer = player1;
            inactivePlayer = player2;
            currentDeck = p1Deck;

        } else {
            currentPlayer = player2;
            inactivePlayer = player1;
            currentDeck = p2Deck;
        }

        deckDisplay1.setText("Player 1 Draw list count:  " + p1Deck.getDeck().size());
        deckDisplay2.setText("Player 2 Draw list count:  " + p2Deck.getDeck().size());

        hpLabel.setText(currentPlayer.getHealth() +"/100");
        blockLabel.setText(Integer.toString(inactivePlayer.getBlock()));
        eHpLabel.setText(inactivePlayer.getHealth() +"/100");
        counter = !counter;

    }


    private void clearPiles () {
        ObservableList<Node> children = pile2.getChildren();
        int count = pile2.getChildren().size();
        for (int item = 0; item < count; item++) {
            children.remove(0);
            conditionPile.getPile().get(0).play(conditionPile.getPile().get(0), statPile);
            currentDeck.getDrawList().add(conditionPile.getPile().get(0));
            conditionPile.getPile().remove(0);
        }
        children = pile3.getChildren();
        count = pile3.getChildren().size();
        for (int item = 0; item < count; item++) {
            children.remove(0);
            controlFlowPile.getPile().get(0).play(controlFlowPile.getPile().get(0), statPile, currentPlayer);
            currentDeck.getDrawList().add(controlFlowPile.getPile().get(0));
            controlFlowPile.getPile().remove(0);
        }
        children = pile1.getChildren();
        count = pile1.getChildren().size();
        for (int item = 0; item < count; item++) {
            children.remove(0);
            statPile.getPile().get(0).play(statPile.getPile().get(0), currentPlayer);
            currentDeck.getDrawList().add(statPile.getPile().get(0));
            statPile.getPile().remove(0);
        }
    }
    /**
     * Initializes the controller and the battle screen.
     *
     * @param url  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb   The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i <= 14; i++){
            p1Deck.addToDeck(Strike);
        }
        for (int i = 0; i <= 13; i++){
            p1Deck.addToDeck(Defend);
        }
        for (int i = 0; i <= 5; i++){
            p1Deck.addToDeck(ifelse);
        }
        for (int i = 0; i <= 10; i++){
            p1Deck.addToDeck(Heal);
        }
        for (int i = 0; i <= 5; i++){
            p1Deck.addToDeck(forloop);
        }
        for (int i = 0; i <= 5; i++){
            p1Deck.addToDeck(whileloop);
        }

        for (int i = 0; i <= 14; i++){
            p2Deck.addToDeck(Strike);
        }
        for (int i = 0; i <= 13; i++){
            p2Deck.addToDeck(Defend);
        }
        for (int i = 0; i <= 5; i++){
            p2Deck.addToDeck(ifelse);
        }
        for (int i = 0; i <= 10; i++){
            p2Deck.addToDeck(Heal);
        }
        for (int i = 0; i <= 5; i++){
            p2Deck.addToDeck(forloop);
        }
        for (int i = 0; i <= 5; i++){
            p1Deck.addToDeck(whileloop);
        }
        p1Deck.shuffle();
        p2Deck.shuffle();

        // Load the audio file
        String audioFile = "../Muon-CadioganMuon-Camu-Muon-Castro_Project Progress/src/Music/shuffle.mp3";
        Media media = new Media(new File(audioFile).toURI().toString());

        // Load the audio file
        String audiofile = "../Muon-CadioganMuon-Camu-Muon-Castro_Project Progress/src/Music/deal.mp3";
        Media Media = new Media(new File(audiofile).toURI().toString());

        // Create the MediaPlayer
        shufflePlayer = new MediaPlayer(media);


        // Create the MediaPlayer
        dealPlayer = new MediaPlayer(Media);
    }

    private MediaPlayer shufflePlayer;
    private MediaPlayer dealPlayer;
    @FXML
    private Button endturn;
    @FXML
    private Button startturn;
    @FXML
    private void endTurn(ActionEvent event) {
        if (event.getSource() == endturn) {
            // Perform Functionality 1
            if (shufflePlayer != null) {
                if (shufflePlayer.getStatus() == MediaPlayer.Status.PLAYING) {

                    shufflePlayer.stop();
                }
                // Start playing the audio from the beginning
                shufflePlayer.seek(shufflePlayer.getStartTime());
                shufflePlayer.play();
            }
        }

        // Perform Functionality 2
        try {
            int size = currentDeck.getHand().size();
            for (int x = 0; x < size; x++ ) {
                Discard();
            }
            if (currentDeck.getDeck().size()<5) {
                currentDeck.drawShuffle();
            }
            clearPiles();
            if(currentPlayer.getHealth() <= 0){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, inactivePlayer.getName() + " has won! Would you like" +
                        " to quit the game?", ButtonType.YES, ButtonType.NO);
                alert.setTitle("Logout/Exit");
                alert.setHeaderText(null);
                ButtonType result = alert.showAndWait().get();
                if (result == ButtonType.YES) {

                    System.exit(0);
                }
                else {
                    newScreen(event, "MenuScreen.fxml");
                }
            }
            if(inactivePlayer.getHealth() <= 0 ){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, currentPlayer.getName() + " has won! Would you like" +
                        " to quit the game?", ButtonType.YES, ButtonType.NO);
                alert.setTitle("Logout/Exit");
                alert.setHeaderText(null);
                ButtonType result = alert.showAndWait().get();
                if (result == ButtonType.YES) {

                    System.exit(0);
                }
                else {
                    newScreen(event, "MenuScreen.fxml");
                }
            }
            setPlayer();
            currentPlayer.resetBlock();
            turn = 0;
            playerDisplay.setText(inactivePlayer.getName());
            currPlayerDisplay.setText(currentPlayer.getName()); //need to put this twice (here and startTurn) to ensure that its referring to the right player
        } catch (IndexOutOfBoundsException e) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void startTurn(ActionEvent event) {
        if (turn == 0){
            try {
                for (int i = 0; i < 5; i++) {
                    Draw();
                }
                playerDisplay.setText(inactivePlayer.getName());
                currPlayerDisplay.setText(currentPlayer.getName());

            }
            catch (IndexOutOfBoundsException e) {

            }
        }
        turn++;

        // Perform Functionality 2
        if (dealPlayer != null) {
            if (dealPlayer.getStatus() == MediaPlayer.Status.PLAYING) {

                dealPlayer.stop();
            }
            // Start playing the audio from the beginning
            dealPlayer.seek(dealPlayer.getStartTime());
            dealPlayer.play();
        }
    }

}
