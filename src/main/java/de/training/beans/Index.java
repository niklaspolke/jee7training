package de.training.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Index {

	public Index() {
	}
	
	public String getHello() {
		return "Hello - this is Index-Bean speaking... :-)";
	}
}
