package com.sopovs.moradanen.jquery.quice;

import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;
import com.sopovs.moradanen.jquery.dao.DummyDao;
import com.sopovs.moradanen.jquery.guice.MyServletConfig;

public class GsonTest {

	@Ignore
	@Test
	public void testCompanyJson() {
		Gson gson = MyServletConfig.createGson();
		String json = gson.toJson(new DummyDao().findAllCompanies());
		System.out.println(json);
	}

}
