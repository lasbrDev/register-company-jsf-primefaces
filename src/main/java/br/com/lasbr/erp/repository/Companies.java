package br.com.lasbr.erp.repository;

import java.io.Serializable;
import java.util.List;

import br.com.lasbr.erp.model.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class Companies implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final transient EntityManager manager;
	
	public Companies(EntityManager manager) {
		this.manager = manager;
	}
	
	public Company byId(Integer id) {
		return manager.find(Company.class, id);
	}
	
	public List<Company> search(String name) {
		String jpql = "from Company where tradeName like :tradeName";
		TypedQuery<Company> query = manager.createQuery(jpql, Company.class);
		query.setParameter("tradeName", name + "%");
		return query.getResultList();
	}
	
	public Company save(Company company) {
		return manager.merge(company);
	}
	
	public void remove(Company company) {
		company = byId(company.getId());
		manager.remove(company);
	}
}
