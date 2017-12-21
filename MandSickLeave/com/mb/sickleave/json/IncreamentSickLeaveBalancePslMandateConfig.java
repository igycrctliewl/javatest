//-----------------------------------com.mb.sickleave.json.IncreamentSickLeaveBalancePslMandateConfig.java-----------------------------------
// generated from www.jsonschema2pojo.org

package com.mb.sickleave.json;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"city",
"minimumEmployees",
"stateCd"
})
public class IncreamentSickLeaveBalancePslMandateConfig {

@JsonProperty("city")
private Object city;
@JsonProperty("minimumEmployees")
private Integer minimumEmployees;
@JsonProperty("stateCd")
private String stateCd;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("city")
public Object getCity() {
return city;
}

@JsonProperty("city")
public void setCity(Object city) {
this.city = city;
}

@JsonProperty("minimumEmployees")
public Integer getMinimumEmployees() {
return minimumEmployees;
}

@JsonProperty("minimumEmployees")
public void setMinimumEmployees(Integer minimumEmployees) {
this.minimumEmployees = minimumEmployees;
}

@JsonProperty("stateCd")
public String getStateCd() {
return stateCd;
}

@JsonProperty("stateCd")
public void setStateCd(String stateCd) {
this.stateCd = stateCd;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
