package br.com.lasbr.erp.id;

public class Main {

	public static void main(String[] args) {

		Orders orders = new Orders();
		
		ReportService reportService = new ReportService(orders);
	}
}
