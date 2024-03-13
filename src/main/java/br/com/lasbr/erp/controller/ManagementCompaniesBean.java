package br.com.lasbr.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.lasbr.erp.model.Company;
import br.com.lasbr.erp.repository.Companies;

@Named
@ViewScoped
public class ManagementCompaniesBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private final Companies companies;
	
	private List<Company> companiesList;
	
	@Inject
	public ManagementCompaniesBean(Companies companies) {
		this.companies = companies;
	}
	
	public void allCompanies() {
		companiesList = companies.all();
	}
	
	public List<Company> getCompaniesList() {
		return companiesList;
	}
}
