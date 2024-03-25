package br.com.lasbr.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.lasbr.erp.model.Company;
import br.com.lasbr.erp.model.CompanyType;
import br.com.lasbr.erp.model.FieldActivity;
import br.com.lasbr.erp.repository.Companies;
import br.com.lasbr.erp.repository.FieldActivities;
import br.com.lasbr.erp.service.RegistrationCompanyService;
import br.com.lasbr.erp.util.UserFacesMessage;

@Named
@ViewScoped
public class ManagementCompaniesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Companies companies;

	private final UserFacesMessage message;

	private final FieldActivities fieldActivities;

	private Company company;

	private final RegistrationCompanyService service;

	private List<Company> companiesList;

	@SuppressWarnings("rawtypes")
	private transient Converter fieldActivityConveter;

	private String wordSearch;

	@Inject
	public ManagementCompaniesBean(Companies companies, UserFacesMessage message, FieldActivities fieldActivities,
			RegistrationCompanyService service) {
		this.companies = companies;
		this.message = message;
		this.fieldActivities = fieldActivities;
		this.service = service;
	}

	public void prepareNewCompany() {
		company = new Company();
	}

	public void prepareEditing() {
		fieldActivityConveter = new FieldActivityConveter(Arrays.asList(company.getFieldActivity()));
	}

	public void save() {
		service.save(company);
		
		updateRecords();
		
		message.info("Empresa salva com sucesso!");
		PrimeFaces.current().ajax().update(Arrays.asList("frm:companiesDataTable", "frm:messages"));
	}
	
	public void delete() {
		service.delete(company);
		company = null;
		updateRecords();
		
		message.info("Empresa excluída com sucesso!");		
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

	public List<FieldActivity> completeFieldActivities(String description) {
		List<FieldActivity> listFieldActivities = fieldActivities.search(description);
		fieldActivityConveter = new FieldActivityConveter(listFieldActivities);
		return listFieldActivities;
	}
	
	private void updateRecords() {
		if (isSearchPerformed()) {
			search();
		} else {
			allCompanies();
		}
	}

	private boolean isSearchPerformed() {
		return wordSearch != null && !"".equals(wordSearch);
	}

	public String getWordSearch() {
		return wordSearch;
	}

	public void setWordSearch(String wordSearch) {
		this.wordSearch = wordSearch;
	}

	public CompanyType[] getCompanyTypes() {
		return CompanyType.values();
	}

	@SuppressWarnings("rawtypes")
	public Converter getFieldActivityConveter() {
		return fieldActivityConveter;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public boolean isCompanySelected() {
		return company != null && company.getId() != null;
	}
}