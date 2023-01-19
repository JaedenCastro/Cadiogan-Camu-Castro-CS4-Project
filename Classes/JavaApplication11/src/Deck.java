/*
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
    ArrayList<Card> cardList;
    ArrayList<Card> Hand;

    public Deck() {
        cardList = new ArrayList<Card>(52);
        Hand = new ArrayList<Card>(13);
    }
    public void draw(){
        Hand.add(cardList.get(0)); //adds 1st element of cardList to the end of Hand
        cardList.remove(0); // 1st element is 1st card to be drawn
    }
    public void shuffle() {
        Collections.shuffle(cardList);
    }
}
