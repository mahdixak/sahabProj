package backend;

public enum Commands {

    show_warnings("^\\s*show\\s+warnings\\s*$"),

    show_errors("^\\s*show\\s+errors\\s*$"),

    last_100_logs("^\\s*last\\s+100\\s+logs\\s*$"),

    last_50_logs("^\\s*last\\s+50\\s+logs\\s*$"),

    last_10_logs("^\\s*last\\s+10\\s+logs\\s*$"),

    ;

    public final String command;

    Commands(String command) {
        this.command = command;
    }

}
