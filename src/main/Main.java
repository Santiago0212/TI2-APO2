package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
		
		while(list.size()<numSeeds) {
			int positionSeed=(int) (Math.random()*toBoxes+1);
			if( !list.contains(positionSeed)) {
				list.add(positionSeed);
			}

		}
		
		
		
		
		
		
		
		
		
		
	}

}
