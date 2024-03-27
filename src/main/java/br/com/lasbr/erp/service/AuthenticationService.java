package br.com.lasbr.erp.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.lasbr.erp.model.User;
import br.com.lasbr.erp.repository.Users;
import br.com.lasbr.erp.util.Transactional;

@Named
public class AuthenticationService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Users users;
	
	@Inject
	public AuthenticationService(Users users) {
		this.users = users;
	}
	
	@Transactional
	public User authenticate(String email, String passord) {
		User user = users.findByEmail(email);
		if (user != null && user.getPassword().equals(passord)) {
			return user;
		}
		return null;
	}

}
