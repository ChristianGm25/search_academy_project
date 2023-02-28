package co.empathy.academy.search;

import jakarta.websocket.server.PathParam;
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
        JSONObject result = rt.getForObject(uri, JSONObject.class);
        json.appendField("query", query);
        json.appendField("clusterName", result.getAsString("cluster_name"));
        return json;
    }
}
