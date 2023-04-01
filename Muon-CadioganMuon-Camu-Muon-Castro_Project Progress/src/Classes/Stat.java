package Classes;

public class Stat{
    private int value,  health, block,  atk;
    private float multiplier;
    private String method;
    public Stat(int health, int block, int maxBlock, int atk, String method) {
        this.health = health;
        this.block = block;
        this.atk = atk;
        this.method = method;
    }

    public void Strike() {

    }
}
