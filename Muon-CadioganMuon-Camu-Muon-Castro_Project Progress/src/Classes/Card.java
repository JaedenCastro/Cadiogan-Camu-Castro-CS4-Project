package Classes;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MUON
 */
public class Card {
    protected String name, type;
    private int numberInHand = 0;

    public Card(String name) {
        this.name = name;
    }


    public Card properties(Card c) {
        return c;
    }


    public void setType(String type) {
        this.name = type;
    }

    public String getName() {
        return name;
    }

    public void setNumberInHand(int num) {numberInHand = num;}
    public int getNumberInHand() {return numberInHand;}
    public void play(Card c, Player p) {}
    public String getType() {
        return type;
    }
}
