//-----------------------------------com.mb.sickleave.json.Data.java-----------------------------------
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
"increamentSickLeaveBalancePslMandateConfig",
"mandateAccrualHours",
"pslIncrementSickLeaveBalanceConfig",
"pslResetAccrualConfig",
"resetAccrualPslMandateConfig"
})
public class Data {

@JsonProperty("increamentSickLeaveBalancePslMandateConfig")
private IncreamentSickLeaveBalancePslMandateConfig increamentSickLeaveBalancePslMandateConfig;
@JsonProperty("mandateAccrualHours")
private BigDecimal mandateAccrualHours;
@JsonProperty("pslIncrementSickLeaveBalanceConfig")
private PslIncrementSickLeaveBalanceConfig pslIncrementSickLeaveBalanceConfig;
@JsonProperty("pslResetAccrualConfig")
private PslResetAccrualConfig pslResetAccrualConfig;
@JsonProperty("resetAccrualPslMandateConfig")
private ResetAccrualPslMandateConfig resetAccrualPslMandateConfig;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("increamentSickLeaveBalancePslMandateConfig")
public IncreamentSickLeaveBalancePslMandateConfig getIncreamentSickLeaveBalancePslMandateConfig() {
return increamentSickLeaveBalancePslMandateConfig;
}

@JsonProperty("increamentSickLeaveBalancePslMandateConfig")
public void setIncreamentSickLeaveBalancePslMandateConfig(IncreamentSickLeaveBalancePslMandateConfig increamentSickLeaveBalancePslMandateConfig) {
this.increamentSickLeaveBalancePslMandateConfig = increamentSickLeaveBalancePslMandateConfig;
}

@JsonProperty("mandateAccrualHours")
public BigDecimal getMandateAccrualHours() {
return mandateAccrualHours;
}

@JsonProperty("mandateAccrualHours")
public void setMandateAccrualHours(BigDecimal mandateAccrualHours) {
this.mandateAccrualHours = mandateAccrualHours;
}

@JsonProperty("pslIncrementSickLeaveBalanceConfig")
public PslIncrementSickLeaveBalanceConfig getPslIncrementSickLeaveBalanceConfig() {
return pslIncrementSickLeaveBalanceConfig;
}

@JsonProperty("pslIncrementSickLeaveBalanceConfig")
public void setPslIncrementSickLeaveBalanceConfig(PslIncrementSickLeaveBalanceConfig pslIncrementSickLeaveBalanceConfig) {
this.pslIncrementSickLeaveBalanceConfig = pslIncrementSickLeaveBalanceConfig;
}

@JsonProperty("pslResetAccrualConfig")
public PslResetAccrualConfig getPslResetAccrualConfig() {
return pslResetAccrualConfig;
}

@JsonProperty("pslResetAccrualConfig")
public void setPslResetAccrualConfig(PslResetAccrualConfig pslResetAccrualConfig) {
this.pslResetAccrualConfig = pslResetAccrualConfig;
}

@JsonProperty("resetAccrualPslMandateConfig")
public ResetAccrualPslMandateConfig getResetAccrualPslMandateConfig() {
return resetAccrualPslMandateConfig;
}

@JsonProperty("resetAccrualPslMandateConfig")
public void setResetAccrualPslMandateConfig(ResetAccrualPslMandateConfig resetAccrualPslMandateConfig) {
this.resetAccrualPslMandateConfig = resetAccrualPslMandateConfig;
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
