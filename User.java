package database;

import java.io.Serializable;

public class User implements Serializable {
	public String login;
	public String senha;
	
	
	public User(String l, String s) {
		login = l;
		senha = s;
	}
	
	public boolean isEqual(String l, String s){
		return (login.matches(l) && senha.matches(s));
	}

}
