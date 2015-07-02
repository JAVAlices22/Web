import java.net.Socket;

public class ClientMain{
    public static void main(String[] args){
        try{
            Socket soc = new Socket("192.168.0.101", 3000);
            new Client(soc).run();
        }
        catch(Exception e){
            
        }
    }
}
