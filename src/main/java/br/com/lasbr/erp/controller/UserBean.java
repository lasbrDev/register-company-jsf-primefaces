package br.com.lasbr.erp.controller;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.lasbr.erp.exception.UserAlreadyExistsException;
import br.com.lasbr.erp.model.User;
import br.com.lasbr.erp.service.UserService;
import br.com.lasbr.erp.util.UserFacesMessage;
import jakarta.validation.constraints.NotNull;
import org.primefaces.PrimeFaces;

@Named
@SessionScoped
public class UserBean implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@NotNull
	private User user = new User();

	@Inject
	private UserService userService;

	@Inject
	private UserFacesMessage message;

	@PostConstruct
	public void init() {
		user = new User();
	}

	public String login() throws IOException {
		User loggedUser = userService.findUserByEmail(user.getEmail());
		if (loggedUser != null && loggedUser.getPassword().equals(user.getPassword())) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("loggedUser", loggedUser);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/ManagementCompanies.xhtml");
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Erro de autenticação", "Credenciais inválidas!"));
		}
		return null;
	}

	public void registerUser() {
		try {
			userService.registerUser(user);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Usuário cadastrado com sucesso!", null));
			PrimeFaces.current().executeScript("PF('registerUserDialog').hide()");
			user = new User();
		} catch (UserAlreadyExistsException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "E-mail já cadastrado!", null));
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate convertToLocalDate(Date date) {
		return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
	}

	public String convertTodDateString(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public void setRegistrationSuccessful(boolean registrationSuccessful) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("registrationSuccessful", registrationSuccessful);
	}

	public boolean isRegistrationSuccessful() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash().get("registrationSuccessful") != null;
	}

	public void setLoginSuccessful(boolean loginSuccessful) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("loggedUser", loginSuccessful);
	}

	public boolean isLoginSuccessful() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash().get("loggedUser") != null;
	}

	public void clearMessages() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(false);
	}
}
