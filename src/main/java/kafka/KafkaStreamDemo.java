package kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;

/**
 * kafka
 *
 * @author jimmy
 * @date 2019-05-28
 */
public class KafkaStreamDemo {
    private final static Logger logger = LoggerFactory.getLogger(KafkaStreamDemo.class);
    public static void main(String[] args) {
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount_topic_appid");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.1.132:9092");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> textLines = builder.stream("study");
        KTable<String, Long> wordCounts = textLines.flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
                .groupBy((key, word) -> {
                    logger.info("key is =>" + Serdes.Integer().deserializer().deserialize("SendWords", key.getBytes()));
                    logger.info("value is => " + word);
                    System.out.println();
                    return word;
                }).count();
        wordCounts.toStream().to("wordcount_topic", Produced.with(Serdes.String(), Serdes.Long()));
        KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.start();
    }
}
