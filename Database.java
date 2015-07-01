package database;

import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable{
	
	private ArrayList <User> usuarios;
	
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
	public Database() {
		usuarios = new ArrayList <User>();
	}
}
