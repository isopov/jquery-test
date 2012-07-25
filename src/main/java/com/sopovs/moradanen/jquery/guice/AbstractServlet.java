package com.sopovs.moradanen.jquery.guice;

import java.util.Map;

import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.servlet.RequestParameters;
import com.sopovs.moradanen.jquery.dao.DummyDao;

public class AbstractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Gson gson;

	private DummyDao dao;

	private Provider<Map<String, String[]>> parameterProvider;

	@Inject
	public void setGson(Gson gson) {
		this.gson = gson;
	}

	@Inject
	public void setDao(DummyDao dao) {
		this.dao = dao;
	}

	@Inject
	public void setParameterProvider(@RequestParameters Provider<Map<String, String[]>> parameterProvider) {
		this.parameterProvider = parameterProvider;
	}

	protected String getParameter(String parName) {
		String[] ids = parameterProvider.get().get(parName);
		if (ids != null && ids.length > 0) {
			return ids[0];
		} else {
			return null;
		}
	}

	protected Gson getGson() {
		return gson;
	}

	protected DummyDao getDao() {
		return dao;
	}

}
