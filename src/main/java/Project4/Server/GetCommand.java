package Project4.Server;


import com.google.gson.JsonElement;

import java.io.IOException;

public class GetCommand implements Command{
    private final Database database;
    private final JsonElement key;
    private JsonElement result;

    public JsonElement getResult() {
        return result;
    }

    public GetCommand(Database database, JsonElement key) {
        this.database = database;
        this.key = key;
    }

    @Override
    public void execute() throws IOException {
        result = database.get(key);
    }
}

