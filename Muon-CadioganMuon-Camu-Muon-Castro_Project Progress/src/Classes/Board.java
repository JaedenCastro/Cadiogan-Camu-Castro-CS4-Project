package Classes;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaede
 */
import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private ArrayList<Card> pile;

    public Board() {
        pile = new ArrayList<>(0);
    }


    /**
     * @return the statPile
     */
    public ArrayList<Card> getPile() {
        return pile;
    }

}