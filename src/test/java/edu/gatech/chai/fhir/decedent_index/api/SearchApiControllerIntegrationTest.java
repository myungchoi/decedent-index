package edu.gatech.chai.fhir.decedent_index.api;

import edu.gatech.chai.fhir.decedent_index.model.Decedents;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchApiControllerIntegrationTest {

    @Autowired
    private SearchApi api;

    @Test
    public void searchDecedentTest() throws Exception {
        String caseNumber = "caseNumber_example";
        String medicalExaminerOffice = "medicalExaminerOffice_example";
        String name = "name_example";
        String family = "family_example";
        String given = "given_example";
        ResponseEntity<Decedents> responseEntity = api.searchDecedent(caseNumber, medicalExaminerOffice, name, family, given);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
