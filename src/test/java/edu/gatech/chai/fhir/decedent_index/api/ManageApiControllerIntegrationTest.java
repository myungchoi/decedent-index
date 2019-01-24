package edu.gatech.chai.fhir.decedent_index.api;

import edu.gatech.chai.fhir.decedent_index.model.Decedent;
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
public class ManageApiControllerIntegrationTest {

    @Autowired
    private ManageApi api;

    @Test
    public void addDecedentTest() throws Exception {
        Decedent body = new Decedent();
        ResponseEntity<Void> responseEntity = api.addDecedent(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getDecedentTest() throws Exception {
        Integer id = 56;
        ResponseEntity<Decedent> responseEntity = api.getDecedent(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getDecedentsTest() throws Exception {
        ResponseEntity<Decedents> responseEntity = api.getDecedents();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateDecedentTest() throws Exception {
        Integer id = 56;
        Decedent body = new Decedent();
        ResponseEntity<Void> responseEntity = api.updateDecedent(id, body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
