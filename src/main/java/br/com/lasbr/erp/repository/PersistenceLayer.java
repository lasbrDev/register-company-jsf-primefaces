package br.com.lasbr.erp.repository;

import java.util.Date;
import java.util.List;

import br.com.lasbr.erp.model.Company;
import br.com.lasbr.erp.model.CompanyType;
import br.com.lasbr.erp.model.FieldActivity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceLayer {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LasBrPU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		FieldActivities fieldActivities = new FieldActivities(em);
		Companies companies = new Companies(em);
		
		List<FieldActivity> listFieldActivities = fieldActivities.search("");
		List<Company> listCompanies = companies.search("");
		System.out.println(listCompanies);
		
		if (!listFieldActivities.isEmpty()) {
			Company company = new Company();
			company.setTradeName("Robert Chase");
			company.setCnpj("41.952.519/0001-57");
			company.setCorporateName("Robert Chase 41952519000157");
			company.setCompanyType(CompanyType.MEI);
			company.setFundationDate(new Date());
			company.setFieldActivity(listFieldActivities.get(0));
			
			companies.save(company);
		} else {
			System.out.println("A lista de atividades de campo está vazia. Não é possível criar a empresa.");
		}
		try {
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		
		List<Company> listCompanies2 = companies.search("");
		System.out.println(listCompanies2);
		
		em.close();
		emf.close();
	}
}
