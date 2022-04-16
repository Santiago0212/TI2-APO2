package model;

public class Player {
	 private String name;
	 private int recolectedSeeds;
	 
	public Player(String name, int recolectedSeeds) {
		this.name = name;
		this.recolectedSeeds = recolectedSeeds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRecolectedSeeds() {
		return recolectedSeeds;
	}
	public void setRecolectedSeeds(int recolectedSeeds) {
		this.recolectedSeeds = recolectedSeeds;
	}
	 
	 
}
