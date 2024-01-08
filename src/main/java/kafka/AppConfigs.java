package kafka;

public enum AppConfigs {

    applicationID("mahdi.id "),

    bootstrapServers("localhost:9092"),

    topicName("channel"),

    numEvents("1000000"),

    ;

    public final String text ;

    AppConfigs(String t) {
        text = t;
    }
}
