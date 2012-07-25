package com.sopovs.moradanen.jquery.domain;


import java.util.ArrayList;
import java.util.List;

public class Sector {
	private String id;
	private String parentId;
	private String name;
	// TODO make this ManyToMany JPA relationship
	private List<Company> focusedCompanies;

	private final List<Sector> subSectors = new ArrayList<Sector>();

	public Sector() {
	}

	public Sector(String id, String parentId, String name) {
		this.id = id;
		this.parentId = parentId;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Company> getFocusedCompanies() {
		return focusedCompanies;
	}

	public void setFocusedCompanies(List<Company> focusedCompanies) {
		this.focusedCompanies = focusedCompanies;
	}

	public List<Sector> getSubSectors() {
		return subSectors;
	}

	public boolean isRoot() {
		return parentId == null;
	}

	public boolean isLeaf() {
		return subSectors.isEmpty();
	}

	public String getVersion() {
		// TODO
		return "0";
	}

}
