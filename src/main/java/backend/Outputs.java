package backend;

public enum Outputs {

    WARNING("Warning! : "),
    ERROR("Error! : ")

    ;

    private final String outputMessage;

    Outputs(String outputMessage) {
        this.outputMessage = outputMessage;
    }
}
