package br.com.lasbr.erp.controller;

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

	private static final long serialVersionUID = 1L;

	private final UserService userService;

	private final UserFacesMessage message;

	private final AuthenticationService authenticationService;

	private User user;

	@Inject
	public UserBean(UserService userService, UserFacesMessage message, AuthenticationService authenticationService) {
		this.userService = userService;
		this.message = message;
		this.authenticationService = authenticationService;

	}

	public String login() {
		System.out.println("Método login foi chamado");
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
		} else {
			try {
				userService.registerUser(user);
				message.info("Usuário cadastrado com sucesso!");
			} catch (Exception e) {
				message.error("Erro ao cadastrar usuário. Por favor, tente novamente.");
			}
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}