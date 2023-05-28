package Classes;

public class ControlFlow extends Card {
    private int property;
    private static String type = "ControlFlow";

    public ControlFlow(String name) {
        super(name);
    }

    public int getProperty() {
        return property;
    }
	public void modifyStat() {
		
	}

    public String getType() {
        return type;
    }
    @Override
    public void play(Card c, Board b, Player p) { // make sure that it is the target player in the 2nd field
        switch(c.getName()) {
            case "forloop":
                forloop(b);
                break;
            case "whileloop":
                whileloop(b,p);
                break;
        }
    }
    public void forloop(Board b) {
        b.getPile().get(0).setIterateCount(4);
    }
    public void whileloop(Board b, Player p) {
        if (p.getHealth()%2==0) {
            b.getPile().get(0).setIterateCount(2);
        } else {
            b.getPile().get(0).setIterateCount(3);
            b.getPile().get(1).setIterateCount(2);
        }
    }
}
