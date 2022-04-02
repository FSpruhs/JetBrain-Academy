package Project4.Client;



import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Message {

    private final String RELATIV_PATH = "src/client/data/";
    //private final String RELATIV_PATH = "./JSON Database/task/src/client/data/";
    private final String command;
    private final String key;
    private final String value;
    private final CommandLine cli;

    public Message(CommandLine cli) {
        this.cli = cli;
        this.command = cli.t;
        this.key = cli.k;
        this.value = cli.v;
    }

    private String openFileAndGetMessage(String path) {
        File file = new File(RELATIV_PATH + path);
        try (Scanner scanner = new Scanner(file)) {
            return scanner.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + path);
        }
        return "";
    }

    public String createGsonString() {
        Map<String, String> commands = new HashMap<>();
        Gson gson = new Gson();
        if (command != null && !command.isEmpty()) {
            commands.put("type", command);
        }
        if (key != null && !key.isEmpty()) {
            commands.put("key", key);
        }
        if (value != null && !value.isEmpty()) {
            commands.put("value", value);
        }
        return gson.toJson(commands);
    }

    public String getMessage() {
        if (cli.in != null) {
            return openFileAndGetMessage(cli.in);
        } else {
            return createGsonString();
        }
    }
}
