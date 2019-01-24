package edu.gatech.chai.fhir.decedent_index.dao;

import java.sql.Connection;
import java.util.List;

import edu.gatech.chai.fhir.decedent_index.model.FhirSource;

public interface FhirSourceDao {
	public Connection connect();
	
	public int save(Integer decedentId, FhirSource fhirSource);
	public void update(Integer id, Integer decedentId, FhirSource fhirSource);
	public void delete(Integer id);
	public void deleteByDecedentId(Integer decedentId);
	public List<FhirSource> searchByDecedentId(Integer decedentId);
	public List<FhirSource> searchByDecedentIdAndType(Integer decedentId, String type);
}
