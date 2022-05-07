package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import model.Player;
import model.PlayerData;
import structures.NodeDE;
import structures.Tablero;

public class Main {

    Scanner sc=new Scanner(System.in);
    Tablero tablero=new Tablero();
    
    static PlayerData data;
    
    public static void main(String[] args) {
    	data = new PlayerData();
    	data.loadJSON();
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
    	

        System.out.println("\nLas semillas han quedado ubicadas en las pocisiones: "+seedList);
        System.out.println("Los portales han quedado ubicadas en las pocisiones: "+"\n"+"Origenes: "+portalOriginList+"\n"+"Destinos: "+portalDestinationList);
        System.out.println("Los jugadores han quedado ubicados en las pocisiones: "+ posPlayerList + "\n");
        System.out.println("--------------------------------------------------");
        tablero.printTablero(columnsNumber);
        System.out.println(" ");
        System.out.println(" ");
        tablero.printTableroEnlaces(columnsNumber);
        
        String player="Rick";
        menu(player,columnsNumber,seedsNumber);
    }
    
    private void menu(String player,int columnsNumber, int seedsNumber) {
    	NodeDE nodePlayer1=tablero.searchPlayer1();
    	NodeDE nodePlayer2=tablero.searchPlayer2();
    	
    	
    	int totalRecolected = nodePlayer1.getPlayer1().getRecolectedSeeds() + nodePlayer2.getPlayer2().getRecolectedSeeds();
    	
    	if(totalRecolected>=seedsNumber) {
    		System.out.println("-----------------------------");
    		System.out.println("Se recolectaron todas las semillas.");
    		data.addPlayer(nodePlayer1.getPlayer1());
    		data.addPlayer(nodePlayer2.getPlayer2());
    		data.saveJSON();
    		showTopFive();
    		sc.next();
    		System.exit(0);
    	}
    	
		
    	System.out.println(" ");
    	System.out.println("--------------------------------------------------");
    	System.out.println(" ");
    	System.out.println("Menu: ");
    	System.out.println("Es el turno de "+player+"! ¿Qué deseas hacer?\r\n"
    			+ "1. Tirar dado\r\n"
    			+ "2. Ver tablero\r\n"
    			+ "3. Ver enlaces\r\n"
    			+ "4. Marcador\r\n"
    			+ "");
    	int respuestaMenu=sc.nextInt();
    	switch(respuestaMenu) {
    	case 1:
    		int dados=(int) (Math.random()*12+2);
    		
    		System.out.println("Has sacado "+dados+" en los dados.");
    		System.out.println("¿Hacia donde quieres dirigirte?\r\n");
    		tablero.printTablero(columnsNumber); 
    		System.out.println("\n\n1. Avanzar\r\n"
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
    		menu(player,columnsNumber, seedsNumber);
    		break;
    	case 2:
        	System.out.println("--------------------------------------------------");
    		tablero.printTablero(columnsNumber);
        	System.out.println(" ");
        	System.out.println("--------------------------------------------------");
            menu(player,columnsNumber, seedsNumber);
    		break;
    	case 3:
        	System.out.println("--------------------------------------------------");
    		tablero.printTableroEnlaces(columnsNumber);
        	System.out.println(" ");
        	System.out.println("--------------------------------------------------");
            menu(player,columnsNumber, seedsNumber);
    		break;
    	case 4:

    		Player player1=nodePlayer1.getPlayer1();
    		Player player2=nodePlayer2.getPlayer2();
    		System.out.println(player1.getName()+": "+player1.getRecolectedSeeds()+" semillas");
    		System.out.println(player2.getName()+": "+player2.getRecolectedSeeds()+" semillas");
    		menu(player,columnsNumber, seedsNumber);
    		break;
    	}
    }
    
    public static void showTopFive() {
    	ArrayList<Player> players = data.players;
    	
    	Player a, b;
		boolean changed=false;
		for(int i=0; i<players.size()-1; i++) {
			a = players.get(i);
			for(int j=(i+1); j<players.size() && !changed; j++) {
				b = players.get(j);
				if(a.compareTo(b)>0) {
					players.remove(j);
					players.add(i,b);
					changed = true;
					i--;
				}
			}
			changed = false;
		}
		System.out.println("\n--------------TOP CINCO--------------");
		
		boolean finished = false;
		for(int i = 0; i<players.size() && !finished; i++) {
			if (i>=5) {
				finished = true;
			} else {
				System.out.println((i+1)+". Jugador: "+players.get(i).getUserName()+"| Semillas recolectadas: "+players.get(i).getRecolectedSeeds());
			}
		}
    	
    }

}