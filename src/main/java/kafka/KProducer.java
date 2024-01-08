package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class KProducer {
    private static final Logger logger = LogManager.getLogger(KProducer.class);

    public static void main(String[] args) {
        new KProducer().run();

    }

    private void run() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,AppConfigs.applicationID);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(properties);

        producer.send(new ProducerRecord<>(AppConfigs.topicName.text, 1, "simple-message" + 1));
        producer.send(new ProducerRecord<>(AppConfigs.topicName.text, 2, "simple-message2" + 2));
        producer.send(new ProducerRecord<>(AppConfigs.topicName.text, 3, "simple-message3" + 3));

        producer.close();
        logger.info("this is info message for testing!");
        logger.warn("this is warning message for testing!");
        logger.error("this is an error for testing");
        logger.fatal("this is a fatal message for testing");
        new KConsumer().run();
    }

}
