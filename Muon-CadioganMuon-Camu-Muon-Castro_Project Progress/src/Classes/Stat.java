package Classes;

public class Stat extends Card{
    private float multiplier;
    public Stat(String type) {
        super(type);
    }
@Override
    public void play(Card c, Player p) { // make sure that it is the target player in the 2nd field
        switch(c.getName()) {
            case "Strike":
                Strike(p);
                break;
            case "Defend":
                Defend(p);
                break;
            case "Heal":
                Heal(p);
                break;
        }
    }
    private void Strike(Player p) {
        int temp = 0;
        Player targetPlayer = p.getPlayers().get(p.getPlayers().size() - 1 - p.getPlayers().indexOf(p));
        if (targetPlayer.getBlock() > 0) {
            if (targetPlayer.getBlock() - 6 < 0) {
                temp = 6 - targetPlayer.getBlock();
            }
        }
        if (targetPlayer.getBlock() == 0) {
            if (temp > 0) {
                targetPlayer.setHealth(targetPlayer.getHealth()-temp);
            } else if (temp == 0) {
                targetPlayer.setHealth(targetPlayer.getHealth()-6);
            }
        }
        System.out.println(targetPlayer.getName() + " is at " + targetPlayer.getHealth() +  " HP!");
    }
    private void Defend(Player p) {
        p.setBlock(p.getBlock()+5);
        System.out.println(p.getName() + " blocked for "+  p.getBlock() + " !");
    }
    private void Heal(Player p) {
        if (p.getHealth() + 3 < p.getMaxHP()) {
            p.setHealth(p.getHealth()+3);
        } else if (p.getHealth() + 3 > p.getMaxHP()) {
            p.setHealth(p.getMaxHP());
        }
        System.out.println(p.getName() + " Healed for 3!" );
    }
}
