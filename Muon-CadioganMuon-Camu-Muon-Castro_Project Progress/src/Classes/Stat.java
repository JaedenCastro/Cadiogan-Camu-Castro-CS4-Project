package Classes;

public class Stat extends Card{
    private String type = "Stat";
    public Stat(String name) {
        super(name);
    }
@Override
    public void play(Card c, Player p) { // make sure that it is the target player in the 2nd field
        for (int i =0; i < interatedCount; i++) {
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
        multiplier = 1;
    }
    private void Strike(Player p) {
        int attackValue = (int) (6.0*multiplier);

        int temp = 0;
        Player targetPlayer = p.getPlayers().get(p.getPlayers().size() - 1 - p.getPlayers().indexOf(p));
        if (targetPlayer.getBlock() > 0) {
            if (targetPlayer.getBlock() - attackValue < 0) {
                temp = attackValue - targetPlayer.getBlock();
                targetPlayer.setBlock(targetPlayer.getBlock() - attackValue > 0 ? targetPlayer.getBlock()-attackValue : 0 );
            }
        }
        if (targetPlayer.getBlock() == 0) {
            if (temp > 0) {
                targetPlayer.setHealth(targetPlayer.getHealth()-temp);
            } else if (temp == 0) {
                targetPlayer.setHealth(targetPlayer.getHealth()-attackValue);
            }
        }
        System.out.println(targetPlayer.getName() + " is at " + targetPlayer.getHealth() +  " HP!");
    }
    private void Defend(Player p) {
        p.setBlock(p.getBlock()+(int)(5.0*multiplier));
        System.out.println(p.getName() + " blocked for "+  p.getBlock() + " !");
    }
    private void Heal(Player p) {
        int healValue = (int) (3.0*multiplier);
        if (p.getHealth() + healValue < p.getMaxHP()) {
            p.setHealth(p.getHealth()+healValue);
        } else if (p.getHealth() + healValue >= p.getMaxHP()) {
            p.setHealth(p.getMaxHP());
        }
        System.out.println(p.getName()+" Healed for "+ healValue + "!");

    }

    public String getType() {
        return type;
    }

}
