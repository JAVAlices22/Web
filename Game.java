package server;

import java.io.IOException;
import java.net.SocketException;

import lógica.*;

public class Game implements Runnable{

	int id;
	
	private boolean stalemate = false;
	private boolean checkmate = false;
	Player player1;
	Player player2;
	Board b = new Board();
	int lastTurn = 0;
	
	public void run(){
		int x0=0, x1=0, y0=0, y1=0;
		b.setBoard();
		//Lógica do jogo
		while(!stalemate && !checkmate){
			System.out.printf("turno: %d jogo: %d\n", b.turn, id);
			lastTurn = b.turn;
			//Receber input das brancas
			if(b.turn == player1.color){
				try{
					x0 = player1.br.read() - 48;
					y0 = player1.br.read() - 48;
					x1 = player1.br.read() - 48;
					y1 = player1.br.read() - 48;
				}
				catch(Exception e){
					e.printStackTrace();
					try {
						player1.free();
						player2.free();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
			//Receber input das pretas
			else{
				try{
					x0 = player2.br.read() - 48;
					y0 = player2.br.read() - 48;
					x1 = player2.br.read() - 48;
					y1 = player2.br.read() - 48;
				}
				catch(Exception e){
					e.printStackTrace();
					try {
						player1.free();
						player2.free();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
			//Tentar realizar o movimento
			move(x0, y0, x1, y1);
			//Verificar se o movimento foi válido
			//Caso seja, enviar a nova posição ao cliente
			//Caso não seja, enviar uma mensagem de movimento inválido ao cliente
			if(lastTurn == b.turn && b.turn == 0)
				player1.ps.println("bizonhou");
			else if(lastTurn == b.turn && b.turn == 1)
				player2.ps.println("bizonhou");
			else
				uptdateClientsPosition(player1, player2);
		}
		if(stalemate){
			player1.ps.println("afogamento");
			player2.ps.println("afogamento");
				
		}
		//Fim de jogo: enviar resultado aos clientes
		if(checkmate){
			if(b.turn == Constants.white){
				player1.ps.println("Vitoria das pretas.");
				player2.ps.println("Vitoria das pretas.");
			}
			if(b.turn == Constants.black){
				player1.ps.println("Vitoria das brancas.");
				player2.ps.println("Vitoria das brancas.");
			}
		}
	}

	//Realizar um movimento no jogo
	//Utiliza as funções do pacote lógica para validar os movimento
	public void move (int x0, int y0, int x1, int y1){
		b.tryMove(x0,y0,x1,y1);
		b.verifyCheck();
		if(b.isInDanger()){
			b.undoMove();
		}
		stalemate = !b.isNotStalemate();
		checkmate = !b.isNotCheckmate();
	}
	
	//Enviar para os clientes a nova posição do jogo e o estado do jogo (encerrado ou não)
	private void uptdateClientsPosition(Player p1, Player p2){
		String position = new String();
		if(stalemate || checkmate)
			position = "0";
		else
			position = "1";
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(b.spot[i][j] == null)
					position = position + '-';
				else{
					if(b.spot[i][j].type == Constants.pawn && b.spot[i][j].color == Constants.white)
						position = position + 'P';
					if(b.spot[i][j].type == Constants.pawn && b.spot[i][j].color == Constants.black)
						position = position + 'p';
					if(b.spot[i][j].type == Constants.knight && b.spot[i][j].color == Constants.white)
						position = position + 'N';
					if(b.spot[i][j].type == Constants.knight && b.spot[i][j].color == Constants.black)
						position = position + 'n';
					if(b.spot[i][j].type == Constants.bishop && b.spot[i][j].color == Constants.white)
						position = position + 'B';
					if(b.spot[i][j].type == Constants.bishop && b.spot[i][j].color == Constants.black)
						position = position + 'b';
					if(b.spot[i][j].type == Constants.rook && b.spot[i][j].color == Constants.white)
						position = position + 'R';
					if(b.spot[i][j].type == Constants.rook && b.spot[i][j].color == Constants.black)
						position = position + 'r';
					if(b.spot[i][j].type == Constants.queen && b.spot[i][j].color == Constants.white)
						position = position + 'Q';
					if(b.spot[i][j].type == Constants.queen && b.spot[i][j].color == Constants.black)
						position = position + 'q';
					if(b.spot[i][j].type == Constants.king && b.spot[i][j].color == Constants.white)
						position = position + 'K';
					if(b.spot[i][j].type == Constants.king && b.spot[i][j].color == Constants.black)
						position = position + 'k';
				}
			}
		}
		player1.ps.println(position);
		player2.ps.println(position);
	}

	
	public Game(Player p1, Player p2, int i) {
		player1 = p1;
		player2 = p2;
		id = i;
	}
	////////////
	////////////
	public void print(Board b){
		char tabuleiro[][] = new char[8][8];
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(b.spot[i][j] == null)
					tabuleiro[i][j] = '-';
				else{
					if(b.spot[i][j].type == Constants.pawn && b.spot[i][j].color == Constants.white)
						tabuleiro[i][j] = 'P';
					if(b.spot[i][j].type == Constants.pawn && b.spot[i][j].color == Constants.black)
						tabuleiro[i][j] = 'p';
					if(b.spot[i][j].type == Constants.knight && b.spot[i][j].color == Constants.white)
						tabuleiro[i][j] = 'N';
					if(b.spot[i][j].type == Constants.knight && b.spot[i][j].color == Constants.black)
						tabuleiro[i][j] = 'n';
					if(b.spot[i][j].type == Constants.bishop && b.spot[i][j].color == Constants.white)
						tabuleiro[i][j] = 'B';
					if(b.spot[i][j].type == Constants.bishop && b.spot[i][j].color == Constants.black)
						tabuleiro[i][j] = 'b';
					if(b.spot[i][j].type == Constants.rook && b.spot[i][j].color == Constants.white)
						tabuleiro[i][j] = 'R';
					if(b.spot[i][j].type == Constants.rook && b.spot[i][j].color == Constants.black)
						tabuleiro[i][j] = 'r';
					if(b.spot[i][j].type == Constants.queen && b.spot[i][j].color == Constants.white)
						tabuleiro[i][j] = 'Q';
					if(b.spot[i][j].type == Constants.queen && b.spot[i][j].color == Constants.black)
						tabuleiro[i][j] = 'q';
					if(b.spot[i][j].type == Constants.king && b.spot[i][j].color == Constants.white)
						tabuleiro[i][j] = 'K';
					if(b.spot[i][j].type == Constants.king && b.spot[i][j].color == Constants.black)
						tabuleiro[i][j] = 'k';
				}
			}
		}
		for(int i=7; i>=0; i--){
			for(int j=0; j<8; j++){
				System.out.printf(" %c ", tabuleiro[i][j]);
				if(j==7)
					System.out.printf("\n\n");
					
			}
				
		}
	}
}
