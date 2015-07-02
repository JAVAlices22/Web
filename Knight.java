package lógica;

public class Knight extends Piece{


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
		i= posx+2;
		j= posy+1;
		if(i>=0 && i<8 && j>=0 && j<8){
			if(spot[i][j] == null)
				valid[i][j] = true;
			else if(spot[i][j].color != color)
				valid[i][j] = true;
		}
		
		i= posx+1;
		j= posy+2;
		if(i>=0 && i<8 && j>=0 && j<8){
			if(spot[i][j] == null)
				valid[i][j] = true;
			else if(spot[i][j].color != color)
				valid[i][j] = true;
		}
		
		i= posx-2;
		j= posy-1;
		if(i>=0 && i<8 && j>=0 && j<8){
			if(spot[i][j] == null)
				valid[i][j] = true;
			else if(spot[i][j].color != color)
				valid[i][j] = true;
		}
		
		i= posx-1;
		j= posy-2;
		if(i>=0 && i<8 && j>=0 && j<8){
			if(spot[i][j] == null)
				valid[i][j] = true;
			else if(spot[i][j].color != color)
				valid[i][j] = true;
		}
		
		i= posx+2;
		j= posy-1;
		if(i>=0 && i<8 && j>=0 && j<8){
			if(spot[i][j] == null)
				valid[i][j] = true;
			else if(spot[i][j].color != color)
				valid[i][j] = true;
		}
		
		i= posx-2;
		j= posy+1;
		if(i>=0 && i<8 && j>=0 && j<8){
			if(spot[i][j] == null)
				valid[i][j] = true;
			else if(spot[i][j].color != color)
				valid[i][j] = true;
		}
		
		i= posx-1;
		j= posy+2;
		if(i>=0 && i<8 && j>=0 && j<8){
			if(spot[i][j] == null)
				valid[i][j] = true;
			else if(spot[i][j].color != color)
				valid[i][j] = true;
		}
		
		i= posx+1;
		j= posy-2;
		if(i>=0 && i<8 && j>=0 && j<8){
			if(spot[i][j] == null)
				valid[i][j] = true;
			else if(spot[i][j].color != color)
				valid[i][j] = true;
		}

	}
	
	Knight(int x0, int y0, int cor) {
		type = Constants.knight;
		color = cor;
		posx = x0;
		posy = y0;
		lastMoved = 0;
		isMoved = false;
		isChecked = false;
		movedTwo = false;
	}
	
	Knight(Knight p){ //construtor de cópia
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
