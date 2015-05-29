package lógica;
import java.util.Scanner;

public class Board {
	public static final int pawn = 1;
	public static final int knight = 2;
	public static final int bishop = 3;
	public static final int rook = 4;
	public static final int queen = 5;
	public static final int king = 6;
	
	public static final int white = 0;
	public static final int black = 1;
	
	public Piece[][] spot = new Piece[8][8];
	private Piece[][] temp = new Piece[8][8];
	public static int moveCount;
	private int whiteKingPosX;
	private int whiteKingPosY;
	private int blackKingPosX;
	private int blackKingPosY;
	private int whiteKingPosX0;
	private int whiteKingPosY0;
	private int blackKingPosX0;
	private int blackKingPosY0;
	public int turn;

	
	public void setBoard(){
		moveCount = 0;
		whiteKingPosX = 0;
		whiteKingPosY = 4;
		blackKingPosX = 7;
		blackKingPosY = 4;
		turn = white;
		spot[0][0] = new Rook(0,0,white);
		spot[0][7] = new Rook(0,7,white);
		spot[0][1] = new Knight(0,1,white);
		spot[0][6] = new Knight(0,6,white);
		spot[0][2] = new Bishop(0,2,white);
		spot[0][5] = new Bishop(0,5,white);
		spot[0][3] = new Queen(0,3,white);
		spot[0][4] = new King(0,4, white);
		for(int i=0; i<8; i++)
			spot[1][i] = new Pawn(1,i,white);
		for(int j=2; j<6; j++){
			for(int k=0; k<8; k++)
				spot[j][k] = null;
		}
		spot[7][0] = new Rook(7,0,black);
		spot[7][7] = new Rook(7,7,black);
		spot[7][1] = new Knight(7,1,black);
		spot[7][6] = new Knight(7,6,black);
		spot[7][2] = new Bishop(7,2,black);
		spot[7][5] = new Bishop(7,5,black);
		spot[7][3] = new Queen(7,3,black);
		spot[7][4] = new King(7,4,black);
		for(int i=0; i<8; i++)
			spot[6][i] = new Pawn(6,i,black);
		updateBoard(whiteKingPosX,whiteKingPosY,blackKingPosX,blackKingPosY);
	}
	
