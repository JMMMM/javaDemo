package mongodb;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * mongodb
 *
 * @author jimmy
 * @date 2019-09-05
 */
public class MongoClientInstance {

    private MongoClientInstance() {
    }

    private static class MongoClientHolder {
        private static final MongoClient mongoClient = getInstance();

        private static MongoClient getInstance() {
            return build();
        }

        private static MongoClient build() {
            com.mongodb.client.MongoClient mongoClient = MongoClients.create(
                    MongoClientSettings.builder()
                            .applyToClusterSettings(builder ->
                                    builder.hosts(Arrays.asList(new ServerAddress("10.0.1.182")))
                            ).build());
            return mongoClient;
        }
    }

    public static MongoClient getInstance() {
        return MongoClientHolder.mongoClient;
    }

    private static class MongoClientPoolHolder {
        private static int CONNECTION_NUMS = 5;
        private static final List<MongoClient> clientPools = getInstance();

        private static List<MongoClient> getInstance() {
            List<MongoClient> connectionPool = new ArrayList<>(CONNECTION_NUMS);
            for (int i = 0; i < CONNECTION_NUMS; i++) {
                MongoClient mongoClient = MongoClientHolder.build();
                connectionPool.add(mongoClient);
            }
            return connectionPool;
        }
    }

    public static List<MongoClient> createPools() {
        return MongoClientPoolHolder.clientPools;
    }

    public static List<MongoClient> createPools(int nums) {
        MongoClientPoolHolder.CONNECTION_NUMS = nums;
        return MongoClientPoolHolder.clientPools;
    }

}
