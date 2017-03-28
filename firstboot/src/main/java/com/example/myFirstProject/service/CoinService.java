package com.example.myFirstProject.service;

import com.example.myFirstProject.domain.Coins;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by serena on 3/26/17.
 */
public interface CoinService {

    List<Coins> getRecord() throws Exception;
    /**
     * create a user with user_id, default coins = 0
     * @param userId
     */
    boolean addUser(int userId);

    /**
     * get coins of userId
     * @param userId
     */
    int getUserCoins(int userId);

    /**
     * create a user record with coins
     * @param userId
     * @param coins
     */
    boolean addUserWithCoins(int userId, int coins);

    /**
     * if the userId already exists in the DB, append the coins to the user
     * @param userId
     * @param coins
     * @return
     */
    boolean appendCoins2UserId(int userId, int coins);

    /**
     * transfer the amount of coins from fromUserId to toUserId
     * @param fromUserId
     * @param toUserId
     * @param amount
     */
    boolean transferCoins(int fromUserId, int toUserId, int amount);

}
