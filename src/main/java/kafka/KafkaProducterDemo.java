package kafka;

import kafka.server.KafkaConfig;
import kafka.server.KafkaServer;

import java.util.HashMap;
import java.util.Map;

public class KafkaProducterDemo {

    private KafkaServer kafkaServer = null;

    public KafkaProducterDemo() {
        Map<String,Object> properties  = new HashMap<>();
//        kafkaServer = new KafkaServer();
    }

    public KafkaConfig kafkaConfig() {
//        KafkaConfig kafkaConfig = new KafkaConfig();
        return null;
    }

}
