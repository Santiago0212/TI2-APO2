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
        int columnsNumber=sc.nextInt();
        System.out.println("Digita el numero de filas: ");
        int rowsNumber=sc.nextInt();

        System.out.println("Digita el numero de semillas: ");
        int seedsNumber=sc.nextInt();
        
        int boxesNumber = columnsNumber*rowsNumber;

        while(seedsNumber>boxesNumber) {
            System.out.println("Digite un número válido de super semillas");
            seedsNumber=sc.nextInt();
        }
        
        System.out.println("Digita el numero de portales:" );
        int portalsNumber = sc.nextInt();
        
        while(portalsNumber>(boxesNumber/2)) {
            System.out.println("Digite un número válido de portales");
            portalsNumber=sc.nextInt();
        }
        

        ArrayList<Integer> seedList =new ArrayList<>();
        ArrayList<Integer> portalList =new ArrayList<>();
        

        Tablero tablero=new Tablero();

        Random random = new Random();

        while(seedList.size()<seedsNumber) {
            int seedPos = random.nextInt(boxesNumber + 1) + 1;
            if(!seedList.contains(seedPos)) {
                seedList.add(seedPos);
            }
        }
        
        while(portalList.size()<(portalsNumber*2)) {
            int portalPos = random.nextInt(boxesNumber + 1) + 1;
            if(!portalList.contains(portalPos)) {
                portalList.add(portalPos);
            }
        }
        
        int counter =0;
        
        for(int i=0;i<boxesNumber;i++) {
            int aux = i+1;          
            NodeDE nodeAUX=null;
            
            if((counter+1)==aux) {
            	if(seedList.contains(i+1)) {
                    nodeAUX = new NodeDE(aux,true,portalList.get(counter+1));
                }else {
                    nodeAUX = new NodeDE(aux,false,portalList.get(counter+1));
                }
            	counter=+2;
            	
            } else {
            	if(seedList.contains(i+1)) {
                    nodeAUX = new NodeDE(aux,true,-1);
                }else {
                    nodeAUX = new NodeDE(aux,false,-1);
                }
            }
            
   
            
            
            tablero.addFirst(nodeAUX);
            
        }
        
        counter = 0;
        
        for (int i = 0; i<(portalList.size()/2); i++) {
        	NodeDE nodeAUX1 = tablero.search((portalList.get(counter+1)));
        	NodeDE nodeAUX2 = tablero.search((portalList.get(counter)));
        	nodeAUX1.setPortalDestination(nodeAUX2);
        	
        	counter+=2;
        	
		}

        System.out.println("Las semillas han quedado ubicadas en las pocisiones: "+seedList);
        System.out.println("Los portales han quedado ubicadas en las pocisiones: "+portalList);
        tablero.printTablero(columnsNumber);
        
        
        
       
    }

}