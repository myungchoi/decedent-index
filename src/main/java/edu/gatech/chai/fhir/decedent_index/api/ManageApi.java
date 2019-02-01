/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.4).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package edu.gatech.chai.fhir.decedent_index.api;

import edu.gatech.chai.fhir.decedent_index.model.Decedent;
import edu.gatech.chai.fhir.decedent_index.model.Decedents;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-02-01T16:28:39.039030-05:00[America/New_York]")
@Api(value = "manage", description = "the manage API")
public interface ManageApi {

    @ApiOperation(value = "adds a decedent", nickname = "addDecedent", notes = "Adds a decedent to the system", authorizations = {
        @Authorization(value = "basicAuth")    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "a decedent created"),
        @ApiResponse(code = 400, message = "invalid input, object invalid"),
        @ApiResponse(code = 401, message = "Authentication information is missing or invalid"),
        @ApiResponse(code = 409, message = "decedent already exists") })
    @RequestMapping(value = "/manage",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addDecedent(@ApiParam(value = "Decedent info to add"  )  @Valid @RequestBody Decedent body);


    @ApiOperation(value = "delete a decedent", nickname = "deleteDecedent", notes = "", authorizations = {
        @Authorization(value = "basicAuth")    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "a decedent deleted"),
        @ApiResponse(code = 401, message = "Authentication information is missing or invalid") })
    @RequestMapping(value = "/manage/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteDecedent(@ApiParam(value = "Decedent ID to be deleted",required=true) @PathVariable("id") Integer id);


    @ApiOperation(value = "get a decedent by ID", nickname = "getDecedent", notes = "", response = Decedent.class, authorizations = {
        @Authorization(value = "basicAuth")    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "a decedent", response = Decedent.class),
        @ApiResponse(code = 400, message = "invalid input, object invalid"),
        @ApiResponse(code = 401, message = "Authentication information is missing or invalid") })
    @RequestMapping(value = "/manage/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Decedent> getDecedent(@ApiParam(value = "Get a decedent by ID",required=true) @PathVariable("id") Integer id);


    @ApiOperation(value = "get all decedents", nickname = "getDecedents", notes = "", response = Decedents.class, authorizations = {
        @Authorization(value = "basicAuth")    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "get all decedents in the system", response = Decedents.class),
        @ApiResponse(code = 401, message = "Authentication information is missing or invalid") })
    @RequestMapping(value = "/manage",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Decedents> getDecedents();


    @ApiOperation(value = "edit a decedent", nickname = "updateDecedent", notes = "", authorizations = {
        @Authorization(value = "basicAuth")    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "a decedent updated"),
        @ApiResponse(code = 400, message = "invalid input, object invalid"),
        @ApiResponse(code = 401, message = "Authentication information is missing or invalid") })
    @RequestMapping(value = "/manage/{id}",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateDecedent(@ApiParam(value = "Decedent ID to be updated",required=true) @PathVariable("id") Integer id,@ApiParam(value = "Decedent info to add"  )  @Valid @RequestBody Decedent body);

}
