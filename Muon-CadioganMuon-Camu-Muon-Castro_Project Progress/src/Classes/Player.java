package Classes;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MUON
 */
public class Player {
    private int health, maxHP, block, maxBlock;
    private Deck deck;
    
    public Player(int maxHP, int health, Deck deck) {
        this.maxHP = 100;
        this.health = health;
        this.deck = deck;
    }
    public int getHealth() {
        return health;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDeck(Card cardsAvailable) {
        this.deck = deck;
    }
    


    
}
