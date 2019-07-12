package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class ReactiveExample1 {

    private static Logger log = LoggerFactory.getLogger(ReactiveExample1.class);

    public static void main(String[] args) throws InterruptedException {
        //1.发布者
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        //2. 订阅者
        Flow.Subscriber subscriber = new Flow.Subscriber() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                log.info("onSubscribe");
                //请求数据
                subscription.request(1);
                this.subscription = subscription;
            }

            /**
             * 处理数据
             * @param item
             */
            @Override
            public void onNext(Object item) {
                log.info("item:{}", item);
                log.info("onNext");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.subscription.request(1);
            }

            /**
             * 数据处理异常
             * @param throwable
             */
            @Override
            public void onError(Throwable throwable) {
                log.info("onError");
            }

            /**
             * 数据完成
             */

            @Override
            public void onComplete() {
                log.info("onComplete");
            }
        };

        // 3. 建立关系
        publisher.subscribe(subscriber);
        // 4. 生产数据
        for (int i = 0; i < 500; i++) {
            publisher.submit("test" + i);
            log.info("submit:{}", "test" + i);
        }

        // 5 .结束关闭
        publisher.close();
        TimeUnit.SECONDS.sleep(10);

    }
}