public class Stat{
    private int value, iteration, maxHP, health, block, maxBlock, atk;
    private float multiplier;
    
    public Stat(int value, int iteration, float multiplier, int maxHP, int health, int block, int maxBlock, int atk) {
        this.value = value;
        this.iteration = iteration;
        this.multiplier = multiplier;
        this.maxHP = maxHP;
        this.health = health;
        this.maxBlock = maxBlock;
        this.block = block;
        this.atk = atk;
    }
    
    public int getValue() {
        return value;
    }
    public int getIteration() {
        return iteration;
    }
    public void heal(){
        health += multiplier * value;
            if (health > maxHP){
                health = maxHP;
            }
    }
  
    public void shield(){
            block += multiplier * value;
            if (block > maxBlock){
                block = maxBlock;
            }
    }
    public void attack(){
            atk += multiplier * value;
    }
    // Has a number of times to be executed based on Control Flow and Conditional cards
    
}
