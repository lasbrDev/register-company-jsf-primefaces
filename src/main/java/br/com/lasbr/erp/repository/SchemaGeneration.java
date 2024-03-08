package br.com.lasbr.erp.repository;

import java.util.List;

import br.com.lasbr.erp.model.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SchemaGeneration {

	public static void main(String[] args) {
		
		try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("LasBrPU");
				EntityManager em = emf.createEntityManager()) {

			List<Company> list = em.createQuery("FROM Company", Company.class).getResultList();
			System.out.println(list);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
