package br.com.lasbr.erp.repository;

import java.io.Serializable;
import java.util.List;

import br.com.lasbr.erp.model.FieldActivity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class FieldActivities implements Serializable {

	private static final long serialVersionUID = 1L;

	private final transient EntityManager manager;

	public FieldActivities(EntityManager manager) {
		this.manager = manager;
	}

	public List<FieldActivity> search(String description) {
		CriteriaBuilder criBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<FieldActivity> criQuery = criBuilder.createQuery(FieldActivity.class);
		Root<FieldActivity> root = criQuery.from(FieldActivity.class);
		criQuery.select(root);
		criQuery.where(criBuilder.like(criBuilder.lower(root.get("description")), description.toLowerCase() + "%"));
		TypedQuery<FieldActivity> query = manager.createQuery(criQuery);
		return query.getResultList();
	}
}
