package lógica;

public class King extends Piece{
	private boolean isAttacked;
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
		i= posx+1;
		j= posy;
		if(i>=0 && i<8 && j>=0 && j<8){ //movimento vertical acima
			if(spot[i][j] == null)
				valid[i][j] = true;
			else if(spot[i][j].color != color)
				valid[i][j] = true;
		}
			
		i= posx-1;
		j= posy;
		if(i>=0 && i<8 && j>=0 && j<8){ //movimento vertical abaixo
			if(spot[i][j] == null)
				valid[i][j] = true;
			else if(spot[i][j].color != color)
				valid[i][j] = true;
		}
		
		i= posx;
		j= posy+1;
		if(i>=0 && i<8 && j>=0 && j<8){ //movimento horizontal à direita
			if(spot[i][j] == null)
				valid[i][j] = true;
			else if(spot[i][j].color != color)
				valid[i][j] = true;
		}
		
		i= posx;
		j= posy-1;
		if(i>=0 && i<8 && j>=0 && j<8){ //movimento horizontal à esquerda
			if(spot[i][j] == null)
				valid[i][j] = true;
			else if(spot[i][j].color != color)
				valid[i][j] = true;
		}
		
		i= posx+1;
		j= posy+1;
		if(i>=0 && i<8 && j>=0 && j<8){ //movimento na diagonal ascendente acima
			if(spot[i][j] == null)
				valid[i][j] = true;
			else if(spot[i][j].color != color)
				valid[i][j] = true;
		}
		
		i= posx-1;
		j= posy-1;
		if(i>=0 && i<8 && j>=0 && j<8){ //movimento na diagonal ascendente abaixo
			if(spot[i][j] == null)
				valid[i][j] = true;
			else if(spot[i][j].color != color)
				valid[i][j] = true;
		}
		
		i= posx+1;
		j= posy-1;
		if(i>=0 && i<8 && j>=0 && j<8){ //movimento na diagonal descendente acima
			if(spot[i][j] == null)
				valid[i][j] = true;
			else if(spot[i][j].color != color)
				valid[i][j] = true;
		}
		
		i= posx-1;
		j= posy+1;
		if(i>=0 && i<8 && j>=0 && j<8){ //movimento na diagonal descendente abaixo
			if(spot[i][j] == null)
				valid[i][j] = true;
			else if(spot[i][j].color != color)
				valid[i][j] = true;
		}
		
		if(!isMoved && !isChecked){ //rock
			if(posy == 3){ 
				isAttacked = false;
				if(spot[posx][4] == null && spot[posx][5] == null && spot[posx][6] == null){ //rock longo
					for(int k=0; k<8; k++){ //Verificar ataques
						for(int l=0; l<8; l++){
							if(spot[k][l] != null && spot[k][l].color != color){
								if(spot[k][l].type == Constants.pawn && (l == 6 || l == 5 || l == 4 || l == 3))
									isAttacked = true;
								if(spot[k][l].valid[posx][4] || spot[k][l].valid[posx][5])
									isAttacked = true;
							}
						}
					}
				}
				if(spot[posx][7] != null && !spot[posx][7].isMoved && !isAttacked)
					valid[posx][5] = true;
				
				isAttacked = false;
				if(spot[posx][1] == null && spot[posx][2] == null){ //rock curto
					for(int k=0; k<8; k++){ //Verificar ataques
						for(int l=0; l<8; l++){
							if(spot[k][l] != null && spot[k][l].color != color){
								if(spot[k][l].type == Constants.pawn && (l == 3 || l == 2 || l == 1 || l == 0))
									isAttacked = true;
								if(spot[k][l].valid[posx][1] || spot[k][l].valid[posx][2])
									isAttacked = true;
							}
						}
					}
				}
				if(spot[posx][0] != null && !spot[posx][0].isMoved && !isAttacked)
					valid[posx][1] = true;
			}
			
			
			if(posy == 4){
				isAttacked = false;
				if(spot[posx][3] == null && spot[posx][2] == null && spot[posx][1] == null){ //rock longo
					for(int k=0; k<8; k++){ //Verificar ataque
						for(int l=0; l<8; l++){
							if(spot[k][l] != null && spot[k][l].color != color){
								if(spot[k][l].type == Constants.pawn && (l == 4 || l == 3 || l == 2 || l == 1))
									isAttacked = true;
								if(spot[k][l].valid[posx][3] || spot[k][l].valid[posx][2])
									isAttacked = true;
							}
						}
					}
				}
				if(spot[posx][0] != null && !spot[posx][0].isMoved && !isAttacked)
					valid[posx][2] = true;
				
				isAttacked = false;
				if(spot[posx][5] == null && spot[posx][6] == null){ //rock curto
					for(int k=0; k<8; k++){ //Verificar ataque
						for(int l=0; l<8; l++){
							if(spot[k][l] != null && spot[k][l].color != color){
								if(spot[k][l].type == Constants.pawn && (l == 4 || l == 5 || l == 6 || l == 7))
									isAttacked = true;
								if(spot[k][l].valid[posx][5] || spot[k][l].valid[posx][6]){
									isAttacked = true;
								}
							}
						}
					}
				}
				if(spot[posx][7] != null && !spot[posx][7].isMoved && !isAttacked)
					valid[posx][6] = true;
			}
		}
	}
	
	King(int x0, int y0, int cor) {
		type = Constants.king;
		color = cor;
		posx = x0;
		posy = y0;
		lastMoved = 0;
		isMoved = false;
		isChecked = false;
		movedTwo = false;
	}
	
	King(King p){ //construtor de cópia
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
