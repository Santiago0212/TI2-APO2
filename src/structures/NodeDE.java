package structures;

import model.Player;

public class NodeDE {

			
	//Informaci�n
	private int value;
	
	//Enlaces
	private NodeDE previous;
	private NodeDE next;
	private Player player;
	
	public NodeDE(int value) {
		this.value = value;
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
		

	
	
}
