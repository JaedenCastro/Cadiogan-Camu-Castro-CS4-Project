package Classes;

public class Condition extends Card {
    private int property;
	private String type = "Condition";

	public Condition(String name) {
		super(name);
	}

	public int getProperty() {
        	return property;
    	}
	
	public void modifyCF(){
		
	}

	public String getType() {
		return type;
	}
}
