package com.example.myFirstProject;

/**
 * Created by serena on 3/26/17.
 */
import com.example.myFirstProject.domain.Coins;
import com.example.myFirstProject.service.CoinService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;
import org.springframework.http.MediaType;
import javax.annotation.Resource;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(FirstBoot.class)
@WebIntegrationTest("server.port:0")
public class CoinServiceTest {
    @Resource(name = "coinServiceImp")
    private CoinService coinService;

    @Autowired
    private WebApplicationContext context;

    @Value("${local.server.port}")
    private int port;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void contextLoadTest() {
        // assertEquals to the Database setup values;
        assertEquals(5000, coinService.getUserCoins(101));
        assertEquals(5000, coinService.getUserCoins(102));
        assertEquals(2000, coinService.getUserCoins(103));
        assertEquals(1, coinService.getUserCoins(104));
        assertEquals(10, coinService.getUserCoins(105));
        assertEquals(0, coinService.getUserCoins(106));
    }

    @Test
    public void addUserTest() throws Exception  {
        mockMvc.perform(get("/coins/user/101")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("5000")));

    }
    @Test
    public void webappPublisherApi() throws Exception{
        mockMvc.perform(get("/coins/user/101")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("5000")));
//                .andExpect(jsonPath("$.user").value("101"));
    }
    @Test
    public void test2() throws Exception {
        mockMvc.perform(post("/user/add")
                        .param("user_id","1")
                        .param("coins","1")
                ).andDo(print())
//                .andExpect(status().isBadRequest())
                ;
//                .andExpect(status().isNotFound());
//                .andExpect(content().string(containsString("Add OK")));

    }



}
