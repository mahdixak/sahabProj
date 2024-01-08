package kafka;

public class KafkaApplication {
    private static final KProducer producer = new KProducer();
    private static final KConsumer consumer = new KConsumer();
    public static void run(String jsonText){
        producer.sendJsonToKafka(jsonText);
    }
}