	//Mover uma peça
	public void tryMove(int x0, int y0, int x1, int y1){	
		//Copiar tabuleiro, as posições dos reis para serem usados pelo método undoMove
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(spot[i][j] == null)
					temp[i][j] = null;
				else if(spot[i][j].type == pawn)
					temp[i][j] = new Pawn((Pawn) spot[i][j]);
				else if(spot[i][j].type == knight)
					temp[i][j] = new Knight((Knight) spot[i][j]);
				else if(spot[i][j].type == bishop)
					temp[i][j] = new Bishop((Bishop) spot[i][j]);
				else if(spot[i][j].type == rook)
					temp[i][j] = new Rook((Rook) spot[i][j]);
				else if(spot[i][j].type == queen)
					temp[i][j] = new Queen((Queen) spot[i][j]);
				else if(spot[i][j].type == king)
					temp[i][j] = new King((King) spot[i][j]);
			}
		}
		whiteKingPosX0 = whiteKingPosX;
		whiteKingPosY0 = whiteKingPosY;
		blackKingPosX0 = blackKingPosX;
		blackKingPosY0 = blackKingPosY;

		//Mover, caso seja um movimento possível
		if(spot[x0][y0] != null && spot[x0][y0].valid[x1][y1] == true && spot[x0][y0].color == turn){
			//Parâmetros a serem revisados pelo método validateMove
			moveCount++; 
			turn = moveCount % 2;
			
			//Movimentos
			if(spot[x0][y0].type == pawn && spot[x1][y1]==null && y0 != y1){ //Ocorreu en passant
				spot[x0][y0].isMoved = true;
				spot[x0][y0].lastMoved = moveCount;
				spot[x1][y1] = spot[x0][y0];
				spot[x1][y1].posx = x1;
				spot[x1][y1].posy = y1;
				spot[x0][y0] = null;
				spot[x0][y1] = null; //Remover o peão capturado
			}
			else if(spot[x0][y0].type == king && Math.abs(y1-y0) == 2){ //Ocorreu Rock
				spot[x0][y0].isMoved = true;
				spot[x0][y0].lastMoved = moveCount;
				spot[x1][y1] = spot[x0][y0];
				spot[x1][y1].posx = x1;
				spot[x1][y1].posy = y1;
				spot[x0][y0] = null;
				if(y0 == 3 && y1 == 1){ //Mover a torre
					spot[x0][0].isMoved = true;
					spot[x0][0].lastMoved = moveCount;
					spot[x0][2]=spot[x0][0];
					spot[x0][2].posx = x0;
					spot[x0][2].posy = 2;
					spot[x0][0]=null;
				}
				if(y0 == 3 && y1 == 5){ //Mover a torre
					spot[x0][7].isMoved = true;
					spot[x0][7].lastMoved = moveCount;
					spot[x0][4]=spot[x0][7];
					spot[x0][4].posx = x0;
					spot[x0][4].posy = 4;
					spot[x0][7]=null;
				}
				if(y0 == 4 && y1 == 6){ //Mover a torre
					spot[x0][7].isMoved = true;
					spot[x0][7].lastMoved = moveCount;
					spot[x0][5]=spot[x0][7];
					spot[x0][5].posx = x0;
					spot[x0][5].posy = 5;
					spot[x0][7]=null;
				}
				if(y0 == 4 && y1 == 2){	//Mover a torre
					spot[x0][0].isMoved = true;
					spot[x0][0].lastMoved = moveCount;
					spot[x0][3]=spot[x0][0];
					spot[x0][3].posx = x0;
					spot[x0][3].posy = 3;
					spot[x0][0]=null;
				}
			}
				
			else{ //Movimentos regulares
				spot[x0][y0].isMoved = true;
				spot[x0][y0].lastMoved = moveCount;
				spot[x1][y1] = spot[x0][y0];
				spot[x1][y1].posx = x1;
				spot[x1][y1].posy = y1;
				spot[x0][y0] = null;
			}
		}
		
		if(spot[x1][y1] != null && spot[x1][y1].type == pawn && Math.abs(x1 - x0) == 2) //Marcar a possibilidade de en passant
			spot[x1][y1].movedTwo = true;
		
		if(spot[x1][y1] != null && spot[x1][y1].type == pawn && (x1==7 || x1==0)){ //Promoção de peão
			System.out.println("Promoção: Cavalo=2 Bispo=3 Torre=4 Dama=qualquer outra coisa, bizonho");
			Scanner s = new Scanner(System.in);
			switch(s.nextInt()){
				case knight:
					spot[x1][y1] =  new Knight(x1,y1,spot[x1][y1].color);
					break;
				case bishop:
					spot[x1][y1] =  new Bishop(x1,y1,spot[x1][y1].color);
					break;
				case rook:
					spot[x1][y1] =  new Rook(x1,y1,spot[x1][y1].color);
					break;
				default:
					spot[x1][y1] =  new Queen(x1,y1,spot[x1][y1].color);
			}
		}
		
		if(spot[x1][y1] != null && spot[x1][y1].type == king && spot[x1][y1].color == white){ //Atualizar a posição do rei para as verificaçoes de jogada válida
			whiteKingPosX = x1;
			whiteKingPosY = y1;
		}
		if(spot[x1][y1] != null && spot[x1][y1].type == king && spot[x1][y1].color == black){
			blackKingPosX = x1;
			blackKingPosY = y1;
		}
		updateBoard(whiteKingPosX,whiteKingPosY,blackKingPosX,blackKingPosY);
	}
	
	//Atualizar as posições válidas de todas as peças
	private void updateBoard(int whiteKingPosX, int whiteKingPosY, int blackKingPosX, int blackKingPosY){	
		for(int i=0; i<8; i++){ 
			for(int j=0; j<8; j++){
				if(spot[i][j] != null)
					spot[i][j].isValid(spot);
			}
		}
		spot[whiteKingPosX][whiteKingPosY].isValid(spot); //Redundância necessária para o verificar o rock
		spot[blackKingPosX][blackKingPosY].isValid(spot);
	}
	
	//Verificar se houve xeque
	public void verifyCheck(){
		spot[whiteKingPosX][whiteKingPosY].isChecked = false;
		spot[blackKingPosX][blackKingPosY].isChecked = false;
		for(int i=0; i<8; i++){ 
			for(int j=0; j<8; j++){
				if(spot[i][j] != null && spot[i][j].color != white && spot[i][j].valid[whiteKingPosX][whiteKingPosY])
					spot[whiteKingPosX][whiteKingPosY].isChecked = true;
				if(spot[i][j] != null && spot[i][j].color != black && spot[i][j].valid[blackKingPosX][blackKingPosY])
					spot[blackKingPosX][blackKingPosY].isChecked = true;
			}
		}
	}
	
	//Verificar se o jogador pos o próprio rei em perigo. Retorna true em caso afirmativo.
	public boolean isInDanger(){
		if(turn == black && spot[whiteKingPosX][whiteKingPosY].isChecked)
			return true;
		if(turn == white && spot[blackKingPosX][blackKingPosY].isChecked)
			return true;
		return false;
	}
	
	//Restaurar as configurações do tabuleiro para as da última jogada válida
	public void undoMove(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(temp[i][j] == null)
					spot[i][j] = null;
				else if(temp[i][j].type == pawn)
					spot[i][j] = new Pawn((Pawn) temp[i][j]);
				else if(temp[i][j].type == knight)
					spot[i][j] = new Knight((Knight) temp[i][j]);
				else if(temp[i][j].type == bishop)
					spot[i][j] = new Bishop((Bishop) temp[i][j]);
				else if(temp[i][j].type == rook)
					spot[i][j] = new Rook((Rook) temp[i][j]);
				else if(temp[i][j].type == queen)
					spot[i][j] = new Queen((Queen) temp[i][j]);
				else if(temp[i][j].type == king)
					spot[i][j] = new King((King) temp[i][j]);
			}
		}
		whiteKingPosX = whiteKingPosX0;
		whiteKingPosY = whiteKingPosY0;
		blackKingPosX = blackKingPosX0;
		blackKingPosY = blackKingPosY0;
		
		moveCount--;
		turn = moveCount % 2;

		updateBoard(whiteKingPosX,whiteKingPosY,blackKingPosX,blackKingPosY);
	}
	
	//Verifica se houve afogamento. Retorna true se não for o caso.
	public boolean isNotStalemate(){
		boolean canMove = true;
		if(turn == white && !spot[whiteKingPosX][whiteKingPosY].isChecked){
			canMove = false;
			for(int i=0; i<8  && !canMove; i++){
				for(int j=0; j<8  && !canMove; j++){
					for(int k=0; k<8  && !canMove; k++){
						for(int l=0; l<8  && !canMove; l++){
							if(spot[i][j] != null && spot[i][j].color == white && spot[i][j].valid[k][l]){
								tryMove(i,j,k,l);
								verifyCheck();
								if(!isInDanger())
									canMove = true;
								undoMove();
							}
						}
					}
				}
			}
		}
		if(turn == black && !spot[blackKingPosX][blackKingPosY].isChecked){
			canMove = false;
			for(int i=0; i<8  && !canMove; i++){
				for(int j=0; j<8  && !canMove; j++){
					for(int k=0; k<8  && !canMove; k++){
						for(int l=0; l<8  && !canMove; l++){
							if(spot[i][j] != null && spot[i][j].color == black && spot[i][j].valid[k][l]){
								tryMove(i,j,k,l);
								verifyCheck();
								if(!isInDanger())
									canMove = true;
								undoMove();
							}
						}
					}
				}
			}
		}
		return canMove;
	}
	
	//Verifica se houve xeque-mate. Retorna true se não for o caso.
	public boolean isNotCheckmate(){
		boolean canMove = true;
		if(turn == white && spot[whiteKingPosX][whiteKingPosY].isChecked){
			canMove = false;
			for(int i=0; i<8  && !canMove; i++){
				for(int j=0; j<8  && !canMove; j++){
					for(int k=0; k<8  && !canMove; k++){
						for(int l=0; l<8  && !canMove; l++){
							if(spot[i][j] != null && spot[i][j].color == white  && spot[i][j].valid[k][l]){
								tryMove(i,j,k,l);
								verifyCheck();
								if(!isInDanger())
									canMove = true;
								undoMove();
							}
						}
					}
				}
			}
		}
		if(turn == black && spot[blackKingPosX][blackKingPosY].isChecked){
			canMove = false;
			for(int i=0; i<8  && !canMove; i++){
				for(int j=0; j<8  && !canMove; j++){
					for(int k=0; k<8  && !canMove; k++){
						for(int l=0; l<8  && !canMove; l++){
							if(spot[i][j] != null && spot[i][j].color == black  && spot[i][j].valid[k][l]){
								tryMove(i,j,k,l);
								verifyCheck();
								if(!isInDanger())
									canMove = true;
								undoMove();
							}
						}
					}
				}
			}
		}
		return canMove;
	}
}


