package de.training.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="USER_SEQ",strategy=GenerationType.TABLE)
	@TableGenerator(name="USER_SEQ",
		table="sequences",
		pkColumnName="SEQ_NAME", // Specify the name of the column of the primary key
		valueColumnName="SEQ_NUMBER", // Specify the name of the column that stores the last value generated
		pkColumnValue="USER_ID", // Specify the primary key column value that would be considered as a primary key generator
		allocationSize=1)
	private long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	public User() {
	}
	
	public User(final String name, final String email, final String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String toString() {
		return "User: (" + name + ", " + email + ", " + password + ")";
	}
}
