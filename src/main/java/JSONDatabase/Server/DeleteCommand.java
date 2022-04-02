package Project4.Server;


import com.google.gson.JsonElement;

import java.io.IOException;

public class DeleteCommand implements Command{
    private final Database database;
    private final JsonElement key;

    public DeleteCommand(Database database, JsonElement key) {
        this.database = database;
        this.key = key;
    }

    @Override
    public void  execute() throws IOException {
        database.delete(key);
    }
}

