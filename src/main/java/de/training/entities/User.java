package de.training.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

@Entity
@NamedQuery(name="User.validateLogin",
	query="SELECT u FROM User u WHERE u.login = :login AND u.password = :password")
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

	private String login;

	private String password;

	public User() {
	}

	public User(final String login, final String password) {
		this.login = login;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "User: (" + login + ", " + password + ")";
	}
}
