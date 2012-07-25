package com.sopovs.moradanen.jquery.guice;

import java.lang.reflect.Type;

import com.google.common.annotations.VisibleForTesting;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sopovs.moradanen.jquery.domain.Company;
import com.sopovs.moradanen.jquery.domain.Person;
import com.sopovs.moradanen.jquery.domain.Sector;

public class MyServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		ServletModule module = new ServletModule() {
			@Override
			protected void configureServlets() {
				serve("/data/company*").with(CompanyServlet.class);
				serve("/data/sector*").with(SectorServlet.class);
				serve("/data/person*").with(PersonServlet.class);

				bind(Gson.class).toInstance(createGson());
			}
		};

		return Guice.createInjector(module);
	}

	@VisibleForTesting
	public static Gson createGson() {
		return new GsonBuilder()
				.registerTypeAdapter(Company.class, new CompanyTypeAdapter())
				.registerTypeAdapter(Sector.class, new SectorTypeAdapter())
				.registerTypeAdapter(Person.class, new PersonTypeAdapter())
				.setPrettyPrinting()
				.create();
	}

	private static class CompanyTypeAdapter implements JsonSerializer<Company> {
		@Override
		public JsonElement serialize(Company src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject result = new JsonObject();
			result.addProperty("id", src.getId());
			result.addProperty("name", src.getName());
			return result;
		}
	}

	private static class SectorTypeAdapter implements JsonSerializer<Sector> {
		@Override
		public JsonElement serialize(Sector src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject result = new JsonObject();
			result.addProperty("id", src.getId());
			result.addProperty("name", src.getName());
			result.addProperty("parentId", src.getParentId());
			if (src.getSubSectors() != null && !src.getSubSectors().isEmpty()) {
				JsonArray subSectors = new JsonArray();
				for (Sector subSector : src.getSubSectors()) {
					subSectors.add(serialize(subSector, typeOfSrc, context));
				}
				result.add("subSectors", subSectors);
			}
			return result;
		}
	}

	private static class PersonTypeAdapter implements JsonSerializer<Person> {
		@Override
		public JsonElement serialize(Person src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject result = new JsonObject();
			result.addProperty("id", src.getId());
			result.addProperty("firstName", src.getFirstName());
			result.addProperty("secondName", src.getSecondName());
			result.addProperty("description", src.getDescription());
			return result;
		}
	}
}