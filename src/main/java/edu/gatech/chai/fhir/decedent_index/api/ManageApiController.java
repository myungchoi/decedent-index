package edu.gatech.chai.fhir.decedent_index.api;

import edu.gatech.chai.fhir.decedent_index.dao.DecedentDaoImpl;
import edu.gatech.chai.fhir.decedent_index.dao.FhirSourceDaoImpl;
import edu.gatech.chai.fhir.decedent_index.model.Decedent;
import edu.gatech.chai.fhir.decedent_index.model.Decedents;
import edu.gatech.chai.fhir.decedent_index.model.FhirSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-01-23T11:31:00.732213-05:00[America/New_York]")
@Controller
public class ManageApiController implements ManageApi {

    private static final Logger log = LoggerFactory.getLogger(ManageApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    DecedentDaoImpl decedentDao;
    
    @org.springframework.beans.factory.annotation.Autowired
    public ManageApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addDecedent(@ApiParam(value = "Decedent info to add"  )  @Valid @RequestBody Decedent body) {
        String accept = request.getHeader("Accept");
        
        String meCaseNumber = body.getMeCaseNumber();
        String meOffice = body.getMeOffice();
        String lastName = body.getLastName();
        String firstName = body.getFirstName();
        String gender = body.getGender();
        
        if (meCaseNumber == null || meCaseNumber.isEmpty() || meOffice == null || meOffice.isEmpty())
        	return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        
        if (lastName == null || lastName.isEmpty()) lastName = "NOT_PROVIDED";
        if (firstName == null || firstName.isEmpty()) firstName = "NOT_PROVIDED";
        if (gender == null || gender.isEmpty()) gender = "unknown";
        
        Decedents res = decedentDao.search(meCaseNumber, meOffice, null, null, null);
        if (res.getCount() > 1) 
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        
        if (res.getCount() == 1) {
        	decedentDao.merge(res.getList().get(0), body);
			URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/manage/{id}").build()
					.expand(res.getList().get(0).getId()).toUri();
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(location);
			
        	return new ResponseEntity<>(headers, HttpStatus.OK);
        }
        
        Integer decedentId = decedentDao.save(body);        
		if (decedentId > 0) {
			URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/manage/{id}").build()
					.expand(decedentId).toUri();
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(location);

			return new ResponseEntity<>(headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    public ResponseEntity<Void> deleteDecedent(@ApiParam(value = "Decedent ID to be deleted",required=true) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        
        decedentDao.delete(id);
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    public ResponseEntity<Decedent> getDecedent(@ApiParam(value = "Get a decedent by ID",required=true) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        
        Decedent decedent = decedentDao.getById(id);
        if (decedent == null) 
        	return new ResponseEntity<Decedent>(HttpStatus.NO_CONTENT);
        else
        	return new ResponseEntity<Decedent>(decedent, HttpStatus.OK);
    }

    public ResponseEntity<Decedents> getDecedents() {
        String accept = request.getHeader("Accept");
        
        Decedents decedents = decedentDao.get();
        return new ResponseEntity<Decedents>(decedents, HttpStatus.OK);
    }

    public ResponseEntity<Void> updateDecedent(@ApiParam(value = "Decedent ID to be updated",required=true) @PathVariable("id") Integer id,@ApiParam(value = "Decedent info to add"  )  @Valid @RequestBody Decedent body) {
        String accept = request.getHeader("Accept");

        Decedent decedent = decedentDao.getById(id);
        if (decedent == null) {
        	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }

        decedentDao.update(id, body);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
