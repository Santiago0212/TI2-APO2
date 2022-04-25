package structures;

import model.Player;

public class NodeDE {

			
	//Información
	private int value;
	
	//Enlaces
	private NodeDE previous;
	private NodeDE next;
	private Player player1;
	private Player player2;
	private boolean seed;
	private NodeDE portalDestination;
	private int portalOrigin;
	private String letra;
	
	
	public NodeDE(int value, boolean seed, int portalOrigin) {
		
		this.value = value;
		this.player1 = null;
		this.player2 = null;
		this.seed = seed;
		this.portalOrigin = portalOrigin;
		this.letra="";
	}

	
	public String getLetra() {
		return letra;
	}


	public void setLetra(String letra) {
		this.letra = letra;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}


	public NodeDE getPrevious() {
		return previous;
	}


	public void setPrevious(NodeDE previous) {
		this.previous = previous;
	}


	public NodeDE getNext() {
		return next;
	}


	public void setNext(NodeDE next) {
		this.next = next;
	}


	public Player getPlayer1() {
		return player1;
	}


	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}


	public Player getPlayer2() {
		return player2;
	}


	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}


	public boolean isSeed() {
		return seed;
	}


	public void setSeed(boolean seed) {
		this.seed = seed;
	}


	public NodeDE getPortalDestination() {
		return portalDestination;
	}


	public void setPortalDestination(NodeDE portalDestination) {
		this.portalDestination = portalDestination;
	}


	public int getPortalOrigin() {
		return portalOrigin;
	}


	public void setPortalOrigin(int portalOrigin) {
		this.portalOrigin = portalOrigin;
	}
	
	
	
}
