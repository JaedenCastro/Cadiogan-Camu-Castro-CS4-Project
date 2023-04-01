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
    protected String name;

    public Card(String type, int i, int i1, int i2) {
        this.name = type;
    }
    public Card properties(Card c) {
        return c;
    }
    public Card execute(Card c) {
        return c;
    }

    public void setType(String type) {
        this.name = type;
    }

    public String getName() {
        return name;
    }
}
