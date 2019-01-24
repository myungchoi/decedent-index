package edu.gatech.chai.fhir.decedent_index.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import edu.gatech.chai.fhir.decedent_index.model.Decedent;
import edu.gatech.chai.fhir.decedent_index.model.FhirSource;

@Component
public class FhirSourceDaoImpl implements FhirSourceDao {
	final static Logger logger = LoggerFactory.getLogger(FhirSourceDaoImpl.class);

	@Override
	public Connection connect() {
		String url = "jdbc:sqlite::resource:DIDB.db";
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
			logger.info("Connected to database");
		} catch (SQLException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}

		return conn;
	}

	@Override
	public int save(Integer decedentId, FhirSource fhirSource) {
		String sql = "INSERT INTO FHIR (decedent_id, fhir_server_url, fhir_version, fhir_patient_id, type) values (?,?,?,?,?)";

		// All columns must not null;
		String fhirServerUrl = fhirSource.getFhirServerUrl();
		String fhirVersion = fhirSource.getFhirVersion();
		String fhirPatientId = fhirSource.getFhirPatientId();
		String type = fhirSource.getType();
		
		if (fhirServerUrl == null || fhirVersion == null || fhirPatientId == null || type == null ||
				fhirServerUrl.isEmpty() || fhirVersion.isEmpty() || fhirPatientId.isEmpty() || type.isEmpty()) {
			return 0;
		}
		
		int insertedId = 0;
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, decedentId);
			pstmt.setString(2, fhirServerUrl);
			pstmt.setString(3, fhirVersion);
			pstmt.setString(4, fhirPatientId);
			pstmt.setString(5, type);

			if (pstmt.executeUpdate() > 0) {
				// Retrieves any auto-generated keys created as a result of executing this
				// Statement object
				java.sql.ResultSet generatedKeys = pstmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					insertedId = generatedKeys.getInt(1);
				}
			}

			logger.info("New decedent data (id=" + insertedId + ") added");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return insertedId;
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM FHIR WHERE id = ?";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			logger.info("fhirSource (" + id + ") deleted");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	protected FhirSource setFhir(ResultSet rs) throws SQLException {
		FhirSource fhirSource = new FhirSource();
		fhirSource.setFhirServerUrl(rs.getString("fhir_server_url"));
		fhirSource.setFhirVersion(rs.getString("fhir_version"));
		fhirSource.setFhirPatientId(rs.getString("fhir_patient_id"));
		fhirSource.setType(rs.getString("type"));
		
		return fhirSource;
	}
	
	protected List<FhirSource> setFhirs(ResultSet rs) throws SQLException {
		List<FhirSource> fhirs = new ArrayList<FhirSource>();
		
		while (rs.next()) {
			FhirSource fhir = setFhir(rs);
			fhirs.add(fhir);
		}
		
		return fhirs;
	}

	public FhirSource getById(Integer id) {
		FhirSource fhirSource = null;
		String sql = "SELECT * FROM FHIR WHERE id = ?";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				fhirSource = setFhir(rs);
			}
			logger.info("filter data (" + id + ") selected");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return fhirSource;
	}

	@Override
	public List<FhirSource> searchByDecedentId(Integer decedentId) {
		List<FhirSource> fhirSources = null;
		String sql = "SELECT * FROM FHIR WHERE decedent_id = ?";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, decedentId);
			ResultSet rs = pstmt.executeQuery();
			fhirSources = setFhirs(rs);
			logger.info(fhirSources.size()+" fhirSources selected");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return fhirSources;
	}

	@Override
	public List<FhirSource> searchByDecedentIdAndType(Integer decedentId, String type) {
		List<FhirSource> fhirSources = null;

		String sql = "SELECT * FROM FHIR WHERE decedentId=? AND type=?";
		
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, decedentId);
			pstmt.setString(2, type);
			ResultSet rs = pstmt.executeQuery();
			fhirSources = setFhirs(rs);
			logger.info(fhirSources.size() + " filter data obtained");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return fhirSources;
	}

	@Override
	public void update(Integer id, Integer decedentId, FhirSource fhirSource) {
		String sql = "UPDATE FHIR SET decedent_id=?, fhir_server_url=?, fhir_version=?, fhir_patient_id=?, type=? WHERE id=?";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, decedentId);
			pstmt.setString(2, fhirSource.getFhirServerUrl());
			pstmt.setString(3, fhirSource.getFhirVersion());
			pstmt.setString(4, fhirSource.getFhirPatientId());
			pstmt.setString(5, fhirSource.getType());
			pstmt.setInt(6, id);
			pstmt.executeUpdate();
			logger.info("fhir source (" + id + ") updated with "+ fhirSource.toString());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void deleteByDecedentId(Integer decedentId) {
		String sql = "DELETE FROM FHIR WHERE decedent_id=?";
		
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, decedentId);
			pstmt.executeUpdate();
			logger.info("fhirSource with decendent ID (" + decedentId + ") deleted");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
