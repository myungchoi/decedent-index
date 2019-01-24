package edu.gatech.chai.fhir.decedent_index.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FhirSource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-01-23T11:31:00.732213-05:00[America/New_York]")
public class FhirSource   {
  @JsonProperty("fhirServerUrl")
  private String fhirServerUrl = null;

  @JsonProperty("fhirSecurity")
  private Object fhirSecurity = null;

  @JsonProperty("fhirVersion")
  private String fhirVersion = null;

  @JsonProperty("fhirPatientId")
  private String fhirPatientId = null;

  @JsonProperty("type")
  private String type = null;

  public FhirSource fhirServerUrl(String fhirServerUrl) {
    this.fhirServerUrl = fhirServerUrl;
    return this;
  }

  /**
   * Get fhirServerUrl
   * @return fhirServerUrl
  **/
  @ApiModelProperty(example = "https://apps.hdap.gatech.edu/gt-fhir/fhir", required = true, value = "")
  @NotNull

  public String getFhirServerUrl() {
    return fhirServerUrl;
  }

  public void setFhirServerUrl(String fhirServerUrl) {
    this.fhirServerUrl = fhirServerUrl;
  }

  public FhirSource fhirSecurity(Object fhirSecurity) {
    this.fhirSecurity = fhirSecurity;
    return this;
  }

  /**
   * place holder for future security model sepcification for FHIR server
   * @return fhirSecurity
  **/
  @ApiModelProperty(example = "{\"type\":\"basic\",\"username\":\"username\",\"password\":\"password\"}", value = "place holder for future security model sepcification for FHIR server")

  public Object getFhirSecurity() {
    return fhirSecurity;
  }

  public void setFhirSecurity(Object fhirSecurity) {
    this.fhirSecurity = fhirSecurity;
  }

  public FhirSource fhirVersion(String fhirVersion) {
    this.fhirVersion = fhirVersion;
    return this;
  }

  /**
   * Get fhirVersion
   * @return fhirVersion
  **/
  @ApiModelProperty(example = "STU3", required = true, value = "")
  @NotNull

  public String getFhirVersion() {
    return fhirVersion;
  }

  public void setFhirVersion(String fhirVersion) {
    this.fhirVersion = fhirVersion;
  }

  public FhirSource fhirPatientId(String fhirPatientId) {
    this.fhirPatientId = fhirPatientId;
    return this;
  }

  /**
   * Get fhirPatientId
   * @return fhirPatientId
  **/
  @ApiModelProperty(example = "1", required = true, value = "")
  @NotNull

  public String getFhirPatientId() {
    return fhirPatientId;
  }

  public void setFhirPatientId(String fhirPatientId) {
    this.fhirPatientId = fhirPatientId;
  }

  public FhirSource type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(example = "EHR", required = true, value = "")
  @NotNull

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FhirSource fhirSource = (FhirSource) o;
    return Objects.equals(this.fhirServerUrl, fhirSource.fhirServerUrl) &&
        Objects.equals(this.fhirSecurity, fhirSource.fhirSecurity) &&
        Objects.equals(this.fhirVersion, fhirSource.fhirVersion) &&
        Objects.equals(this.fhirPatientId, fhirSource.fhirPatientId) &&
        Objects.equals(this.type, fhirSource.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fhirServerUrl, fhirSecurity, fhirVersion, fhirPatientId, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FhirSource {\n");
    
    sb.append("    fhirServerUrl: ").append(toIndentedString(fhirServerUrl)).append("\n");
    sb.append("    fhirSecurity: ").append(toIndentedString(fhirSecurity)).append("\n");
    sb.append("    fhirVersion: ").append(toIndentedString(fhirVersion)).append("\n");
    sb.append("    fhirPatientId: ").append(toIndentedString(fhirPatientId)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
