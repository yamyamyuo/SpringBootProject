package com.example.myFirstProject.controller;

/**
 * Created by serena on 3/25/17.
 */
import com.example.myFirstProject.domain.Coins;
import com.example.myFirstProject.service.CoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

/**
 * Created by serena on 3/25/17.
 */
@RestController
public class CoinsController {

    private static Logger logger = Logger.getLogger(CoinsController.class);
    @Resource(name = "coinServiceImp")
    private CoinService coinService;

    @RequestMapping(value = "/getCoins", method = RequestMethod.GET)
    @ResponseBody
    public List<Coins> getRecord() throws Exception {
        return coinService.getRecord();
    }

    @RequestMapping(value = "/user/add", method= RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestParam("user_id") int userId,
                                          @RequestParam("coins") int coins) {
        return coinService.addUserWithCoins(userId, coins) == true ? new ResponseEntity<String>(HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "user/append", method = RequestMethod.POST)
    public ResponseEntity<String> appendCoins2UserId(@RequestParam("user_id") int userId,
                                     @RequestParam("coins") int coins) {
        return coinService.appendCoins2UserId(userId, coins) == true ? new ResponseEntity<String>(HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/coins/user/{user_id}", method = RequestMethod.GET)
    public int getUserCoins(@PathVariable("user_id") int userId) {
        return coinService.getUserCoins(userId);
    }

    @RequestMapping(value = "/transaction/transfer", method = RequestMethod.POST)
    public ResponseEntity<String> transferCoins(@RequestParam("from_user_id") int fromUserId,
                                @RequestParam("to_user_id") int toUserId,
                                @RequestParam("coins") int coins) {
        return coinService.transferCoins(fromUserId, toUserId, coins) == true ? new ResponseEntity<String>(HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

}
