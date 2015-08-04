package de.training.beans;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
    		//TODO:do the username/password check
    		if (username.equals(password)) {
    			// successfully logged in and redirect to start page
    			setUser(username);
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
    	return redirect;
    }
}
