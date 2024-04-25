package br.com.lasbr.erp.repository;

import java.io.Serial;
import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.lasbr.erp.exception.UserAlreadyExistsException;
import br.com.lasbr.erp.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@Named
public class Users implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Inject
	private  transient EntityManager manager;

	public User findUserByEmail(String email) {
		try {
			return manager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
					.setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void save(User user) {
		if (findUserByEmail(user.getEmail()) == null) {
			manager.persist(user);
		} else {
			throw new UserAlreadyExistsException("User with email " + user.getEmail() + "alredy exists");
		}
	}
}
