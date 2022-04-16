package structures;

import model.Player;

public class NodeDE {

			
	//Información
	private int value;
	
	//Enlaces
	private NodeDE previous;
	private NodeDE next;
	private Player player;
	private boolean seed;
	private NodeDE portalDestination;
	
	public NodeDE(int value,boolean seed) {
		this.value = value;
		this.seed=seed;
		player=null;
		portalDestination=null;
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

	public NodeDE getPortalDestination() {
		return portalDestination;
	}

	public void setPortalDestination(NodeDE portalDestination) {
		this.portalDestination = portalDestination;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isSeed() {
		return seed;
	}

	public void setSeed(boolean seed) {
		this.seed = seed;
	}
}
