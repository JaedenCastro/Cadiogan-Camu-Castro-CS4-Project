package Classes; /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MUON
 */
import java.util.ArrayList;
import java.util.Collections;
public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> drawList;
    private ArrayList<Card> discardList;

    public Deck() {
        deck = new ArrayList<>(52);
        hand = new ArrayList<>(13);
        drawList = deck;
        discardList = new ArrayList<>(0);
    }
    public void addToDeck(Card c) {
        deck.add(c);
    }
    public void draw(){
        getHand().add(deck.get(0)); //adds 1st element of cardList to the end of Hand
        deck.remove(0); // 1st element is 1st card to be drawn
    }
    public void shuffle() {
        Collections.shuffle(discardList);
        for (Card a: discardList) {
            getDrawList().add(a);
        }
        discardList.clear();
    }

    public void drawShuffle(){
        Collections.shuffle(drawList);
    }

    /**
     * @return the cardList
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * @return the Hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * @return the drawList
     */
    public ArrayList<Card> getDrawList() {
        return drawList;
    }

    /**
     * @return the discardList
     */
    public ArrayList<Card> getDiscardList() {
        return discardList;
    }

}
