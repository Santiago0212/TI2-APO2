package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import model.Player;
import structures.NodeDE;
import structures.Tablero;

public class Main {

    Scanner sc=new Scanner(System.in);
    Tablero tablero=new Tablero();

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
        ArrayList<Integer> portalDestinationList =new ArrayList<>();
        ArrayList<Integer> portalOriginList =new ArrayList<>();
        ArrayList<Integer> posPlayerList =new ArrayList<>();
        

        

        Random random = new Random();
        
        while(seedList.size()<seedsNumber) {
			int positionSeed=(int) (Math.random()*boxesNumber+1);
			if( !seedList.contains(positionSeed)) {
				seedList.add(positionSeed);
			}

		}
        
        while(portalDestinationList.size()<portalsNumber) {
			int portalPos=(int) (Math.random()*boxesNumber+1);
			if( !portalDestinationList.contains(portalPos)) {
				portalDestinationList.add(portalPos);
			}
		}
        
        while(portalOriginList.size()<portalsNumber) {
			int portalPos=(int) (Math.random()*boxesNumber+1);
			if( !portalOriginList.contains(portalPos) && !portalDestinationList.contains(portalPos)) {
				portalOriginList.add(portalPos);
			}
		}
        
        while(posPlayerList.size()<2) {
			int playerPos=(int) (Math.random()*boxesNumber+1);
			if( (!posPlayerList.contains(playerPos))&&( !seedList.contains(playerPos))&&(!portalDestinationList.contains(playerPos))&&(!portalOriginList.contains(playerPos))) {
				posPlayerList.add(playerPos);
			}

		}
        
        
        int counter =0;
        
        
        for(int i=0;i<boxesNumber;i++) {
            int aux = i+1;          
            NodeDE nodeAUX=null;
            
            if((portalOriginList.contains(aux))) {
            	if(seedList.contains(i+1)) {
                    nodeAUX = new NodeDE(aux,true,portalOriginList.get(counter));
                }else {
                    nodeAUX = new NodeDE(aux,false,portalOriginList.get(counter));
                }
            	counter++;
            	
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
        String[] alf={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","U","V","W","X","Y","Z"};
        int counterLetra=0;
        for (int i = 0; i<(portalDestinationList.size()); i++) {
        	NodeDE nodeAUX1 = tablero.search((portalDestinationList.get(counter)));
        	NodeDE nodeAUX2 = tablero.search((portalOriginList.get(counter)));
        	nodeAUX1.setLetra(alf[counterLetra]);
        	nodeAUX2.setLetra(alf[counterLetra]);
        	nodeAUX1.setPortalDestination(nodeAUX2);
        	counter++;
        	counterLetra++;
		}
        sc.nextLine();
        System.out.println("Digita el nombre del personaje que jugara con Rick: ");
        String rickName=sc.nextLine();
        
        System.out.println("Digita el nombre del personaje que jugara con Morty: ");
        String mortyName=sc.nextLine();
        
    	Player rick=new Player("Rick",0,rickName);
    	Player morty=new Player("Morty",0,mortyName);
    	NodeDE nodeAUX1 = tablero.search((posPlayerList.get(0)));
    	NodeDE nodeAUX2 = tablero.search((posPlayerList.get(1)));
    	nodeAUX1.setPlayer1(rick);
    	nodeAUX2.setPlayer2(morty);
    	

        System.out.println("Las semillas han quedado ubicadas en las pocisiones: "+seedList);
        System.out.println("Los portales han quedado ubicadas en las pocisiones: "+"\n"+"Origenes: "+portalOriginList+"\n"+"Destinos: "+portalDestinationList);
        System.out.println("Los jugadores han quedado ubicados en las pocisiones: "+posPlayerList);
        tablero.printTablero(columnsNumber);
        System.out.println(" ");
        System.out.println(" ");
        tablero.printTableroEnlaces(columnsNumber);
        
        String player="Rick";
        menu(player,columnsNumber);
    }
    private void menu(String player,int columnsNumber) {
    	NodeDE nodePlayer1=tablero.searchPlayer1();
    	NodeDE nodePlayer2=tablero.searchPlayer2();

		
    	System.out.println(" ");
    	System.out.println("--------------------------------------------------");
    	System.out.println(" ");
    	System.out.println("Menu: ");
    	System.out.println("Es el turno de "+player+"!. ¿Qué deseas hacer?\r\n"
    			+ "1. Tirar dado\r\n"
    			+ "2. Ver tablero\r\n"
    			+ "3. Ver enlaces\r\n"
    			+ "4. Marcador\r\n"
    			+ "");
    	int respuestaMenu=sc.nextInt();
    	switch(respuestaMenu) {
    	case 1:
            Random random = new Random();
    		int dados=(int) (Math.random()*12+2);
    		
    		System.out.println("Has sacado "+dados+" en los dados.");
    		System.out.println("¿Hacia donde quieres dirigirte?\r\n"
        			+ "1. Avanzar\r\n"
        			+ "2. Retroceder\r\n");
    		int respuestaMovimiento=sc.nextInt();
    		
    		
    		//aqui va el movimiento
    		switch(respuestaMovimiento) {
        	case 1:
        		if(player.equals("Rick")) {
        			//LLama avanzar con rick
        			player="Morty";
        			tablero.avanzarRick(nodePlayer1,dados);
        		}else {
        			//llama a avanzar con morty
        			player="Rick";
        			tablero.avanzarMorty(nodePlayer2, dados);
        		}
        		break;
        	case 2:
        		if(player.equals("Rick")) {
        			//LLama retroceder con rick
        			player="Morty";
        			tablero.retrocederRick(nodePlayer1, dados);
        		}else {
        			//llama a retroceder con morty
        			player="Rick";
        			tablero.retrocederMorty(nodePlayer2, dados);
        		}
        		//movimiento de retroceder
        		break;
    		}
    		menu(player,columnsNumber);
    		break;
    	case 2:
        	System.out.println("--------------------------------------------------");
    		tablero.printTablero(columnsNumber);
        	System.out.println(" ");
        	System.out.println("--------------------------------------------------");
            menu(player,columnsNumber);
    		break;
    	case 3:
        	System.out.println("--------------------------------------------------");
    		tablero.printTableroEnlaces(columnsNumber);
        	System.out.println(" ");
        	System.out.println("--------------------------------------------------");
            menu(player,columnsNumber);
    		break;
    	case 4:

    		Player player1=nodePlayer1.getPlayer1();
    		Player player2=nodePlayer2.getPlayer2();
    		System.out.println(player1.getName()+": "+player1.getRecolectedSeeds()+" semillas");
    		System.out.println(player2.getName()+": "+player2.getRecolectedSeeds()+" semillas");
    		menu(player,columnsNumber);
    		break;
    	}
    }

}