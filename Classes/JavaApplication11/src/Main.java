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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Card myCard = new Card("Strike");
        Card newCard = new Card("Defend");
        Card card3 = new Card("Piercing Wail");
        Deck deck = new Deck();
        Collections.addAll(deck.getDeck(), myCard, newCard, card3);
        System.out.println(deck.getDeck().get(0).getType());
    }
    
}
