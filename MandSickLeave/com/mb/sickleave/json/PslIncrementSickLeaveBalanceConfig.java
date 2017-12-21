//-----------------------------------com.mb.sickleave.json.PslIncrementSickLeaveBalanceConfig.java-----------------------------------
// generated from www.jsonschema2pojo.org

package com.mb.sickleave.json;

import java.math.BigDecimal;
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
"accrualRate",
"capInHours",
"daysAfterAccrualBegins"
})
public class PslIncrementSickLeaveBalanceConfig {

@JsonProperty("accrualRate")
private BigDecimal accrualRate;
@JsonProperty("capInHours")
private Integer capInHours;
@JsonProperty("daysAfterAccrualBegins")
private Integer daysAfterAccrualBegins;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("accrualRate")
public BigDecimal getAccrualRate() {
return accrualRate;
}

@JsonProperty("accrualRate")
public void setAccrualRate(BigDecimal accrualRate) {
this.accrualRate = accrualRate;
}

@JsonProperty("capInHours")
public Integer getCapInHours() {
return capInHours;
}

@JsonProperty("capInHours")
public void setCapInHours(Integer capInHours) {
this.capInHours = capInHours;
}

@JsonProperty("daysAfterAccrualBegins")
public Integer getDaysAfterAccrualBegins() {
return daysAfterAccrualBegins;
}

@JsonProperty("daysAfterAccrualBegins")
public void setDaysAfterAccrualBegins(Integer daysAfterAccrualBegins) {
this.daysAfterAccrualBegins = daysAfterAccrualBegins;
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
