package database;

public class User {
	String login;
	String senha;
	
	
	public User(String l, String s) {
		login = l;
		senha = s;
	}
	
	public boolean isEqual(String l, String s){
		return (login.matches(l) && senha.matches(s));
	}

}
