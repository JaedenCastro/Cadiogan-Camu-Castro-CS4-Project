package Classes;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author MUON
 */
public class Player {
    private String name;
    private int health;
    private static int maxHP = 100;
    private int block=0;
    private Deck deck;
    private static ArrayList<Player> Players = new ArrayList<>();
    public Player(String name, int maxHP, int health, Deck deck) {
        this.name = name;
        this.maxHP = 100;
        this.setHealth(health);
        this.deck = deck;
        Players.add(this);
    }

    public static ArrayList<Player> getPlayers() {
        return Players;
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


    public int getBlock() {
        return block;
    }
    public int resetBlock() {
        block = 0;
        return block;
    }
    public void setBlock(int block) {
        this.block = block;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public String getName() {
        return name;
    }
}
