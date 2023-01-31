/*
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
    private ArrayList<Card> statPile;
    private ArrayList<Card> conditionPile;
    private ArrayList<Card> controlFlowPile;
    
    public Board() {
        statPile = new ArrayList<>(0);
        conditionPile = new ArrayList<>(0);
        controlFlowPile = new ArrayList<>(0);
    }
    
    
    /**
     * @return the statPile
     */
    public ArrayList<Card> getStatPile() {
        return statPile;
    }

    /**
     * @return the conditionPile
     */
    public ArrayList<Card> getConditionPile() {
        return conditionPile;
    }

    /**
     * @return the controlFlowPile
     */
    public ArrayList<Card> getControlFlowPile() {
        return controlFlowPile;
    }
}
