package structures;

public class DoublyLinkedList {
	private NodeDE head;
	private NodeDE tail;
	
	public void addFirst(NodeDE node) {
		if(head == null) {
			head = node;
			tail = node;
		}
		else {
			node.setNext(head);
			head.setPrevious(node);
			
			head = node;
		}
	}
	
	public void addLast(NodeDE node) {
		if(tail == null) {
			head = node;
			tail = node;
		}
		else {
			tail.setNext(node);
			node.setPrevious(tail);
			
			tail = node;
		}
	}
	
	public NodeDE search(int goal) {
		return search(head, goal);
	}
	
	public NodeDE search(NodeDE current, int goal) {
		
		if(current == null) {
			return null;
		}
		
		if(current.getValue() == goal) {
			return current;
		}
		
		return search(current.getNext(),goal);
		
	}
	
	public void print() {
		print(head);
	}
	
	private void print(NodeDE current) {
		if(current==null) {
			return;
		}

		System.out.println(current.getValue());		
		print(current.getNext());
		
	}
	
	public void delete(int value) {
		delete(head, value);
	}
	
	public void delete(NodeDE current, int value) {
		if(current == null) {
			return;
		}
		if(current.getPrevious()==null && current.getValue()==value) {
			head = current.getNext();
			return;
		}
		if(current.getNext()==null && current.getValue()==value) {
			tail = current.getPrevious();
			return;
		}
		if(current.getValue()==value) { 
			current.getPrevious().setNext(current.getNext());
			current.getNext().setPrevious(current.getPrevious());
			return;
		}
		delete(current.getNext(),value);
	}
}
