package de.training.beans;

import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.training.entities.User;

@ManagedBean
@SessionScoped
public class LoginBean {

	private String user;

    private String username;

    private String password;

    public String getUser() {
    	return user;
    }

    public void setUser(String user) {
    	this.user = user;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
    	Logger logger = Logger.getLogger(LoginBean.class.getName());
    	logger.info("trying to login as: " + username);
    	String redirect = null;

    	FacesContext fContext = FacesContext.getCurrentInstance();
		if (username != null && username.length() > 0 && password != null && password.length() > 0) {
			// Open a database connection:
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("training-jpa");
			EntityManager em = emf.createEntityManager();

			TypedQuery<User> loginValidationQuery = em.createNamedQuery("User.validateLogin", User.class);
			loginValidationQuery.setParameter("login", username);
			loginValidationQuery.setParameter("password", password);
			List<User> loginResult = loginValidationQuery.getResultList();

			/* TODO: should be size() == 1 but currently no guarantee of no duplicates */
			if (loginResult != null && loginResult.size() > 0) {
				// successfully logged in and redirect to start page
				setUser(username);
				logger.info("login succeeded");
				redirect = "index.xhtml?faces-redirect=true";
			} else {
				FacesMessage msgGlobal = new FacesMessage("Login failed");
				FacesMessage msgUsername = new FacesMessage("Login possibly unknown");
				FacesMessage msgPassword = new FacesMessage("Password possibly wrong");
				fContext.addMessage(null, msgGlobal);
				fContext.addMessage("username", msgUsername);
				fContext.addMessage("password", msgPassword);
			}
		} else {
			if (username == null || username.length() == 0) {
				FacesMessage msgUsername = new FacesMessage("Username empty");
				fContext.addMessage("username", msgUsername);
			}
			if (password == null || password.length() == 0) {
				FacesMessage msgPassword = new FacesMessage("Password empty");
				fContext.addMessage("password", msgPassword);
			}
		}
		if (redirect == null) {
			logger.info("login failed");
		}
		return redirect;
	}

	public boolean isLoggedIn() {
		return getUser() != null;
	}
}
