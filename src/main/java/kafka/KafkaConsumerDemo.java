package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerDemo {

    private final String topic;
    private final KafkaConsumer<Integer, String> consumer;

    public KafkaConsumerDemo(String topic) {
        this.topic = topic;
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream("consumer.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        consumer = new KafkaConsumer<Integer, String>(prop);
        consumer.subscribe(Collections.singletonList(this.topic));
    }

    public void consumer() {
        while (true) {
            ConsumerRecords<Integer, String> records = consumer.poll(1000);
            for (ConsumerRecord record : records) {
                System.out.println("Received message:(" + record.key() + "," + record.value() + ") at offset " + record.offset());
            }
        }
    }

    public static void main(String[] args) {
        KafkaConsumerDemo demo = new KafkaConsumerDemo("kafka_study_demo");
        new Thread(() -> demo.consumer()).start();
    }
}
