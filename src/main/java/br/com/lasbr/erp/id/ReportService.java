package br.com.lasbr.erp.id;

import java.math.BigDecimal;

public class ReportService {

private final Orders orders;
	
	public ReportService(Orders orders) {
		this.orders = orders;
	}

	public BigDecimal totalOrdersCurrentMonth() {
		return orders.totalOrdersCurrentMonth();		
	}
}
