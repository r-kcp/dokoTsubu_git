package model;

import java.io.Serializable;

public class User implements Serializable {
	
	private String name;
	private String pass;
	
	public User() {
	}
	
	public void User(String name, String pass) {
		
		name = this.name;
		pass = this.pass;
		
	}

	public String getName() {
		return name;
	}
	
	public String getPass() {
		return pass;
	}
	
}
