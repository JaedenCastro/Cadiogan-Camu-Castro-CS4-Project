/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MUON
 */
public class Card {
    protected String type;

    public Card(String type) {
        this.type = type;
    }
    public Card properties(Card c) {
        return c;
    }
    public Card execute(Card c) {
        return c;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
