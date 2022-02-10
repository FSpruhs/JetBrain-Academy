package Project4.Server;


import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

public class Request {

    @Expose private String type;
    @Expose private JsonElement key;
    @Expose private JsonElement value;

    public void setKey(JsonElement key) {
        this.key = key;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(JsonElement value) {
        this.value = value;
    }

    public JsonElement getKey() {
        return key;
    }

    public JsonElement getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Request{" +
                "type='" + type + '\'' +
                ", key=" + key +
                ", value=" + value +
                '}';
    }
}
