package com.example.myFirstProject.domain;

/**
 * Created by serena on 3/26/17.
 */
import com.example.myFirstProject.FirstBoot;
import com.example.myFirstProject.controller.CoinsController;
import com.example.myFirstProject.service.CoinService;
import com.example.myFirstProject.service.CoinServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(FirstBoot.class)
public class CoinServiceTest {
    @Autowired
    private CoinsRepository coinsRepository;

    @Test
    public void addUserTest() throws Exception{
        Coins testCoins = new Coins();
        testCoins.setUserId(101);
        testCoins.setCoins(2500);
        CoinService coinService = new CoinServiceImp();
//        boolean s = coinService.addUser(testCoins.getUserId());
//        System.out.print(s);
    }

}
