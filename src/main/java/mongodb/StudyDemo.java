package mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * mongodb
 *
 * @author jimmy
 * @date 2019-09-05
 */
public class StudyDemo {
    private static int THREAD_NUMS = 10;
    private static long INIT_NUM = 100000000L;
    private static List<MongoClient> mongoClientPools = Collections.synchronizedList(MongoClientInstance.createPools(THREAD_NUMS));
    private static CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUMS);
    private static MongoClient mongoClient = MongoClientInstance.getInstance();

    //这个方法能初始化数据，多线程初始化
    private static void initData() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_NUMS);
        long avg = INIT_NUM / THREAD_NUMS;
        AtomicLong ids = new AtomicLong(1);
        for (int i = 0; i < THREAD_NUMS; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                MongoDatabase studyDB = mongoClientPools.get(finalI).getDatabase("study");
                MongoCollection<Document> persons = studyDB.getCollection("persons");
                persons.createIndex(new Document("id", -1), new IndexOptions().unique(true));
                List<Document> lists = new ArrayList<>();
                for (long j = 0; j < avg; j++) {
                    Document doc = new Document();
                    doc.append("name", UUID.randomUUID().toString());
                    doc.append("id", ids.getAndAdd(1));
                    lists.add(doc);
                    if (j % 100 == 0) {
                        persons.insertMany(lists);
                        lists.clear();
                    }
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        threadPool.shutdownNow();
    }

    public static void main(String[] args) throws InterruptedException {
        MongoCollection<Document> persons = mongoClient.getDatabase("study").getCollection("persons");
        DBObject condition = new BasicDBObject("$lte", 100);
        long startTime = System.currentTimeMillis();
        FindIterable<Document> result = persons.find(new BasicDBObject("id", condition));
        long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime) + "ms");
        MongoCursor<Document> resultFilter = result.iterator();
        while (resultFilter.hasNext()) {
            Document doc = resultFilter.next();
            String formatter = String.format("id:%d,name:%s", doc.get("id"), doc.get("name"));
            System.out.println(formatter);
        }
    }
}
