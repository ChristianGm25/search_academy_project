package com.example.search_API;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

public class ServiceImpl {
    public String getName() {
        // Create the low-level client
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // And create the API client
        ElasticsearchClient client = new ElasticsearchClient(transport);

        String ret = "";
        try{
            ret = client.cluster().state().toString();
        }
        catch(Exception e){
            return "Error";
        }
        return ret;

    }
}
