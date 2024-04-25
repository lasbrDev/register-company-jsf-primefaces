package br.com.lasbr.erp.service;

import java.io.Serial;
import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.lasbr.erp.model.Company;
import br.com.lasbr.erp.repository.Companies;
import br.com.lasbr.erp.util.Transactional;

@Named
public class RegistrationCompanyService implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private final Companies companies;

	public RegistrationCompanyService() {
		this.companies = null;
	}
	
	@Inject
	public RegistrationCompanyService(Companies companies) {
		this.companies = companies;
	}
	
	@Transactional
	public void save(Company company) {
        assert companies != null;
        companies.save(company);
	}

	@Transactional
	public void delete(Company company) {
        assert companies != null;
        companies.remove(company);
	}
}
