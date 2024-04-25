package br.com.lasbr.erp.controller;

import java.io.Serial;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.lasbr.erp.model.User;
import br.com.lasbr.erp.service.AuthenticationService;
import br.com.lasbr.erp.service.UserService;
import br.com.lasbr.erp.util.UserFacesMessage;

@Named
@SessionScoped
public class UserBean implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private User user;

	@Inject
	private UserService userService;

	@Inject
	private UserFacesMessage message;

	@Inject
	private AuthenticationService authenticationService;

	private boolean registrationSuccessful;

	public String login() {
		if (user == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de autenticação", "Credenciais inválidas!"));
			return null;
		}

		User authenticatedUser = authenticationService.authenticate(user.getEmail(), user.getPassword());
		if (authenticatedUser != null) {
			return "/ManagementCompanies.xhtml?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de autenticação", "Credenciais inválidas!"));
			return null;
		}
	}

	public void save() {
		User existingUser = userService.findUserByEmail(user.getEmail());
		if (existingUser != null) {
			message.error("E-mail já cadastrado!");
			registrationSuccessful = false;
		} else {
			try {
				userService.registerUser(user);
				message.info("Usuário cadastrado com sucesso!");
				user = new User();
				registrationSuccessful = true;
			} catch (Exception e) {
				message.error("Erro ao cadastrar usuário. Por favor, tente novamente.");
				registrationSuccessful = false;
			}
		}
	}

	public User getUser() {
		if (user == null) {
			user = new User();
		}
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isRegistrationSuccessful() {
		return registrationSuccessful;
	}
}
