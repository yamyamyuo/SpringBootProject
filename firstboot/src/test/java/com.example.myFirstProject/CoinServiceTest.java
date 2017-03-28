package com.example.myFirstProject;

/**
 * Created by serena on 3/26/17.
 */
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;
import org.springframework.http.MediaType;
import javax.annotation.Resource;
import static org.junit.Assert.assertEquals;

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
        assertEquals(1, coinService.getUserCoins(104));
        assertEquals(10, coinService.getUserCoins(105));
        assertEquals(0, coinService.getUserCoins(106));
    }

    @Test
    public void transactionTest() throws Exception {
        //transfer correct amount of coins from valid user
        mockMvc.perform(post("/transaction/transfer")
                .param("from_user_id", "102")
                .param("to_user_id", "101")
                .param("coins", "2")
                ).andExpect(status().isOk());
    }

    @Test
    public void testTransactionInvalidUser() throws Exception {
        // transfer coins from invalid user_id
        mockMvc.perform(post("/transaction/transfer")
                .param("from_user_id", "107")
                .param("to_user_id", "101")
                .param("coins", "2")
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testTransactionInvalidAmount() throws Exception {
        // transfer coins from valid user_id with invalid coins
        mockMvc.perform(post("/transaction/transfer")
                .param("from_user_id", "106")
                .param("to_user_id", "101")
                .param("coins", "2")
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void getCoinsWithUserId() throws Exception{
        //
        mockMvc.perform(get("/coins/user/105")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("10")))
                ;
    }

    @Test
    public void addUserWithCoins() throws Exception {
        //user already exists will add failure
        mockMvc.perform(post("/user/add")
                        .param("user_id","101")
                        .param("coins","1")
                ).andDo(print())
                .andExpect(status().isBadRequest())
                ;
    }

    @Test
    public void addUserNotExist() throws Exception {
        //add user does not exists should return success
        mockMvc.perform(post("/user/add")
                .param("user_id", "1111")
                .param("coins", "1")
        ).andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void addUserLackParam() throws Exception {
        // add user not exists and without coins information, should return bad request
        mockMvc.perform(post("/user/add")
                .param("user_id", "1001")
        ).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void appendUserWithCoins() throws Exception  {
        //append the coins to user
        mockMvc.perform(post("/user/append")
                .param("user_id", "103")
                .param("coins", "2")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(post("/user/add")
                .param("user_id", "1001")
        ).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getRecord() throws Exception {
        mockMvc.perform(get("/getCoins")).andDo(print()).andExpect(status().isOk());
    }



}
