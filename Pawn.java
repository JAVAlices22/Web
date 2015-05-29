package lógica;

public class Pawn extends Piece{
	private int originalPosition;
	
	@Override
	public void isValid(Piece[][] spot){
		int i;
		int j;
		
		for(i=0; i<8 ; i++){ //resetar a matriz de validade
			for(j=0; j<8; j++){
				valid[i][j] = false;
			}
		}
		
		//Movimentos
		if(originalPosition == 1){
			i = posx+1;
			j = posy;
			if(i>=0 && i<8 && j>=0 && j<8){
				if(spot[i][j] == null) //movimento para frente
					valid[i][j] = true;
				if(posx == 1 && spot[i+1][j] == null)
					valid[i+1][j]= true;
				}
			}
			i = posx+1;
			j = posy+1;
			if(i>=0 && i<8 && j>=0 && j<8){
				if(spot[i][j] != null && spot[i][j].color != color){ //captura à direita
					valid[i][j] = true;
				}
			}
			i = posx+1;
			j = posy-1;
			if(i>=0 && i<8 && j>=0 && j<8){
				if(spot[i][j] != null && spot[i][j].color != color){ //captura à esquerda
					valid[i][j] = true;
				}
			}
			i = posx;
			j = posy;
			if(j>=0 && j<8){
				if(i==4 && j<7 && spot[i][j+1] != null && spot[i][j+1].movedTwo && spot[i][j+1].lastMoved == Board.moveCount){ //captura en passant a direita
					valid[i+1][j+1] = true;
				}
				if(i==4 && j>1 && spot[i][j-1] != null && spot[i][j-1].movedTwo && spot[i][j-1].lastMoved == Board.moveCount){ //captura en passant a esquerda
					valid[i+1][j-1] = true;
				}
			}
		
		
		if(originalPosition == 6){
			i = posx-1;
			j = posy;
			if(i>=0 && i<8 && j>=0 && j<8){
				if(spot[i][j] == null) //movimento para frente
					valid[i][j] = true;
				if(posx == 6 && spot[i-1][j] == null)
						valid[i-1][j]= true;
				}
			}
			i = posx-1;
			j = posy+1;
			if(i>=0 && i<8 && j>=0 && j<8){
				if(spot[i][j] != null && spot[i][j].color != color){ //captura à direita
					valid[i][j] = true;
				}
			}
			i = posx-1;
			j = posy-1;
			if(i>=0 && i<8 && j>=0 && j<8){
				if(spot[i][j] != null && spot[i][j].color != color){ //captura à esquerda
					valid[i][j] = true;
				}
			}
			i = posx;
			j = posy;
			if(j>=0 && j<8){
				if(i==3 && j<7 && spot[i][j+1] != null && spot[i][j+1].movedTwo && spot[i][j+1].lastMoved == Board.moveCount){ //captura en passant a direita
					valid[i-1][j+1] = true;
				}
				if(i==3 && j>1 && spot[i][j-1] != null && spot[i][j-1].movedTwo && spot[i][j-1].lastMoved == Board.moveCount){ //captura en passant a esquerda
					valid[i-1][j-1] = true;
				}
			}
		}				
			
	
	Pawn(int x0, int y0, int cor) {
		type = pawn;
		color = cor;
		posx = x0;
		posy = y0;
		originalPosition = posx;
		lastMoved = 0;
		isMoved = false;
		isChecked = false;
		movedTwo = false;
	}
	
	Pawn(Pawn p){ //construtor de cópia
		type = p.type;
		color = p.color;
		posx = p.posx;
		posy = p.posy;
		originalPosition = p.originalPosition;
		lastMoved = p.lastMoved;
		isMoved = p.isMoved;
		isChecked = p.isChecked;
		movedTwo = p.movedTwo;
		
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				valid[i][j] = p.valid[i][j];
			}
		}
		
	}

}
