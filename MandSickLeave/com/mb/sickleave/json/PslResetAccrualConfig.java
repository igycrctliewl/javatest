//-----------------------------------com.mb.sickleave.json.PslResetAccrualConfig.java-----------------------------------
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
"capInHours",
"carryOverAllowed",
"resetDay",
"resetMonth"
})
public class PslResetAccrualConfig {

@JsonProperty("capInHours")
private Integer capInHours;
@JsonProperty("carryOverAllowed")
private Boolean carryOverAllowed;
@JsonProperty("resetDay")
private Integer resetDay;
@JsonProperty("resetMonth")
private String resetMonth;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("capInHours")
public Integer getCapInHours() {
return capInHours;
}

@JsonProperty("capInHours")
public void setCapInHours(Integer capInHours) {
this.capInHours = capInHours;
}

@JsonProperty("carryOverAllowed")
public Boolean getCarryOverAllowed() {
return carryOverAllowed;
}

@JsonProperty("carryOverAllowed")
public void setCarryOverAllowed(Boolean carryOverAllowed) {
this.carryOverAllowed = carryOverAllowed;
}

@JsonProperty("resetDay")
public Integer getResetDay() {
return resetDay;
}

@JsonProperty("resetDay")
public void setResetDay(Integer resetDay) {
this.resetDay = resetDay;
}

@JsonProperty("resetMonth")
public String getResetMonth() {
return resetMonth;
}

@JsonProperty("resetMonth")
public void setResetMonth(String resetMonth) {
this.resetMonth = resetMonth;
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
