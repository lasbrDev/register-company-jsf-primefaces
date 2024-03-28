package br.com.lasbr.erp.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.lasbr.erp.exception.UserAlreadyExistsException;
import br.com.lasbr.erp.model.User;
import br.com.lasbr.erp.service.UserService;
import br.com.lasbr.erp.util.UserFacesMessage;

@Named
@ViewScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private final UserService service;
	
	private final UserFacesMessage message;
	
	private User user;
	
	@Inject
	public UserBean(UserService service, UserFacesMessage message) {
		this.service = service;
		this.message = message;
	}
	
	public void prepareNewUser() {
		user = new User();
	}
	
	public void save() {
		try {
			service.registerUser(user);
			prepareNewUser();
			message.info("Usuário cadastrado com sucesso!");
		} catch (UserAlreadyExistsException e) {
			message.error("E-mail já cadastrado!");
		}
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
