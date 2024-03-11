package br.com.lasbr.erp.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ManagementCompaniesBean  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static Integer NUMBER = 0;
	
	public ManagementCompaniesBean() {
		NUMBER++;
	}
	
	public Integer getNumber() {
		return NUMBER;
	}
}
