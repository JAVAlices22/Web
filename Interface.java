package lógica;
import java.util.Scanner;

public abstract class Interface {
	
	private static boolean stalemate = false;
	private static boolean checkmate = false;
	
	public static void print(Board b){
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
	
	
	Interface() {
	}

	public static void main(String[] args) {
		int x0, x1, y0, y1;
		Scanner scan = new Scanner(System.in);
		
		Board b = new Board();
		b.setBoard();
		print(b);
		while(!stalemate && !checkmate){
			System.out.println("Digite x0, y0, x1, y1:");
			x0 = scan.nextInt();
			y0 = scan.nextInt();
			x1 = scan.nextInt();
			y1 = scan.nextInt();
				
			b.tryMove(x0,y0,x1,y1);
			b.verifyCheck();
			if(b.isInDanger()){
				b.undoMove();
			}
			stalemate = !b.isNotStalemate();
			checkmate = !b.isNotCheckmate();
			System.out.printf("%d\n", b.turn); //Imprimir o turno
			print(b);
			}
		scan.close();
		if(stalemate)
			System.out.println("Afogamento.");
		if(checkmate){
			if(b.turn == Constants.white)
				System.out.println("Vitória das pretas.");
			if(b.turn == Constants.black)
				System.out.println("Vitória das brancas.");
		}	
	}
}
