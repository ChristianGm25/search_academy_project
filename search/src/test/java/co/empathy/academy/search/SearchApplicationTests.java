package co.empathy.academy.search;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class SearchApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void givenQuery() throws Exception {
		JSONObject json = new JSONObject();
		json.appendField("query", "hola");
		json.appendField("clusterName", "docker-cluster");
		mvc.perform(MockMvcRequestBuilders.get("/search/{query}", "hola"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(json.toJSONString()));
	}
}
