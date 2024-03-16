package br.com.lasbr.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.lasbr.erp.model.Company;
import br.com.lasbr.erp.repository.Companies;
import br.com.lasbr.erp.util.UserFacesMessage;

@Named
@ViewScoped
public class ManagementCompaniesBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private final Companies companies;
	
	private final UserFacesMessage message;
	
	private List<Company> companiesList;
	
	private String wordSearch;
	
	@Inject
	public ManagementCompaniesBean(Companies companies, UserFacesMessage message) {
		this.companies = companies;
		this.message = message;
	}
	
	public void allCompanies() {
		companiesList = companies.all();
	}
	
	public List<Company> getCompaniesList() {
		return companiesList;
	}
	
	public void search() {
		companiesList = companies.search(wordSearch);
		
		if (companiesList.isEmpty()) {
			message.info("Sua consulta não retornou um registro válido!");
		}
	}

	public String getWordSearch() {
		return wordSearch;
	}

	public void setWordSearch(String wordSearch) {
		this.wordSearch = wordSearch;
	}
}
