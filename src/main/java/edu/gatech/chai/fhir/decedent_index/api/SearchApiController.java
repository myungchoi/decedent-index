package edu.gatech.chai.fhir.decedent_index.api;

import edu.gatech.chai.fhir.decedent_index.dao.DecedentDaoImpl;
import edu.gatech.chai.fhir.decedent_index.dao.FhirSourceDaoImpl;
import edu.gatech.chai.fhir.decedent_index.model.Decedents;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-01-23T11:31:00.732213-05:00[America/New_York]")
@Controller
public class SearchApiController implements SearchApi {

    private static final Logger log = LoggerFactory.getLogger(SearchApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    DecedentDaoImpl decedentDao;
    
    @org.springframework.beans.factory.annotation.Autowired
    FhirSourceDaoImpl fhirSourceDao;

    @org.springframework.beans.factory.annotation.Autowired
    public SearchApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Decedents> searchDecedent(@NotNull @ApiParam(value = "casd number at ME office", required = true) @Valid @RequestParam(value = "case-number", required = true) String caseNumber,@NotNull @ApiParam(value = "ME office name", required = true) @Valid @RequestParam(value = "medical-examiner-office", required = true) String medicalExaminerOffice,@ApiParam(value = "name of decedent") @Valid @RequestParam(value = "name", required = false) String name,@ApiParam(value = "search family name porition of name") @Valid @RequestParam(value = "family", required = false) String family,@ApiParam(value = "search given name portion of name") @Valid @RequestParam(value = "given", required = false) String given) {
        String accept = request.getHeader("Accept");
        
        Decedents decedents = decedentDao.search(caseNumber, medicalExaminerOffice, family, given, name);
        return new ResponseEntity<Decedents>(decedents, HttpStatus.OK);
    }

}
