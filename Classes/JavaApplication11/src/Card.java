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
    String Type;

    public Card(String Type) {
        this.Type = Type;
    }
    public Card properties(Card c) {
        return c;
    }
    public Card execute(Card c) {
        return c;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getType() {
        return Type;
    }
}
