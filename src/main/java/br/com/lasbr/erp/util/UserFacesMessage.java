package br.com.lasbr.erp.util;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@ApplicationScoped
public class UserFacesMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private void add(String message, FacesMessage.Severity severity) {
		FacesMessage facesMessage = new FacesMessage(message);
		facesMessage.setSeverity(severity);
		
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public void info(String message) {
		add(message, FacesMessage.SEVERITY_INFO);
	}
}
