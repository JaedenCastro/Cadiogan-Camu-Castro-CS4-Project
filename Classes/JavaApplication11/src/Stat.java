public class Stat{
    private int value, iteration, maxHP, health, block, maxBlock;
    private float multiplier;
    
    public Stat(int value, int iteration, float multiplier, int maxHP, int health, int block, int maxBlock) {
        this.value = value;
        this.iteration = iteration;
        this.multiplier = multiplier;
        this.maxHP = maxHP;
        this.health = health;
        this.maxBlock = maxBlock;
        this.block = block;
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
            build += multiplier * value;
            if (build > maxBuild){
                build = maxBuild;
            }
    }
}
