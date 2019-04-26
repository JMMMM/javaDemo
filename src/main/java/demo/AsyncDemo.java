package demo;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

public class AsyncDemo {
    public static void main(String[] args) {

        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        httpclient.start();
        httpclient.execute(HttpHost.create("https://www.baidu.com"), new HttpGet(), new FutureCallback() {
            @Override
            public void completed(Object o) {
                System.out.println(o);
            }

            @Override
            public void failed(Exception e) {
                System.out.println(e);

            }

            @Override
            public void cancelled() {

            }
        });
    }
}
