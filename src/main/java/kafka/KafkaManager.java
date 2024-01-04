package kafka;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;

import java.util.Properties;

public class KafkaManager {
    private final Properties streamConfiguration = new Properties();
    void run () {
        streamConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG,"sahab-project");
        streamConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "broker1:9092,broker2:9092");
    }

    void creatingKafkaTopics() {
        final KStreamBuilder builder = new KStreamBuilder();
        final KStream<String,String> events = builder.stream("first topic title!");
    }
}
