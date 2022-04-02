package Project4.Server;

import com.google.gson.JsonElement;

import java.io.IOException;

public class SetCommand implements Command{
    private final Database database;
    private final JsonElement key;
    private final JsonElement value;



    public SetCommand(Database database, JsonElement key, JsonElement value) {
        this.database = database;
        this.key = key;
        this.value = value;
    }

    @Override
    public void execute() throws IOException {
        database.set(key, value);
    }
}
