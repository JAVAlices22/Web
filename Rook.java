package lógica;

public class Rook extends Piece{
	
	@Override
	public void isValid(Piece[][] spot){
		boolean blocked = false;
		int i;
		int j;
		
		for(i=0; i<8 ; i++){ //resetar a matriz de validade
			for(j=0; j<8; j++){
				valid[i][j] = false;
			}
		}
		
		//Movimentos
		for(i=posx+1; i<8 && !blocked; i++){ //movimento na vertical acima
			if(spot[i][posy] == null)
				valid[i][posy] = true;
			else if(spot[i][posy].color != color){
				valid[i][posy] = true;
				blocked = true;
			}
			else if(spot[i][posy].color == color)
				blocked = true;
		}
		blocked = false;
		for(i=posx-1; i>=0 && !blocked; i--){ //movimento na vertical abaixo
			if(spot[i][posy] == null)
				valid[i][posy] = true;
			else if(spot[i][posy].color != color){
				valid[i][posy] = true;
				blocked = true;			
			}
			else if(spot[i][posy].color == color)
				blocked = true;
		}
		blocked = false;
		for(i = posy+1; i<8 && !blocked; i++){ //movimento na horiontal à direita
			if(spot[posx][i] == null)
				valid[posx][i] = true;
			else if(spot[posx][i].color != color){
				valid[posx][i] = true;
				blocked = true;
			}
			else if(spot[posx][i].color != color)
				blocked = true;
		}
		blocked = false;
		for(i = posy-1; i>=0 && !blocked; i--){ //movimento na horiontal à esquerda
			if(spot[posx][i] == null)
				valid[posx][i] = true;
			else if(spot[posx][i].color != color){
				valid[posx][i] = true;
				blocked = true;
			}
			else if(spot[posx][i].color != color)
				blocked = true;
		}	
		
	}
	
	
	Rook(int x0, int y0, int cor) {
		type = rook;
		color = cor;
		posx = x0;
		posy = y0;
		lastMoved = 0;
		isMoved = false;
		isChecked = false;
		movedTwo = false;
	}

	Rook(Rook p){ //construtor de cópia
		type = p.type;
		color = p.color;
		posx = p.posx;
		posy = p.posy;
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
