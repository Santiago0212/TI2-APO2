package structures;

import java.util.ArrayList;

public class Tablero {
	private NodeDE head;
	private NodeDE tail;
	
	public Tablero(){
		head=null;
		tail=null;
		
	}
	
	public void addFirst(NodeDE node) {
		if(head == null) {
			head = node;
			tail = node;
		}
		else {
			node.setNext(head);
			head.setPrevious(node);
			head = node;
			
			
			tail.setNext(head);
			head.setPrevious(tail);
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
			
			tail.setNext(head);
			head.setPrevious(tail);
		}
	}
	
	public NodeDE search(int goal) {
		return search(head, goal);
	}
	
	private NodeDE search(NodeDE current, int goal) {
	
		if(current.getValue() == goal) {
			return current;
		} 
		
		if(current.equals(tail)) {
			return null;
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
		
		if(!current.equals(tail)) {
			print(current.getNext());
		}
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
		if(!current.equals(tail)) {
			delete(current.getNext(),value);
		}
	}
	
	
	
	
	
	//Modificaciones sebasssss
	public void printTablero(int colums) {
		printTablero(tail,colums,0,0);
	}
	// si la direccion es 0 va de izquierda a derecha si es 1 va de derecha a izquierda
	private void printTablero(NodeDE current,int colums,int contadorColums,int direction) {
		if(current==null){
			return;
		}
		if(current.isSeed()) {
			System.out.print("[*] ");
		}else if(current.getPlayer1()!=null && current.getPlayer2()==null){
			System.out.print("[R] ");
		}else if(current.getPlayer2()!=null && current.getPlayer1()==null){
			System.out.print("[M] ");
		}else if(current.getPlayer1()!=null && current.getPlayer2()!=null){
			System.out.print("[R M] ");
		}else {
			System.out.print("["+current.getValue()+"] ");	
		}
		if(current.equals(head) && direction==0) {
			return;
		}
		if((current.equals(search(head.getValue()-colums+1))) && direction==1){
			return;
		}
		contadorColums=contadorColums+1;
		if(contadorColums==colums) {
			contadorColums=0;
			System.out.println("");
			if(direction==0) {
				direction=1;
			}else if(direction==1){
				direction=0;
			}
		}
		if(direction==0) {
			if(!current.equals(head)) {
				if(contadorColums==0) {
					NodeDE aux=search(current.getValue()+colums-1);
					current=aux;
				}
				printTablero(current.getPrevious(),colums,contadorColums,direction);
			}
		}else if(direction==1) {
			if(!current.equals(head)) {
				if(contadorColums==0) {
					NodeDE aux=search(current.getValue()+colums+1);
					if(aux!=null) {
						current=aux.getNext();
					}else {
						aux=search(current.getValue()+colums);
						current=aux;
					}
				}else {
					NodeDE aux = current;
					current=aux.getNext();
				}
				printTablero(current,colums,contadorColums,direction);
			}else {
				printTablero(current.getNext(),colums,contadorColums,direction);
			}
		}
	}
	public void printTableroEnlaces(int colums) {
		printTableroEnlaces(tail,colums,0,0);
	}
	// si la direccion es 0 va de izquierda a derecha si es 1 va de derecha a izquierda
	private void printTableroEnlaces(NodeDE current,int colums,int contadorColums,int direction) {
		
		
		
		if(current==null){
			return;
		}
		if(current.getLetra()!="") {
			System.out.print("["+current.getLetra()+"] ");
		}else{
			System.out.print("[ ] ");	
		}
		
		if(current.equals(head) && direction==0) {
			return;
		}
		if((current.equals(search(head.getValue()-colums+1))) && direction==1){
			return;
		}
		contadorColums=contadorColums+1;
		if(contadorColums==colums) {
			contadorColums=0;
			System.out.println("");
			if(direction==0) {
				direction=1;
			}else if(direction==1){
				direction=0;
			}
		}
		if(direction==0) {
			if(!current.equals(head)) {
				if(contadorColums==0) {
					NodeDE aux=search(current.getValue()+colums-1);
					current=aux;
				}
				printTableroEnlaces(current.getPrevious(),colums,contadorColums,direction);
			}
		}else if(direction==1) {
			if(!current.equals(head)) {
				if(contadorColums==0) {
					NodeDE aux=search(current.getValue()+colums+1);
					if(aux!=null) {
						current=aux.getNext();
					}else {
						aux=search(current.getValue()+colums);
						current=aux;
					}
				}else {
					NodeDE aux = current;
					current=aux.getNext();
				}
				printTableroEnlaces(current,colums,contadorColums,direction);
			}else {
				printTableroEnlaces(current.getNext(),colums,contadorColums,direction);
			}
		}
	}
}