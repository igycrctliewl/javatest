//-----------------------------------com.mb.sickleave.json.Response.java-----------------------------------
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
"data",
"_requestId",
"_statusCode",
"_statusText",
"_statusMessage"
})
public class Response {

@JsonProperty("data")
private Data data;
@JsonProperty("_requestId")
private String requestId;
@JsonProperty("_statusCode")
private String statusCode;
@JsonProperty("_statusText")
private String statusText;
@JsonProperty("_statusMessage")
private String statusMessage;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("data")
public Data getData() {
return data;
}

@JsonProperty("data")
public void setData(Data data) {
this.data = data;
}

@JsonProperty("_requestId")
public String getRequestId() {
return requestId;
}

@JsonProperty("_requestId")
public void setRequestId(String requestId) {
this.requestId = requestId;
}

@JsonProperty("_statusCode")
public String getStatusCode() {
return statusCode;
}

@JsonProperty("_statusCode")
public void setStatusCode(String statusCode) {
this.statusCode = statusCode;
}

@JsonProperty("_statusText")
public String getStatusText() {
return statusText;
}

@JsonProperty("_statusText")
public void setStatusText(String statusText) {
this.statusText = statusText;
}

@JsonProperty("_statusMessage")
public String getStatusMessage() {
return statusMessage;
}

@JsonProperty("_statusMessage")
public void setStatusMessage(String statusMessage) {
this.statusMessage = statusMessage;
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