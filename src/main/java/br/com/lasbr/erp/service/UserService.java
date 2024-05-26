package br.com.lasbr.erp.service;

import java.io.Serial;
import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.lasbr.erp.exception.UserAlreadyExistsException;
import br.com.lasbr.erp.model.User;
import br.com.lasbr.erp.repository.Users;
import br.com.lasbr.erp.util.Transactional;

@Named
public class UserService implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Inject
	private Users users;
	
	@Transactional
	public void registerUser(User user) {
        if (users.findUserByEmail(user.getEmail()) != null) {
			throw new UserAlreadyExistsException("E-mail já cadastrado!");
		}
		users.save(user);
	}

	public User findUserByEmail(String email) {
        return users.findUserByEmail(email);
	}
}

