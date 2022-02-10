package Project4.Client;

import com.beust.jcommander.JCommander;

public class Main {

    public static void main(String[] args) {
        CommandLine cli = new CommandLine();
        JCommander.newBuilder().addObject(cli).build().parse(args);
        Message message = new Message(cli);
        Client client = new Client(message);
    }
}
