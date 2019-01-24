package edu.gatech.chai.fhir.decedent_index.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import edu.gatech.chai.fhir.decedent_index.model.Decedent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Decedents
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-01-23T11:31:00.732213-05:00[America/New_York]")
public class Decedents   {
  @JsonProperty("count")
  private Integer count = null;

  @JsonProperty("created")
  private OffsetDateTime created = null;

  @JsonProperty("list")
  @Valid
  private List<Decedent> list = new ArrayList<Decedent>();

  public Decedents count(Integer count) {
    this.count = count;
    return this;
  }

  /**
   * Get count
   * @return count
  **/
  @ApiModelProperty(example = "1", required = true, value = "")
  @NotNull

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Decedents created(OffsetDateTime created) {
    this.created = created;
    return this;
  }

  /**
   * Get created
   * @return created
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public OffsetDateTime getCreated() {
    return created;
  }

  public void setCreated(OffsetDateTime created) {
    this.created = created;
  }

  public Decedents list(List<Decedent> list) {
    this.list = list;
    return this;
  }

  public Decedents addListItem(Decedent listItem) {
    this.list.add(listItem);
    return this;
  }

  /**
   * Get list
   * @return list
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public List<Decedent> getList() {
    return list;
  }

  public void setList(List<Decedent> list) {
    this.list = list;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Decedents decedents = (Decedents) o;
    return Objects.equals(this.count, decedents.count) &&
        Objects.equals(this.created, decedents.created) &&
        Objects.equals(this.list, decedents.list);
  }

  @Override
  public int hashCode() {
    return Objects.hash(count, created, list);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Decedents {\n");
    
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    list: ").append(toIndentedString(list)).append("\n");
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
