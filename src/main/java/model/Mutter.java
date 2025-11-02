package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Mutter implements Serializable {
	
	private int id;
	private String userName;
	private String text;
	private Timestamp createAt;
	
	public Mutter() {
		
	}
	
	public Mutter(int id, String userName, String text, Timestamp createAt) {
		
		this.id = id;
		this.userName =userName;
		this.text = text;
		this.createAt = createAt;
		
	}
	
	public Mutter(String userName, String text) {
	    this.userName = userName;
	    this.text = text;
	}
	
	public int getId() {
		
		return id;
		
	}
	
	public String getUserName() {
		
		return userName;
			
	}
	
	public String getText() {
		
		return text;
	}
	
	public Timestamp getCreatAt() {
		
		return createAt;
	}
	
	
}
