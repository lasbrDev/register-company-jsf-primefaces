package br.com.lasbr.erp.repository;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.lasbr.erp.model.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class Companies implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private final transient EntityManager manager;

	public Companies() {
		this.manager = null;
	}
	
	@Inject
	public Companies(EntityManager manager) {
		this.manager = manager;
	}
	
	public Company byId(Integer id) {
		return manager.find(Company.class, id);
	}
	
	public List<Company> search(String name) {
		String jpql = "from Company where corporateName like :corporateName";
		TypedQuery<Company> query = manager.createQuery(jpql, Company.class);
		query.setParameter("corporateName", name + "%");
		return query.getResultList();
	}
	
	public List<Company> all() {
		return manager.createQuery("from Company", Company.class).getResultList();
	}
	
	public void save(Company company) {
		manager.merge(company);
	}
	
	public void remove(Company company) {
		company = byId(company.getId());
		manager.remove(company);
	}
}
