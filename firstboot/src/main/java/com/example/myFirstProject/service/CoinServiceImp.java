package com.example.myFirstProject.service;


import com.example.myFirstProject.controller.CoinsController;
import com.example.myFirstProject.domain.Coins;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by serena on 3/26/17.
 */

@Service(value = "coinServiceImp")
public class CoinServiceImp implements CoinService {
    private static Logger logger = Logger.getLogger(CoinsController.class);

    private int defaultCoins = 0;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Coins> getRecord() {
        String sql = "SELECT * FROM COINS";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Coins.class));
    }

    @Override
    public boolean addUser(int userId) {
        if (checkUserIdExists(userId)) {
            logger.error("[info] -- "+ userId + " already exists");
            return false;
        }
        String sql = "INSERT INTO Coins(user_id, coins) VALUES(?,?)";
        return 0 != jdbcTemplate.update(sql, userId, defaultCoins);
    }

    @Override
    public int getUserCoins(int userId) {
        if (!checkUserIdExists(userId)) {
            logger.error("[info] -- " + userId + "does not exists");
            return 0;
        }
        String sql = "SELECT coins FROM COINS where user_id = ?";
        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql, userId);
        return res !=null && res.size()!=0 ? (int) res.get(0).get("coins") : 0;
    }

    @Override
    public boolean addUserWithCoins(int userId, int coins) {
        if (checkUserIdExists(userId)) {
            logger.info("[info] -- "+ userId + " already exists");
            return false;
        }
        String sql = "INSERT INTO Coins(user_id, coins) VALUES(?,?)";
        return 0 != jdbcTemplate.update(sql, userId, coins);
    }

    @Override
    public boolean appendCoins2UserId(int userId, int coins) {
        if (!checkUserIdExists(userId))
            addUser(userId);
        int currentCoins = getUserCoins(userId);
        int newAmount = currentCoins + coins;
        String sql = "UPDATE COINS SET coins = ? WHERE user_id = ?";
        return newAmount >= 0 && 0 != jdbcTemplate.update(sql, newAmount, userId);
    }

    /**
     * transfer coins from user1 to user2
     * this is a transactional function, follow the ACID
     * @param fromUserId
     * @param toUserId
     * @param amount
     * @return
     */
    @Transactional
    @Override
    public boolean transferCoins(int fromUserId, int toUserId, int amount) {
        if (!checkUserIdExists(fromUserId) || !checkValidAmount(fromUserId, amount))
            return false;
        logger.info("[info] -- Start Transaction");
        appendCoins2UserId(fromUserId, -amount);
        appendCoins2UserId(toUserId, amount);
        logger.info("[info] --End Transaction");
        return true;
    }

    /**
     * check if the userId already exists in DB
     * @param userId
     * @return boolean
     */
    public boolean checkUserIdExists(int userId) {
        String sql = "SELECT user_id FROM COINS WHERE user_id = ?";
        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql, userId);
        return res !=null && res.size() != 0 ? true : false;
    }

    /**
     * check if the userId's balance greater and equal to @param amount
     * @param userId
     * @param amount
     * @return boolean
     */
    public boolean checkValidAmount(int userId, int amount) {
        int userBalance = getUserCoins(userId);
        return amount >= 0 && amount <= userBalance ? true : false;
    }
}
