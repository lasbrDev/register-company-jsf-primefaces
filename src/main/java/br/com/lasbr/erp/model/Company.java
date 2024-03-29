package br.com.lasbr.erp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
@Table(name = "company")
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@Column(name = "trade_name", nullable = false, length = 100)
	private String tradeName;
	
	@NotEmpty
	@Column(name = "corporate_name", nullable = false, length = 120)
	private String corporateName;
	
	@Column(nullable = false, length = 18)
	@NotNull
	@CNPJ
	private String cnpj;
	
	@NotNull
	@Past
	@Temporal(TemporalType.DATE)
	@Column(name = "fundation_date")
	private Date fundationDate;
	
	@NotNull
	@DecimalMin(value = "0.00", inclusive = true)
	@DecimalMax(value = "1000000.00", inclusive = true)
	@Column(name = "invoicing", precision = 10, scale = 2)
	private BigDecimal invoicing;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "field_activity_id", nullable = false)
	private FieldActivity fieldActivity;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name= "company_type", nullable = false, length = 50)
	private CompanyType companyType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getFundationDate() {
		return fundationDate;
	}

	public void setFundationDate(Date fundationDate) {
		this.fundationDate = fundationDate;
	}

	public BigDecimal getInvoicing() {
		return invoicing;
	}

	public void setInvoicing(BigDecimal invoicing) {
		this.invoicing = invoicing;
	}

	public FieldActivity getFieldActivity() {
		return fieldActivity;
	}

	public void setFieldActivity(FieldActivity fieldActivity) {
		this.fieldActivity = fieldActivity;
	}
	
	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return "Company [id=" + id + "]";
	}
}
