package Project4.Server;


import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

public class Response {

    @Expose private String response;
    @Expose private String reason;
    @Expose private JsonElement value;

    public void setValue(JsonElement value) {
        this.value = value;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public JsonElement getValue() {
        return value;
    }

    public String getReason() {
        return reason;
    }

    public String getResponse() {
        return response;
    }

}
