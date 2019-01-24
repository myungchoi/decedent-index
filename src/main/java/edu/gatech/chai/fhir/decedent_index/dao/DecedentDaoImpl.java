package edu.gatech.chai.fhir.decedent_index.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneId;

import edu.gatech.chai.fhir.decedent_index.model.Decedent;
import edu.gatech.chai.fhir.decedent_index.model.Decedents;
import edu.gatech.chai.fhir.decedent_index.model.FhirSource;

@Component
public class DecedentDaoImpl implements DecedentDao {
	final static Logger logger = LoggerFactory.getLogger(DecedentDaoImpl.class);

	@org.springframework.beans.factory.annotation.Autowired
	FhirSourceDaoImpl fhirSourceDao;

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
	public int save(Decedent decedent) {
		String sql = "INSERT INTO Decedent (last_name, first_name, gender, me_office, me_case_number) values (?,?,?,?,?)";

		int insertedId = 0;
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, decedent.getLastName());
			pstmt.setString(2, decedent.getFirstName());
			pstmt.setString(3, decedent.getGender());
			pstmt.setString(4, decedent.getMeOffice());
			pstmt.setString(5, decedent.getMeCaseNumber());

			if (pstmt.executeUpdate() > 0) {
				// Retrieves any auto-generated keys created as a result of executing this
				// Statement object
				java.sql.ResultSet generatedKeys = pstmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					insertedId = generatedKeys.getInt(1);
					// Create FHIR sources with this new decedent Id.
					List<FhirSource> fhirSources = decedent.getListOfFhirSources();
					if (fhirSources != null) {
						for (FhirSource fhirSource : fhirSources) {
							fhirSourceDao.save(insertedId, fhirSource);
						}
					}
				}
			}

			logger.info("New decent data (id=" + insertedId + ") added");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return insertedId;
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM Decedent where id = ?";

		fhirSourceDao.deleteByDecedentId(id);
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

			logger.info("decedent data (" + id + ") deleted");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	protected Decedent setDecedent(ResultSet rs) throws SQLException {
		Decedent decedent = new Decedent();
		decedent.setId(rs.getInt("id"));
		decedent.setLastName(rs.getString("last_name"));
		decedent.setFirstName(rs.getString("first_name"));
		decedent.setGender(rs.getString("gender"));
		decedent.setMeOffice(rs.getString("me_office"));
		decedent.setMeCaseNumber(rs.getString("me_case_number"));

		List<FhirSource> fhirSources = fhirSourceDao.searchByDecedentId(rs.getInt("id"));
		decedent.setListOfFhirSources(fhirSources);

		return decedent;
	}

	@Override
	public Decedent getById(Integer id) {
		Decedent decedent = null;
		String sql = "SELECT * FROM Decedent where id = ?";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				decedent = setDecedent(rs);
				List<FhirSource> fhirSources = fhirSourceDao.searchByDecedentId(id);
				decedent.setListOfFhirSources(fhirSources);
			}
			logger.info("decedent data (" + id + ") selected");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return decedent;
	}

	protected Decedents setDecedents(ResultSet rs) throws SQLException {
		Decedents decedents = new Decedents();
		int count = 0;
		while (rs.next()) {
			Decedent decedent = setDecedent(rs);
			decedents.getList().add(decedent);
			count++;
		}

		decedents.setCount(count);
		decedents.setCreated(OffsetDateTime.now(ZoneId.of("US/Eastern")));

		return decedents;
	}

	@Override
	public Decedents get() {
		Decedents decedents = null;

		String sql = "SELECT * FROM Decedent";

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			decedents = setDecedents(rs);
			logger.info(decedents.getCount() + " filter data obtained");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		if (decedents == null) {
			decedents = new Decedents();
			decedents.setCount(0);
			decedents.setCreated(OffsetDateTime.now(ZoneId.of("US/Eastern")));
		}
		return decedents;
	}

	@Override
	public void update(Decedent decedent) {
		String sql = "UPDATE Decedent SET";
		int index = 1;

		int lastNameIndex = 0;
		if (decedent.getLastName() != null && !decedent.getLastName().isEmpty()) {
			sql = sql + " last_name=?";
			lastNameIndex = index;
			index++;
		}

		int firstNameIndex = 0;
		if (decedent.getFirstName() != null && !decedent.getFirstName().isEmpty()) {
			sql = sql + " first_name=?";
			firstNameIndex = index;
			index++;
		}

		int genderIndex = 0;
		if (decedent.getGender() != null && !decedent.getGender().isEmpty()) {
			sql = sql + " gender=?";
			genderIndex = index;
			index++;
		}

		int meOfficeIndex = 0;
		if (decedent.getMeCaseNumber() != null && !decedent.getMeCaseNumber().isEmpty()) {
			sql = sql + " me_office=?";
			meOfficeIndex = index;
			index++;
		}

		int meCaseNumberIndex = 0;
		if (decedent.getMeCaseNumber() != null && !decedent.getMeCaseNumber().isEmpty()) {
			sql = sql + " me_case_number=?";
			meCaseNumberIndex = index;
			index++;
		}

		sql = sql + " where id=?";
		int decedentIdIndex = index;
		index++;

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			if (lastNameIndex > 0) {
				pstmt.setString(lastNameIndex, decedent.getLastName());
			}

			if (firstNameIndex > 0) {
				pstmt.setString(firstNameIndex, decedent.getFirstName());
			}

			if (genderIndex > 0) {
				pstmt.setString(genderIndex, decedent.getGender());
			}

			if (meOfficeIndex > 0) {
				pstmt.setString(meOfficeIndex, decedent.getMeOffice());
			}

			if (meCaseNumberIndex > 0) {
				pstmt.setString(meCaseNumberIndex, decedent.getMeCaseNumber());
			}

			pstmt.setInt(decedentIdIndex, decedent.getId());
			pstmt.executeUpdate();

			// Now we need to update FHIR sources. We wipe out existing one and reload with
			// new one.
			fhirSourceDao.deleteByDecedentId(decedent.getId());

			List<FhirSource> fhirSources = decedent.getListOfFhirSources();
			if (fhirSources != null) {
				for (FhirSource fhirSource : fhirSources) {
					fhirSourceDao.save(decedent.getId(), fhirSource);
				}
			}

			logger.info("Decedent (" + decedent.getId() + ") updated with" + decedent.toString());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Decedents search(String meCaseNumber, String meOffice, String lastName, String firstName, String name) {
		Decedents decedents = null;
		String sql = "SELECT * FROM Decedent where"; // profile_name = ?";

		int index = 1;
		int meCaseNumberIndex = 0;
		if (meCaseNumber != null && !meCaseNumber.isEmpty()) {
			sql = sql + " UPPER(me_case_number)=?";
			meCaseNumberIndex = index;
			index++;
		}

		int meOfficeIndex = 0;
		if (meOffice != null && !meOffice.isEmpty()) {
			if (index > 1)
				sql = sql + " AND";
			sql = sql + " UPPER(me_office)=?";
			meOfficeIndex = index;
			index++;
		}

		int lastNameIndex = 0;
		if (lastName != null && !lastName.isEmpty()) {
			if (index > 1)
				sql = sql + " AND";
			sql = sql + " UPPER(last_name)=?";
			lastNameIndex = index;
			index++;
		}

		int firstNameIndex = 0;
		if (firstName != null && !firstName.isEmpty()) {
			if (index > 1)
				sql = sql + " AND";
			sql = sql + " UPPER(first_name)=?";
			firstNameIndex = index;
			index++;
		}

		int nameIndex = 0;
		if (name != null && !name.isEmpty()) {
			if (index > 1)
				sql = sql + " AND";
			sql = sql + " (UPPER(first_name) like ? or UPPER(last_name) like ?)";
			nameIndex = index;
			index = index + 2;
		}

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			if (meCaseNumberIndex > 0) {
				pstmt.setString(meCaseNumberIndex, meCaseNumber.toUpperCase());
			}

			if (meOfficeIndex > 0) {
				pstmt.setString(meOfficeIndex, meOffice.toUpperCase());
			}

			if (lastNameIndex > 0) {
				pstmt.setString(lastNameIndex, lastName.toUpperCase());
			}

			if (firstNameIndex > 0) {
				pstmt.setString(firstNameIndex, firstName.toUpperCase());
			}

			if (nameIndex > 0) {
				pstmt.setString(nameIndex, name.toUpperCase());
				pstmt.setString(nameIndex + 1, name.toUpperCase());
			}

			ResultSet rs = pstmt.executeQuery();
			decedents = setDecedents(rs);
			logger.info(decedents.getCount() + " decedents selected");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return decedents;
	}
}
