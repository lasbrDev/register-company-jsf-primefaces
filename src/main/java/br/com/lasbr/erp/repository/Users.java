package br.com.lasbr.erp.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.lasbr.erp.exception.UserAlreadyExistsException;
import br.com.lasbr.erp.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@Named
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	private final transient EntityManager manager;

	@Inject
	public Users(EntityManager manager) {
		this.manager = manager;
	}

	public User findByEmail(String email) {
		try {
			return manager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
					.setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public User save(User user) {
		if (findByEmail(user.getEmail()) == null) {
			manager.persist(user);
			return user;
		} else {
			throw new UserAlreadyExistsException("User with email " + user.getEmail() + "alredy exists");
		}
	}
}
