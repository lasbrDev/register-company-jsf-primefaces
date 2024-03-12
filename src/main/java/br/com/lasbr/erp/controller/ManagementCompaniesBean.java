package br.com.lasbr.erp.controller;

import java.io.Serializable;
import java.util.Arrays;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.lasbr.erp.model.Company;
import br.com.lasbr.erp.model.CompanyType;

@Named
@ViewScoped
public class ManagementCompaniesBean  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Company company = new Company();
	
	public void save() {
		System.out.println("Corporate Name: " + company.getCorporateName()
				+ " - Trade Name: " + company.getTradeName()
				+ " - Type: " + company.getCompanyType());
	}
	
	public String help() {
		return "HelpManagementCompanies?faces-redirect=true";
	}
	
	public Company getCompany() {
		return company;
	}
	
	public SelectItem[] getCompanyTypes() {
		return Arrays.stream(CompanyType.values())
				.map(type -> new SelectItem(type, type.getDescription()))
				.toArray(SelectItem[]::new);
	}
}
