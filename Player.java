package server;

import java.net.Socket;
import java.io.*;

public class Player {
	Socket soc;
	int color;
	InputStreamReader ir;
	BufferedReader br;
	PrintStream ps;

	public void free() throws IOException{
		soc.close();
		br.close();
		ir.close();
		ps.close();
	}
	public Player(Socket s, int c) throws Exception{
		soc = s;
		color = c;
		ir = new InputStreamReader(soc.getInputStream());
		br = new BufferedReader(ir);
		ps = new PrintStream(soc.getOutputStream());
	}

}
