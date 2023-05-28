package Classes;

public class ControlFlow extends Card {
    private int property;
    private String type = "ControlFlow";

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
    public void play(Card c, Board b) { // make sure that it is the target player in the 2nd field
        switch(c.getName()) {
            case "forloop":
                forloop(b);
                break;
            case "whileloop":
                //whileloop(c);
                break;
        }
    }
    public void forloop(Board b) {
        b.getPile().get(0).setIterateCount(4);
    }
}
