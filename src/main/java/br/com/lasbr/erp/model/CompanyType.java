package br.com.lasbr.erp.model;

public enum CompanyType {
	
	MEI("Microempreendedor Individual"),
	EIRELI("Empresa Individual de Responsabilidade Limitada"),
	LTDA("Sociedade Limitada"),
	SA("Sociedade Anônima");
	
	private final String description;
	
	CompanyType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
