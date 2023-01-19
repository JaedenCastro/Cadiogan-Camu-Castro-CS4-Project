/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MUON
 */
public class Player {
    int health;
    Card cardsAvailable;
    
    public Player(int health, Card cardsAvailable) {
        this.health = health;
        this.cardsAvailable = cardsAvailable;
    }
    public int getHealth() {
        return health;
    }

    public Card getCardsAvailable() {
        return cardsAvailable;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setCardsAvailable(Card cardsAvailable) {
        this.cardsAvailable = cardsAvailable;
    }
    


    
}
