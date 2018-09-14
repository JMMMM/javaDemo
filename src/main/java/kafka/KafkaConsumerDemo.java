package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.internals.ConsumerCoordinator;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

/**
 * kafka基础消费者代码
 */
public class KafkaConsumerDemo {

    private final String topic;
    private final KafkaConsumer<Integer, String> consumer;
    private final String consumerName;

    public KafkaConsumerDemo(String consumerName, String topic) {
        this.topic = topic;
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream("consumer.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.consumer = new KafkaConsumer<Integer, String>(prop);
        this.consumer.subscribe(Collections.singletonList(this.topic));
        this.consumerName = consumerName;
    }

    public void consumer() {
        while (true) {
            ConsumerRecords<Integer, String> records = consumer.poll(1000);
            System.out.println(consumerName + " 拉取record size:" + records.count());
            for (ConsumerRecord record : records) {
                System.out.println(consumerName + ": Received message:(" + record.key() + "," + record.value() + ") at offset " + record.offset());
            }
        }
    }

    public static void main(String[] args) {
        KafkaConsumerDemo demo = new KafkaConsumerDemo("consumer1", "kafka_study_demo");
        new Thread(() -> demo.consumer()).start();
        KafkaConsumerDemo demo2 = new KafkaConsumerDemo("consumer2", "kafka_study_demo");
        new Thread(() -> demo2.consumer()).start();
    }
}
