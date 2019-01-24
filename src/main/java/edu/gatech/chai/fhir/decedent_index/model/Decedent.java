package edu.gatech.chai.fhir.decedent_index.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import edu.gatech.chai.fhir.decedent_index.model.FhirSource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Decedent
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-01-23T11:31:00.732213-05:00[America/New_York]")
public class Decedent   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("gender")
  private String gender = null;

  @JsonProperty("meOffice")
  private String meOffice = null;

  @JsonProperty("meCaseNumber")
  private String meCaseNumber = null;

  @JsonProperty("listOfFhirSources")
  @Valid
  private List<FhirSource> listOfFhirSources = null;

  public Decedent id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "1", value = "")

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Decedent lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
  **/
  @ApiModelProperty(example = "Edison", value = "")

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Decedent firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  **/
  @ApiModelProperty(example = "Thomas", value = "")

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Decedent gender(String gender) {
    this.gender = gender;
    return this;
  }

  /**
   * Get gender
   * @return gender
  **/
  @ApiModelProperty(example = "male", value = "")

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Decedent meOffice(String meOffice) {
    this.meOffice = meOffice;
    return this;
  }

  /**
   * Get meOffice
   * @return meOffice
  **/
  @ApiModelProperty(example = "UAB Forensic Lab", required = true, value = "")
  @NotNull

  public String getMeOffice() {
    return meOffice;
  }

  public void setMeOffice(String meOffice) {
    this.meOffice = meOffice;
  }

  public Decedent meCaseNumber(String meCaseNumber) {
    this.meCaseNumber = meCaseNumber;
    return this;
  }

  /**
   * Get meCaseNumber
   * @return meCaseNumber
  **/
  @ApiModelProperty(example = "12345", required = true, value = "")
  @NotNull

  public String getMeCaseNumber() {
    return meCaseNumber;
  }

  public void setMeCaseNumber(String meCaseNumber) {
    this.meCaseNumber = meCaseNumber;
  }

  public Decedent listOfFhirSources(List<FhirSource> listOfFhirSources) {
    this.listOfFhirSources = listOfFhirSources;
    return this;
  }

  public Decedent addListOfFhirSourcesItem(FhirSource listOfFhirSourcesItem) {
    if (this.listOfFhirSources == null) {
      this.listOfFhirSources = new ArrayList<FhirSource>();
    }
    this.listOfFhirSources.add(listOfFhirSourcesItem);
    return this;
  }

  /**
   * Get listOfFhirSources
   * @return listOfFhirSources
  **/
  @ApiModelProperty(value = "")
  @Valid
  public List<FhirSource> getListOfFhirSources() {
    return listOfFhirSources;
  }

  public void setListOfFhirSources(List<FhirSource> listOfFhirSources) {
    this.listOfFhirSources = listOfFhirSources;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Decedent decedent = (Decedent) o;
    return Objects.equals(this.id, decedent.id) &&
        Objects.equals(this.lastName, decedent.lastName) &&
        Objects.equals(this.firstName, decedent.firstName) &&
        Objects.equals(this.gender, decedent.gender) &&
        Objects.equals(this.meOffice, decedent.meOffice) &&
        Objects.equals(this.meCaseNumber, decedent.meCaseNumber) &&
        Objects.equals(this.listOfFhirSources, decedent.listOfFhirSources);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lastName, firstName, gender, meOffice, meCaseNumber, listOfFhirSources);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Decedent {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    meOffice: ").append(toIndentedString(meOffice)).append("\n");
    sb.append("    meCaseNumber: ").append(toIndentedString(meCaseNumber)).append("\n");
    sb.append("    listOfFhirSources: ").append(toIndentedString(listOfFhirSources)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
