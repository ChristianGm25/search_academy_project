package co.empathy.academy.search.Controller;

import co.empathy.academy.search.Model.QueryCluster;
import co.empathy.academy.search.Service.SearchEngineImpl;
import co.empathy.academy.search.Service.SearchServiceImpl;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloWorldController {
    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name){
        return "Hello " + name;
    }

    @GetMapping("/search/{query}")
    public JSONObject getJSON(@PathVariable String query){
        JSONObject json = new JSONObject();
        String uri = "http://localhost:9200";
        RestTemplate rt = new RestTemplate();
        //JSONObject result = rt.getForObject(uri, JSONObject.class);
        SearchEngineImpl c = new SearchEngineImpl();
        QueryCluster qc = new QueryCluster();
        try{
            qc = c.search(query);
        }
        catch (Exception e){
            json.appendField("query", "Error");
            json.appendField("clusterName", "Error");
        }

        json.appendField("query", qc.getQuery());
        json.appendField("clusterName", qc.getCluster());
        return json;
    }
}
