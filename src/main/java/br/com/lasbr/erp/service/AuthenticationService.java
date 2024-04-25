package br.com.lasbr.erp.service;

import java.io.Serial;
import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.lasbr.erp.model.User;
import br.com.lasbr.erp.repository.Users;

@Named
public class AuthenticationService implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Inject
	private Users users;

	public AuthenticationService() {
		this.users = null;
	}

    public User authenticate(String email, String password) {
        assert users != null;
        User user = users.findUserByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
}
