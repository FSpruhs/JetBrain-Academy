package Project4.Server;


import com.google.gson.GsonBuilder;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EditRequestTask implements Runnable{

    private final Socket socket;
    private final Database database;
    private final DataOutputStream output;
    private final Request request;
    Command command;

    public EditRequestTask(Socket socketForClient, Database database, Request request,
                           DataOutputStream output) {
        this.socket = socketForClient;
        this.database = database;
        this.output = output;
        this.request = request;

    }

    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public void run() {
        try  {
            Response response = new Response();

            if (request.getType().equals("get")) {
                try {
                    Command get = new GetCommand(database, request.getKey());
                    setCommand(get);
                    command.execute();
                    response.setResponse("OK");
                    response.setValue(((GetCommand) get).getResult());
                } catch (Exception e) {
                    response.setResponse("ERROR");
                    response.setReason("No such key");
                }
            } else if (request.getType().equals("set")) {
                Command set = new SetCommand(database, request.getKey(), request.getValue());
                setCommand(set);
                command.execute();
                response.setResponse("OK");
            } else if (request.getType().equals("delete")) {
                try {
                    Command delete = new DeleteCommand(database, request.getKey());
                    setCommand(delete);
                    command.execute();
                    response.setResponse("OK");
                } catch (Exception e) {
                    response.setResponse("ERROR");
                    response.setReason("No such key");
                }
            }
            String msgJson = new GsonBuilder().create().toJson(response);
            System.out.println("Sent: " + msgJson);
            output.writeUTF(msgJson);


            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
