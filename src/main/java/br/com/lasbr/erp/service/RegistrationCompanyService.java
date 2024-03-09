package br.com.lasbr.erp.service;

import java.io.Serializable;

import br.com.lasbr.erp.model.Company;
import br.com.lasbr.erp.repository.Companies;
import br.com.lasbr.erp.util.Transactional;

public class RegistrationCompanyService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Companies companies;
	
	public RegistrationCompanyService(Companies companies) {
		this.companies = companies;
	}
	
	@Transactional
	public void save(Company company) {
		companies.save(company);
	}

	@Transactional
	public void delete(Company company) {
		companies.remove(company);
	}
}
