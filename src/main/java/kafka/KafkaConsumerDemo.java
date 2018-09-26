package kafka;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.google.protobuf.InvalidProtocolBufferException;
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
    private final KafkaConsumer<String, byte[]> consumer;
    private final String consumerName;

    public KafkaConsumerDemo(String consumerName, String topic) {
        this.topic = topic;
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream("consumer.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.consumer = new KafkaConsumer<String, byte[]>(prop);
        this.consumer.subscribe(Collections.singletonList(this.topic));
        this.consumerName = consumerName;
    }

    public void consumer() {
        while (true) {
            ConsumerRecords<String, byte[]> records = consumer.poll(1000);
            for (ConsumerRecord record : records) {
                try {
                    CanalEntry.RowChange temp = CanalEntry.RowChange.parseFrom((byte[])record.value());
                    temp.toString();
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
                System.out.println(consumerName + ": Received message:(" + record.key() + "," + record.value() + ") at offset " + record.offset());
            }
        }
    }

    public static void main(String[] args) {
        KafkaConsumerDemo demo = new KafkaConsumerDemo("consumer1", "async_cache");
        new Thread(() -> demo.consumer()).start();
    }
}
