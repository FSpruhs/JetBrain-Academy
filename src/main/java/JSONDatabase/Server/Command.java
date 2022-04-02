package Project4.Server;


import java.io.IOException;

public interface Command {
    void execute() throws IOException;
}