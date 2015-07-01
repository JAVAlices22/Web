package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import database.Database;
import lógica.Constants;

public class Server {

	public static int cont=0; //contador de jogos criados desde a execução do servidor
    public static boolean firstPlayer = true;
    public static Player p1;
    public static Player p2;
    public static PrintStream ps;
    public static InputStreamReader is;
    public static BufferedReader br;
    public static String login;
    public static String senha;
    public static Database d;
    public static boolean teste;
    public static Player[] array;
	    
	    public static void main(String[] args) throws IOException
	    {
	        d = new Database();
	        d.addUser("ipafrango", "cabaco");
	        try{
	            final int PORT = 3000;
	            ServerSocket SERVER = new ServerSocket(PORT);
	            //Esperar pela conecção dos jogadores
	            while(true){
	            	try{
		                //Estanciar o primeiro jogador
		            	if(firstPlayer){
		                	p1 = new Player(SERVER.accept(), Constants.white);
		                	p1.ps.println("Digite login e senha");
		                	login = p1.br.readLine();
		                	senha = p1.br.readLine();
		                	if(d.checkUser(login, senha)){
		                		firstPlayer = false;
		                		p1.ps.println("sucesso");
		                		p1.ps.println("brancas");
			                	System.out.println("p1 conectou");
		                	}
		                	else{
			                	System.out.println("falha ao conectar p1");
		                		p1.ps.println("falha");
		                		p1.free();
		                	}
	
		                }
		            	//Estanciar o segundo jogador e criar um novo jogo
		                else{
		                	p2 = new Player(SERVER.accept(), Constants.black);
		                	p2.ps.println("Digite login e senha");
		                	login = p2.br.readLine();
		                	senha = p2.br.readLine();
		                	if(d.checkUser(login, senha)){
		                		firstPlayer = true;
		                		p2.ps.println("sucesso");
		                		p2.ps.println("pretas");
			                	new Thread(new Game(p1, p2, cont)).start();
			                	cont++;
			                	System.out.println("p2 conectou");
		                	}
		                	else{
			                	System.out.println("falha ao conectar p2");
		                		p2.ps.println("falha");
		                		p2.free();
		                	}
		                }
		            }
	            	catch(Exception e){
	            		System.out.println(e);
	            	}
	            }
	        }
	        catch(Exception e){
	        	System.out.println(e);
	        }
	        
	       
	   }
	   public Server() {}
}

