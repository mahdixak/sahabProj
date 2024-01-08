package backend;

public enum Outputs {

    WARNING("Warning! : "),
    ERROR("Error! : ")

    ;

    public final String outputMessage;

    Outputs(String outputMessage) {
        this.outputMessage = outputMessage;
    }
}
