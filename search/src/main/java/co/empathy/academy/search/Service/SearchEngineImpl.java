package co.empathy.academy.search.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.empathy.academy.search.Model.QueryCluster;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class SearchEngineImpl implements SearchEngine{

    @Override
    public QueryCluster search(String query) throws IOException, InterruptedException, ParseException {
        if(query.isEmpty()){
            return null;
        }
        else{

            RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
            ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
            ElasticsearchClient client = new ElasticsearchClient(transport);

            Request request = new Request("GET","/");
            String response = EntityUtils.toString(restClient.performRequest(request).getEntity());

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject)parser.parse(response);
            System.out.print("JSON:" + json.toJSONString());
            QueryCluster ret = new QueryCluster(query, json.getAsString("cluster_name"));

            return ret;
        }
    }
}
