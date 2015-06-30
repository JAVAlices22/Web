package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import lógica.Constants;

public class Server {

	public static int cont=0; //contador de jogos criados desde a execução do servidor
    public static boolean firstPlayer = true;
    public static Player p1;
    public static Player p2;
	    
	    public static void main(String[] args) throws IOException
	    {
	        
	        try{
	            final int PORT = 3000;
	            ServerSocket SERVER = new ServerSocket(PORT);
	            //Esperar pela conecção dos jogadores
	            while(true){
	                //Estanciar o primeiro jogador
	            	if(firstPlayer){
	                	p1 = new Player(SERVER.accept(), Constants.white);
	                	firstPlayer = false;
	                	PrintStream ps = new PrintStream(p1.soc.getOutputStream());
	                	ps.println("brancas");
	                	System.out.println("p1 conectou");
	                }
	            	//Estanciar o segundo jogador e criar um novo jogo
	                else{
	                	p2 = new Player(SERVER.accept(), Constants.black);
	                	PrintStream ps = new PrintStream(p2.soc.getOutputStream());
	                	ps.println("pretas");
	                	new Thread(new Game(p1, p2, cont)).start();
	                	cont++;
	                	firstPlayer = true;
	                	System.out.println("p2 conectou");
	                }
	            }
	        }
	        catch(Exception e){
	        	System.out.println(e);
	        }
	        
	       
	   }
	   public Server() {}
}
