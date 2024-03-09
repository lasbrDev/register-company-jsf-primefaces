package br.com.lasbr.erp.util;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class TransactionalInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final transient EntityManager manager;
	
	public TransactionalInterceptor(EntityManager manager) {
		this.manager = manager;
	}
	
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction trx = manager.getTransaction();
		boolean creator = false;
		
		try {
			if (!trx.isActive()) {
				trx.begin();
				trx.rollback();
				trx.begin();
				creator = true;
			}
			return context.proceed();
		} catch (Exception e) {
			if (creator) {
				trx.rollback();
			}
			throw e;
		} finally {
			if (trx.isActive() && creator) {
				trx.commit();
			}
		}
	}
}
