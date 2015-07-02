package lógica;

public abstract class Piece {
	
	//figura
	public int type;
	public int color;
	public int posx;
	public int posy;
	public int lastMoved; //Para tratar o caso en passant para peoes
	public boolean movedTwo; //Para tratar o caso en passant para os peões
	public boolean isMoved; //Para peças com movimentos que dependem de não terem sido ainda movidas.
	public boolean isChecked;

	
	public boolean valid[][] = new boolean[8][8];
	
	public void isValid(Piece[][] spot){
	}
	
	Piece() {
	}

}
