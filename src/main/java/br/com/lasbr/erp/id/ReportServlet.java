package br.com.lasbr.erp.id;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/relatorio")
public class ReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    private final ReportService service;

    @Inject
    public ReportServlet(ReportService service) {
        this.service = service;
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(service.totalOrdersCurrentMonth());
	}
}
