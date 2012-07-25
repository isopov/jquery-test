package com.sopovs.moradanen.jquery.guice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;

@Singleton
public class CompanyServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sectorId = getParameter("sectorId");
		if (sectorId != null) {
			resp.getWriter().print(getGson().toJson(getDao().findCompaniesBySector(sectorId)));
		}
	}

}