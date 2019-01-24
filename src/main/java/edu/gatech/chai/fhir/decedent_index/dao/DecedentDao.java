package edu.gatech.chai.fhir.decedent_index.dao;

import java.sql.Connection;

import edu.gatech.chai.fhir.decedent_index.model.Decedent;
import edu.gatech.chai.fhir.decedent_index.model.Decedents;

public interface DecedentDao {
	public Connection connect();
	
	public int save(Decedent decedent);
	public void update(Integer decedentId, Decedent decedent);
	public void delete(Integer id);
	public Decedents get();
	public Decedent getById(Integer id);
	public Decedents search(String caseNumber, String meOffice, String lastName, String firstName, String name);
}
