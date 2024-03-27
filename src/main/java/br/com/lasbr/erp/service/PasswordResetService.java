package br.com.lasbr.erp.service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.lasbr.erp.model.User;
import br.com.lasbr.erp.repository.Users;
import br.com.lasbr.erp.util.Transactional;

@Named
public class PasswordResetService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Users users;
	
	@Inject
	public PasswordResetService(Users users) {
		this.users = users;
	}
	
	public String generateRecoveryCode(String email) {
		User user = users.findByEmail(email);
		if (user != null) {
			String recoveryCode = UUID.randomUUID().toString();
			user.setRecoveryCode(recoveryCode);
			
			LocalDateTime expirationDateTime = LocalDateTime.now().plusHours(24);
			user.setRecoveryCodeExpiration(Date.from(expirationDateTime.atZone(ZoneId.systemDefault()).toInstant()));
			
			users.save(user);
			return recoveryCode;
		}
		return null;
	}
	
	public boolean validateRecoveryCode(String email, String recoverycode) {
		User user = users.findByEmail(email);
		return user != null && recoverycode.equals(user.getRecoveryCode());
	}
	
	@Transactional
	public void resetPassword(String email, String newPassword) {
		User user = users.findByEmail(email);
		if(user != null) {
			user.setPassword(newPassword);
			users.save(user);
		}
	}
}
