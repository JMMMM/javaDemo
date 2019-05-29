package kafka;

import kafka.server.KafkaConfig;
import kafka.server.KafkaServer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Kafka基础生产者代码
 */
public class KafkaProducerDemo {

    private final String topic;
    private final Boolean isAsync;
    private final KafkaProducer<Integer, String> prod;

    public KafkaProducerDemo(String topic, Boolean isAsync) {
        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("producer.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.prod = new KafkaProducer(props);
        this.topic = topic;
        this.isAsync = isAsync;
    }


    private void send(Integer key, String message) {
        ProducerRecord<Integer, String> record = new ProducerRecord(this.topic, key, message);
        if (isAsync) {
            prod.send(record, (recordMetadata, e1) ->
                    System.out.println("#offset:" + recordMetadata.offset()));
        } else {
            try {
                prod.send(record).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) throws InterruptedException {
        KafkaProducerDemo producerDemo = new KafkaProducerDemo("study", false);
        int i = 0;
        while (true) {
            producerDemo.send(1, "message_" + i++);
            Thread.sleep(5000);
        }
    }
}
