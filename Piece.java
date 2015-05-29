package lógica;

public abstract class Piece {
	public static final int pawn = 1;
	public static final int knight = 2;
	public static final int bishop = 3;
	public static final int rook = 4;
	public static final int queen = 5;
	public static final int king = 6;
	
	//figura
	public int type;
	public int color;
	public int posx;
	public int posy;
	public int lastMoved; //Para tratar o caso en passant para peoes
	public boolean isMoved; //Para peças com movimentos que dependem de não terem sido ainda movidas.
	public boolean isChecked;
	public boolean movedTwo; //Para tratar o caso en passant para os peões

	
	public boolean valid[][] = new boolean[8][8];
	
	public void isValid(Piece[][] spot){
	}
	
	Piece() {
	}

}
