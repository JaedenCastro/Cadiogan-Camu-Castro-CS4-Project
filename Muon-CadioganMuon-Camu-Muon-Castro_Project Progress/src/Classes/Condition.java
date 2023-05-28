package Classes;

public class Condition extends Card {
    private int property;
	private String type = "Condition";

	public Condition(String name) {
		super(name);
	}
	@Override
	public void play(Card c, Board b) { // make sure that it is the target player in the 2nd field
		switch(c.getName()) {
			case "ifelse":
				ifelse(b);
				break;
		}
	}
	public void ifelse(Board b){
		if (b.getPile().size() % 2 == 0) {
			b.getPile().get(0).setMultiplier(2);
		}
	}
	public String getType() {
		return type;
	}
}
