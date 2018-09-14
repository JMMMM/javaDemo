package kafka;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import kafka.consumer.Consumer;
import kafka.javaapi.consumer.ConsumerConnector;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * kafka 消费者高级API客户端
 */
public class KafkaConsumerHighDemo {

    private final String topic;
    private final ConsumerConnector consumer;
    private final String consumerName;

    public KafkaConsumerHighDemo(String consumerName, String topic) {
        this.topic = topic;
        this.consumerName = consumerName;
        Properties props = new Properties();
        try {
            props.load(getClass().getResourceAsStream("consumer.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConsumerConfig conf = new ConsumerConfig(props);
        this.consumer = Consumer.createJavaConsumerConnector(conf);
    }

    public void consume() {
        Map<String, Integer> topicCountMap = new HashMap();
        topicCountMap.put(topic, 1);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(this.topic);
        KafkaStream<byte[], byte[]> stream = streams.get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            System.out.println("message:" + new String(it.next().message()));
        }

    }
}