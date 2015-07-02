package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.util.ArrayList;

import database.*;
import lógica.Constants;

public class Server {

	private static int cont=0; //contador de jogos criados desde a execução do servidor
    private static boolean firstPlayer = true;
    private static Player p1;
    private static Player p2;
    private static String login;
    private static String senha;
    private static Database d;
    private static int option = 0;
	    
	    @SuppressWarnings("unchecked")
		public static void main(String[] args) throws IOException
	    {
	        d = new Database();
			try{ //Carrega o arquivo, se existir.
				FileInputStream arquivoLeitura = new FileInputStream("database.dat"); //carrega o arquivo
				ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura); //classe responsável por recuperar os objetos no arquivo
				d.usuarios = (ArrayList<User>)objLeitura.readObject(); //lê o objeto usuarios do arquivo
			    objLeitura.close();
		        arquivoLeitura.close();
			}catch(Exception ex){
			   System.out.println("Arquivo não encontrado");
			} 
	        
			for(int i = 0; i<d.usuarios.size(); i++){
				System.out.printf("usuario %d "+d.usuarios.get(i).login+" "+d.usuarios.get(i).senha+"\n", i);
			}
	        try{
	            final int PORT = 3000;
	            ServerSocket SERVER = new ServerSocket(PORT);
	            //Esperar pela conecção dos jogadores
	            while(true){
                	option = 0;
	            	try{
		                //Estanciar o primeiro jogador
		            	if(firstPlayer){
		                	p1 = new Player(SERVER.accept(), Constants.white);
		                	while(option != 1){
			                	p1.ps.println("Logar: 1  Cadastrar: 2 Remover: 3");
			                	option = p1.br.read()- 48;
			                	p1.br.readLine();
			                	System.out.println(option);
			                	switch(option){
			                		case 1:
					                	p1.ps.println("Digite login e senha");
					                	login = p1.br.readLine();
					                	System.out.println("login:"+login);
					                	senha = p1.br.readLine();
					                	System.out.println("senha:"+senha);
					                	if(d.checkUser(login, senha)){
					                		firstPlayer = false;
					                		p1.ps.println("sucesso");
					                		p1.ps.println("brancas");
						                	System.out.println("p1 conectou");
					                	}
					                	else{
						                	System.out.println("falha ao conectar p1");
					                		p1.ps.println("falha");
					                		option = 0;
					                	}
					                	break;
						            case 2:
						            	p1.ps.println("Digite login e senha a serem cadastrados");
					                	login = p1.br.readLine();
					                	senha = p1.br.readLine();
						            	System.out.println(d.addUser(login, senha));
						            	d.save();
					                	p1.ps.println("salvo");
						            	break;
						            case 3:
						            	p1.ps.println("Digite login e senha a serem removidos");
					                	login = p1.br.readLine();
					                	senha = p1.br.readLine();
						            	d.deleteUser(login, senha);
						            	d.save();
					                	p1.ps.println("removido");
						            	break;
			                	}
		                	}
		                }
		            	//Estanciar o segundo jogador e criar um novo jogo
		                else{
		                	p2 = new Player(SERVER.accept(), Constants.black);
		                	while(option != 1){
			                	p2.ps.println("Logar: 1  Cadastrar: 2 Remover: 3");
			                	option = p2.br.read() - 48;
			                	p2.br.readLine();
			                	System.out.println(option);
			                	switch(option){
			                		case 1:
					                	p2.ps.println("Digite login e senha");
					                	login = p2.br.readLine();
					                	System.out.println("login:"+login);
					                	senha = p2.br.readLine();
					                	System.out.println("senha:"+senha);
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
					                		option = 0;
					                	}
					                	break;
						            case 2:
						            	p2.ps.println("Digite login e senha a serem cadastrados");
					                	login = p2.br.readLine();
					                	senha = p2.br.readLine();
						            	System.out.println(d.addUser(login, senha));
						            	d.save();
					                	p2.ps.println("adicionado");
						            	break;
						            case 3:
						            	p2.ps.println("Digite login e senha a serem removidos");
					                	login = p2.br.readLine();
					                	senha = p2.br.readLine();
						            	d.deleteUser(login, senha);
						            	d.save();
					                	p2.ps.println("removido");
						            	break;
			                	}
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
