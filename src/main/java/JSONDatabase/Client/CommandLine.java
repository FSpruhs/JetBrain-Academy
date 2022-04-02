package Project4.Client;

import com.beust.jcommander.Parameter;

public class CommandLine {
    @Parameter(names = "-t", description = "type of the request")
    public String t;

    @Parameter(names = "-k", description = "is the key")
    public String k;

    @Parameter(names = "-v", description = "value to save in the database")
    public String v;

    @Parameter(names = "-in", description = "request file Path")
    public String in;
}

