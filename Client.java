import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

public class Client {
	public static char tabuleiro[][] = new char[8][8];
	private static String resposta = "qualquer coisa";
	private static int option=0;
    private PrintStream ps;
    private InputStreamReader ir;
    private BufferedReader br;
    private Scanner scan = new Scanner(System.in);
    private JFrame frame = new JFrame("WebChess");

	
	public void run() throws Exception{
		//Conectar ao servidor e abrir as streams de comunicação
		//Socket soc = new Socket("161.24.24.36", 3000);
		
		int x0, x1, y0, y1;
		
		//Inicialização
		while(!resposta.matches("sucesso")){
			resposta = br.readLine();
			System.out.println(resposta);
			option = scan.nextInt();
			scan.nextLine();
			ps.println(option);
			resposta = br.readLine();
			System.out.println(resposta);
			ps.println(scan.nextLine());
			ps.println(scan.nextLine());
			resposta = br.readLine();
		}
		
		 //Atribuir cor das peças ao cliente e inicializar tabuleiro
		resposta = br.readLine();
		System.out.println(resposta);
		updatePosition("1RNBQKBNRPPPPPPPP--------------------------------pppppppprnbqkbnr");
		print();
		//Lógica de jogo
		if(resposta.matches("brancas")){ //Lógica de jogo com as brancas
			while(!resposta.matches("afogamento") && !resposta.matches("Vitoria das brancas.") 
					&& !resposta.matches("Vitoria das pretas.")){
				//Ler jogada do terminal e enviar para o servidor
				x0 = scan.nextInt();
				y0 = scan.nextInt();
				x1 = scan.nextInt();
				y1 = scan.nextInt();
				ps.print(x0);
				ps.print(y0);
				ps.print(x1);
				ps.print(y1);
				//Imprimir a posição depois da jogada realizada, se for válida 
				//e em seguida imprimir a posição depois da jogada das pretas
				resposta = br.readLine();
				if(!resposta.matches("bizonhou")){
					if(updatePosition(resposta)){
						print();
						break;
					}
					print();
					resposta = br.readLine();
					if(updatePosition(resposta)){
						print();
						break;
					}
					print();
				}
			}
		}
		else{ //lógica de jogo com as pretas
			while(!resposta.matches("afogamento") && !resposta.matches("Vitoria das brancas.") 
					&& !resposta.matches("Vitoria das pretas.")){
				//Imprimir a posição depois da jogada das brancas
				if(!resposta.matches("bizonhou")){	
					resposta = br.readLine();
					if(updatePosition(resposta)){
						print();
						break;
					}
					print();
				}
				//Ler jogada do terminal e enviar para o servidor
				x0 = scan.nextInt();
				y0 = scan.nextInt();
				x1 = scan.nextInt();
				y1 = scan.nextInt();
				ps.print(x0);
				ps.print(y0);
				ps.print(x1);
				ps.print(y1);
				resposta = br.readLine();
				//Imprimir a posição depois da jogada realizada, se for válida 
				if(!resposta.matches("bizonhou")){
					if(updatePosition(resposta)){
						print();
						break;
					}
					print();
				}
			}
		}
		//Fim do jogo
		resposta = br.readLine();
		System.out.println(resposta);
	}

	//Analisa a string enviada pelo servidor após uma jogada e atualiza o tabuleiro
	//O primeiro caracter indica se o jogo acabou (0 indica acabou e 1 indica que não acabou)
	//Os demais representam a nova posição das peças
	private boolean updatePosition(String s){
		char position[] = s.toCharArray();
		boolean acabou = false;
		if(position[0] == '0')
			acabou = true;
		for(int i=7; i>=0; i--){
			for(int j=0; j<8; j++){
				tabuleiro[i][j] = position[i*8 + j+1];
			}
		}
		return acabou;
	}
	
	//Imprime o tabuleiro
	private void print(){
		//new TabuleiroRun(tabuleiro).run();
		
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        frame.add(new GUI(tabuleiro));  
        frame.setSize(615, 640);  
        frame.validate();  
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);  
		
		
		for(int i=7; i>=0; i--){
			for(int j=0; j<8; j++){
				System.out.printf(" %c ", tabuleiro[i][j]);
				if(j==7)
					System.out.printf("\n\n");
					
			}
				
		}
	}
	
	public Client(Socket soc) throws Exception {
	    ps = new PrintStream(soc.getOutputStream());
            ir = new InputStreamReader(soc.getInputStream());
            br = new BufferedReader(ir);
	}
}
