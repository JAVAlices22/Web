package database;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Database{
	
	public ArrayList <User> usuarios;
	
	public boolean addUser(String l, String s){
		User u = new User(l, s);
		if(!checkUser(l, s)){
			usuarios.add(u);
			return true;
		}
		return false;
	}
	
	public boolean deleteUser(String l, String s){
		User u = new User(l, s);
		if(!checkUser(l, s)){
			usuarios.remove(u);
			return true;
		}
		return false;
	}
	
	public boolean checkUser(String l, String s){
		for(int i = 0; i < usuarios.size(); i++){
			if(usuarios.get(i).isEqual(l, s))
				return true;
		}
		return false;
	}
	
    public void save(){
        try{
        	FileOutputStream arquivoGrav = new FileOutputStream("database.dat"); //gera arquivo para armazenar objeto
        	ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav); //classe responsável por inserir os objetos
            objGravar.writeObject(usuarios); //grava o objeto L no arquivo
            objGravar.flush();
            objGravar.close();
            arquivoGrav.flush();
            arquivoGrav.close();
        	System.out.println("Foi salvo com sucesso.");
   }catch(Exception ex){
	   ex.printStackTrace();
        }
    }
    
	public Database() {
		usuarios = new ArrayList <User>();
	}
}
