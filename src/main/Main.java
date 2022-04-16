package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import structures.NodeDE;
import structures.Tablero;

public class Main {

	Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		Main inicio = new Main();
		inicio.inicio();
	}
	public void inicio() {
		
		System.out.println("Digita el numero de columnas: ");
		int numColums=sc.nextInt();
		System.out.println("Digita el numero de filas: ");
		int numRows=sc.nextInt();
		System.out.println("Digita el numero de semillas: ");
		int numSeeds=sc.nextInt();
		ArrayList<Integer>list=new ArrayList<>();
		int toBoxes=numColums*numRows;
		Tablero tablero=new Tablero();
		
		while(list.size()<numSeeds) {
			int positionSeed=(int) (Math.random()*toBoxes+1);
			if( !list.contains(positionSeed)) {
				list.add(positionSeed);
			}
		}
		
		for(int i=0;i<toBoxes;i++) {
			int aux=i+1;
			NodeDE nodeAUX=null;
			if(list.contains(i+1)) {
				nodeAUX=new NodeDE(aux,true);
			}else {
				nodeAUX=new NodeDE(aux,false);
			}
			tablero.addFirst(nodeAUX);
			
		}
		System.out.println("Las semillas han quedado hubicadas en las pocisiones: "+list);
		tablero.printTablero(numColums);
		
		
		
		
		
		
		
		
		
		
		
	}

}
