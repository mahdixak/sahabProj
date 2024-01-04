package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Properties;
@Service
public class Producer {
    private static final String TOPIC = "topic";

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String message) {
        this.kafkaTemplate.send(TOPIC,message);
    }
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
