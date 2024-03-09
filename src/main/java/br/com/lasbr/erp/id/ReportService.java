package br.com.lasbr.erp.id;

import java.math.BigDecimal;

import javax.inject.Inject;

public class ReportService {


	@Inject
	private Orders orders;

	public BigDecimal totalOrdersCurrentMonth() {
		return orders.totalOrdersCurrentMonth();
	}
	
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
}
