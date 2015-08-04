package de.training.beans;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LoginBean {

    private String username;
    
    private String password;

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

	public void login() {
    	Logger logger = Logger.getLogger(LoginBean.class.getName());
    	logger.info("trying to login as: " + username);
    	
    	if (username != null && username.length() > 0 && password != null && password.length() > 0) {
    		//TODO:do the username/password check
    		FacesContext fContext = FacesContext.getCurrentInstance();
    		if (username.equals(password)) {
    			FacesMessage m = new FacesMessage("Successfull login as " + username);
    			fContext.addMessage(null, m);
    		} else {
    			FacesMessage msgGlobal = new FacesMessage("Login failed");
    			FacesMessage msgUsername = new FacesMessage("Login possibly unknown");
    			FacesMessage msgPassword = new FacesMessage("Password possibly wrong");
    			fContext.addMessage(null, msgGlobal);
    			fContext.addMessage("username", msgUsername);
    			fContext.addMessage("password", msgPassword);
    		}
    	}
    }

}
