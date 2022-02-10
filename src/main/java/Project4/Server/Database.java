package Project4.Server;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {

    private final String RELATIV_PATH = System.getProperty("user.dir") + "/src/server/data/db.json";
    //private final String RELATIV_PATH =  "./JSON Database/task/src/server/data/db.json";
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    public JsonObject database;
    private final Gson gson = new Gson();

    public Database() throws IOException {
        readDatabase();
    }

    private void readDatabase() throws IOException {
        if (Files.exists(Path.of(RELATIV_PATH))) {
            String content = new String(Files.readAllBytes(Path.of(RELATIV_PATH)));
            readLock.lock();
            database = new Gson().fromJson(content, JsonObject.class);
            readLock.unlock();
        } else {
            Files.createFile(Path.of(RELATIV_PATH));
            database = new JsonObject();
            writeDatabase();
        }
    }

    private void writeDatabase() {
        writeLock.lock();
        File file = new File(RELATIV_PATH);
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(database, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeLock.unlock();
    }

    private JsonElement searchAndCreate (JsonArray keys) {
        JsonElement helpVar = database;
        for (JsonElement key: keys) {
            if (!helpVar.getAsJsonObject().has(key.getAsString())) {
                helpVar.getAsJsonObject().add(key.getAsString(), new JsonObject());
            }
            helpVar = helpVar.getAsJsonObject().get(key.getAsString());
        }
        return helpVar;
    }

    private JsonElement search (JsonArray keys) {
        JsonElement helpVar = database;
        for (JsonElement key : keys) {
            if (!key.isJsonPrimitive() || !helpVar.getAsJsonObject().has(key.getAsString())) {
                throw new IndexOutOfBoundsException("ERROR");
            }
            helpVar = helpVar.getAsJsonObject().get(key.getAsString());
        }
        return helpVar;
    }

    public void set(JsonElement key, JsonElement value) throws IOException {
        readDatabase();
        if (key.isJsonPrimitive()) {
            database.add(key.getAsString(), value);
        } else if (key.isJsonArray()) {
            JsonArray keys = key.getAsJsonArray();
            String toAdd = keys.remove(keys.size() - 1).getAsString();
            searchAndCreate(keys).getAsJsonObject().add(toAdd, value);
        }
        writeDatabase();
    }


    public JsonElement get(JsonElement key) throws IOException {
        readDatabase();
        if (key.isJsonPrimitive() && database.has(key.getAsString())) {
            return database.get(key.getAsString());
        } else {
            return search(key.getAsJsonArray());
        }
    }

    public void delete(JsonElement key) throws IndexOutOfBoundsException, IOException {
        readDatabase();
        if (key.isJsonPrimitive() && database.has(key.getAsString())) {
            database.remove(key.getAsString());
        } else if (key.isJsonArray()) {
            JsonArray keys = key.getAsJsonArray();
            String toRemove = keys.remove(keys.size() - 1).getAsString();
            search(keys).getAsJsonObject().remove(toRemove);
        } else {
            throw new IndexOutOfBoundsException("ERROR");
        }
        writeDatabase();
    }
}

