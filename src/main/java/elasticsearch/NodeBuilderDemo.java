package elasticsearch;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NodeBuilderDemo {
    public static void main(String[] args) throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        SearchResponse response = client.prepareSearch("megacorp").setTypes("employee").get();
        System.out.println(response);

        System.out.println(        response.getHits().getAt(0).getSourceAsMap());
        client.close();
    }
}
