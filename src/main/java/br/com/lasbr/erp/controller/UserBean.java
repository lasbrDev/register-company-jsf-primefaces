package br.com.lasbr.erp.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.lasbr.erp.model.User;
import br.com.lasbr.erp.service.UserService;

@Named
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private final UserService service;
	
	private User user;
	
	@Inject
	public UserBean(UserService service) {
		this.service = service;
	}
	
	public void prepareNewUser() {
		user = new User();
	}
	
	public void save() {
		service.registerUser(user);
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
