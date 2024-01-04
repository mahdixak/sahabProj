package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Producer {
    public Producer () {
        //bootstrap.servers : this is address of kafka server
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringSerializer");

        ProducerRecord record = new ProducerRecord("channel","name","self");

        KafkaProducer producer = new KafkaProducer(properties);
        producer.send(record);
        producer.close();
    }
}
