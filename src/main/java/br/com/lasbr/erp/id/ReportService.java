package br.com.lasbr.erp.id;

import java.math.BigDecimal;

import javax.inject.Inject;

public class ReportService {


	private final Orders orders;

    @Inject
    public ReportService(Orders orders) {
        this.orders = orders;
    }

	public BigDecimal totalOrdersCurrentMonth() {
		return orders.totalOrdersCurrentMonth();
	}
}
